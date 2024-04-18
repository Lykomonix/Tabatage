package lyko.tabatage.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import lyko.tabatage.R;


public class ChoiceFragment extends Fragment {

    private FragmentManager fragmentManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState == null){
            fragmentManager = getParentFragmentManager();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_choice, container, false);
        Button btnSubmit = view.findViewById(R.id.btnSubmit);

        TextView txteActionTime = view.findViewById(R.id.txteActionTime);
        TextView txteRest = view.findViewById(R.id.txteRest);
        TextView txteRepeat = view.findViewById(R.id.txteRepeat);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strActionTime = txteActionTime.getText().toString();
                String strRest = txteRest.getText().toString();
                String strRepeat = txteRepeat.getText().toString();
                if(!strActionTime.isEmpty() && !strRest.isEmpty() && !strRepeat.isEmpty()){
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.replace(R.id.fcvActivity, ActionFragment.class, null);
                    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
                else {
                    CharSequence text = "vous n'avez pas rempli les champs";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(getContext(), text, duration);
                    toast.show();
                }
            }
        });
        return view;
    }
}