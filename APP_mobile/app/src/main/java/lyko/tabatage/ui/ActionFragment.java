package lyko.tabatage.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

import lyko.tabatage.R;

public class ActionFragment extends Fragment {

    String actionTime;
    String rest;
    String repeat;
    int nbrepeat;

    public ActionFragment(){
        nbrepeat = 0;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_action, container, false);
        Bundle bundle = getArguments();
        if (bundle != null) {
            actionTime = bundle.getString("actionTime");
            rest = bundle.getString("rest");
            repeat = bundle.getString("repeat");
        }

        TextView txtRepeat = view.findViewById(R.id.txtNbRepeat);
        TextView txtTime = view.findViewById(R.id.txtTime);

        txtRepeat.setText(String.format("0 / %s", repeat));
        txtTime.setText(String.format("%s s",actionTime));

        long timerDuration = TimeUnit.MINUTES.toMillis(1);
        long ticksInterval = 10;

        new CountDownTimer(timerDuration, ticksInterval) {
            long millis = 1000;
            @Override
            public void onTick(long millisUntilFinished) {
                millis = millis - ticksInterval;
                if (millis == 0){
                    millis = 1000;
                    txtRepeat.setText(String.format("%s / %s", nbrepeat, repeat));
                }
                String timerText = String.format(Locale.getDefault(),"%02d:%02d:%02d",
                        TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished));
                txtTime.setText(timerText);
            }

            @Override
            public void onFinish() {
                txtTime.setText("finished");
            }
        }.start();

        return view;
    }
}