package github.dzc.apptemplate.sinaDemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import github.dzc.apptemplate.R;


public class SinaFragment extends Fragment {


    public SinaFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Bundle bundle = getArguments();
        String text = bundle.getString("text","textDefault");
        View view = inflater.inflate(R.layout.fragment_sina, container, false);
        TextView textView = (TextView) view.findViewById(R.id.tv);
        textView.setText(text);
        return view;
    }


}
