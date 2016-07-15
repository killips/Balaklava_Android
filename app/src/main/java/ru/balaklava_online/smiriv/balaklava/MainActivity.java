package ru.balaklava_online.smiriv.balaklava;

import android.content.res.Configuration;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    private FirstFragment firstFragment;
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private RecyclerViewFragment recyclerViewActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("MyTag", "CountFragment: " + fragmentManager.getBackStackEntryCount());
        Log.d("MyTag","Create MainActivity Fragment "+this);
        LinearLayout fr = (LinearLayout) findViewById(R.id.right);
        LinearLayout fl = (LinearLayout) findViewById(R.id.left);

        if (fragmentManager.findFragmentByTag("right") == null && fr != null) {
            firstFragment = new FirstFragment();
            fragmentManager.beginTransaction().add(R.id.right, firstFragment, "right").commit();
        }
        if(fragmentManager.findFragmentByTag("left") == null && fl != null){
            recyclerViewActivity = new RecyclerViewFragment();
            fragmentManager.beginTransaction().add(R.id.left, recyclerViewActivity, "left").commit();
        }
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            if (fragmentManager.findFragmentByTag("right") != null) {
                if (((RecyclerViewFragment) fragmentManager.findFragmentByTag("left")).getAtach()) {
                    findViewById(R.id.left).setVisibility(View.GONE);
                    findViewById(R.id.right).setVisibility(View.VISIBLE);
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        if(findViewById(R.id.left).getVisibility() == View.VISIBLE){
            super.onBackPressed();
        }
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            findViewById(R.id.left).setVisibility(View.VISIBLE);
            findViewById(R.id.right).setVisibility(View.GONE);
            ((RecyclerViewFragment) fragmentManager.findFragmentByTag("left")).setAtach(false);
        }
    }
}
