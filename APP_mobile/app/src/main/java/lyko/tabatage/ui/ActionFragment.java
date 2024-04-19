package lyko.tabatage.ui;

import static java.lang.Long.parseLong;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

import lyko.tabatage.R;

public class ActionFragment extends Fragment {

    String actionTime;
    String rest;
    String repeat;
    int nbRepeat;
    TextView txtRepeat;
    TextView txtTime;
    boolean chronoActif;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_action, container, false);

        chronoActif = false;
        nbRepeat = 0;
        //récupération des valeurs du formulaire
        Bundle bundle = getArguments();
        if (bundle != null) {
            actionTime = bundle.getString("actionTime");
            rest = bundle.getString("rest");
            repeat = bundle.getString("repeat");
        }

        //récupération des éléments du layout
        txtRepeat = view.findViewById(R.id.txtNbRepeat);
        txtTime = view.findViewById(R.id.txtTime);
        Button btnSwitch = view.findViewById(R.id.btnSwitch);

        //initialisation de l'affichage des répétition
        txtRepeat.setText(String.format("0 / %s", repeat));

        //initilisation du chrono
        txtTime.setText(String.format(getString(R.string.txtTime), 0, 0, 0));

        //click sur le boutton
        btnSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!chronoActif){
                    newTimerAction();
                    chronoActif = true;
                }
            }
        });
        return view;
    }

    public void newTimerAction(){
        //initialisation des valeurs du timer
        long timerDuration = TimeUnit.SECONDS.toMillis(parseLong(actionTime));
        long ticksInterval = 10;
        txtRepeat.setText(String.format("%s / %s", ++nbRepeat, repeat));

        new CountDownTimer(timerDuration, ticksInterval) {
            long millis = 1000;
            @Override
            public void onTick(long millisUntilFinished) {
                //gestion du temps qui passe
                millis = millis - ticksInterval;
                if (millis == 0){
                    millis = 1000;
                }
                //gestion de l'affichage du timer
                String timerText = String.format(Locale.getDefault(),getString(R.string.txtTime),
                        TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished));
                txtTime.setText(timerText);
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onFinish() {
                //fin du timer
                if(nbRepeat < Integer.parseInt(repeat)){
                    newTimerRest();
                }
                else{
                    txtTime.setText("finished");
                }
            }
        }.start();
    }

    public void newTimerRest(){
        //initialisation des valeurs du timer
        long timerDuration = TimeUnit.SECONDS.toMillis(parseLong(rest));
        long ticksInterval = 10;

        new CountDownTimer(timerDuration, ticksInterval) {
            long millis = 1000;
            @Override
            public void onTick(long millisUntilFinished) {
                //gestion du temps qui passe
                millis = millis - ticksInterval;
                if (millis == 0){
                    millis = 1000;
                }
                //gestion de l'affichage du timer
                String timerText = String.format(Locale.getDefault(),getString(R.string.txtTime),
                        TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished));
                txtTime.setText(timerText);
            }

            @Override
            public void onFinish() {
                //fin du timer
                newTimerAction();
            }
        }.start();
    }
}