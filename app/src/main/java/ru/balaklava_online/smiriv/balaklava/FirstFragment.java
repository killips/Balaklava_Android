package ru.balaklava_online.smiriv.balaklava;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by smiriv on 13.07.2016.
 */
public class FirstFragment extends Fragment {
    private android.support.v7.app.ActionBar actionBar;
    private String Text;
    private Toolbar toolbar_fragmentFirst;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        Log.d("MyTag","Create Show Fragment "+this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        TextView t = (TextView) getActivity().findViewById(R.id.textFirst);
        outState.putCharSequence("textFirst",t.getText());
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_first, container, false);
        toolbar_fragmentFirst = (Toolbar)view.findViewById(R.id.main_toolbar);
        AppCompatActivity activity = (AppCompatActivity)getActivity();
        activity.setSupportActionBar(toolbar_fragmentFirst);
        actionBar = activity.getSupportActionBar();
        if(actionBar!= null) {
            actionBar.setTitle("Show");
        }
        if (getContext().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TextView t = (TextView) getActivity().findViewById(R.id.textFirst);
        if(savedInstanceState != null) {
            String lol = (String) savedInstanceState.getCharSequence("textFirst");
            if (lol != null) {
                t.setText(lol);
            }
        }
        if(Text != null) {
            t.setText(Text);
        }
        toolbar_fragmentFirst.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().findViewById(R.id.left).setVisibility(View.VISIBLE);
                getActivity().findViewById(R.id.right).setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void setText(String Text) {
        this.Text = Text;
    }
}
