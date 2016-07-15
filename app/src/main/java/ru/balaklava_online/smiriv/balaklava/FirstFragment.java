package ru.balaklava_online.smiriv.balaklava;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by smiriv on 13.07.2016.
 */
public class FirstFragment extends Fragment {
    private android.app.ActionBar actionBar;
    private String Text;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_first, container, false);
        Toolbar toolbar_fragmentFirst = (Toolbar)view.findViewById(R.id.main_toolbar);
        AppCompatActivity activity = (AppCompatActivity)getActivity();
        activity.setSupportActionBar(toolbar_fragmentFirst);
        ActionBar actionBar = activity.getSupportActionBar();
        if(actionBar!= null) {
            actionBar.setTitle("First");
        }
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(Text != null){
            TextView t = (TextView) getActivity().findViewById(R.id.textFirst);
            t.setText(Text);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void setText(String Text) {
        this.Text = Text;
    }
}
