package lyko.tabatage.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import lyko.tabatage.R;

public class ActionFragment extends Fragment {

    String actionTime;
    String rest;
    String repeat;

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

        return view;
    }
}