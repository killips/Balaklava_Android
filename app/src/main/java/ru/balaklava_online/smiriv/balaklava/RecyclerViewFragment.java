package ru.balaklava_online.smiriv.balaklava;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by smiriv on 14.07.2016.
 */
public class RecyclerViewFragment extends Fragment implements RecyclerViewAdapter.OnItemClickListener {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);


    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.activity_recyclerview, container, false);

        //Toolbar
        Toolbar toolbar_fragmentFirst = (Toolbar)view.findViewById(R.id.main_toolbar);
        AppCompatActivity activity = (AppCompatActivity)getActivity();
        activity.setSupportActionBar(toolbar_fragmentFirst);
        ActionBar actionBar = activity.getSupportActionBar();
        if(actionBar!= null) {
            actionBar.setTitle("MyList");
        }
        //EndToolbar

        List<Record> records = new ArrayList<Record>();
        populateRecords(records);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(records);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(itemAnimator);
        adapter.setmListener(this);
        return view;
    }


    private void populateRecords(List<Record> records){
        for (int i = 0; i<50; i++){
            Record record = new Record();
            record.setName("Item №" + i);
            record.setType(Record.Type.values()[i%3]);
            records.add(record);
        }
    }

    @Override
    public void onItemClick(int position, Record record) {
        FragmentManager fragmentManager = getFragmentManager();
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            FirstFragment firstFragment = new FirstFragment();
            firstFragment.setText(record.getName());
            fragmentManager.beginTransaction().replace(R.id.left, firstFragment, "replace").addToBackStack("MyStack").commit();
        }
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                TextView textView = (TextView) getActivity().findViewById(R.id.textFirst);
                if(textView != null)
                    textView.setText(record.getName());
        }
    }
}
