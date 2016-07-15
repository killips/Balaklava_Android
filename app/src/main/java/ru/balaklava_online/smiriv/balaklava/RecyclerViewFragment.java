package ru.balaklava_online.smiriv.balaklava;

import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by smiriv on 14.07.2016.
 */
public class RecyclerViewFragment extends Fragment implements RecyclerViewAdapter.OnItemClickListener {
    private boolean Atach = false;
    private List<Record> records;

    public boolean getAtach(){
        return this.Atach;
    }
    public void setAtach(boolean Atach){
        this.Atach = Atach;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

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
        if(records == null) {
            records = new ArrayList<Record>();
            populateRecords(records);
        }
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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onItemClick(int position, Record record) {

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            TextView textView = (TextView) getActivity().findViewById(R.id.textFirst);
            Atach = true;
            if(textView != null)
                textView.setText(record.getName());
        }

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            getActivity().findViewById(R.id.left).setVisibility(View.GONE);
            TextView textView = (TextView) getActivity().findViewById(R.id.textFirst);
            Atach = true;
            if(textView != null)
                textView.setText(record.getName());
            getActivity().findViewById(R.id.right).setVisibility(View.VISIBLE);
        }
    }

    private void populateRecords(List<Record> records){
        for (int i = 0; i<50; i++){
            Record record = new Record();
            record.setName("Item №" + i);
            record.setType(Record.Type.values()[i%3]);
            records.add(record);
        }
    }
}
