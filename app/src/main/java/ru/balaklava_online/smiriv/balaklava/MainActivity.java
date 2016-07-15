package ru.balaklava_online.smiriv.balaklava;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    private FirstFragment firstFragment;
//    private SecondFragment secondFragment;
    private RecyclerViewFragment recyclerViewActivity;
    private boolean flag=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Log.d("AllTag", "CountFragment: " + fragmentManager.getBackStackEntryCount());

        LinearLayout fr = (LinearLayout) findViewById(R.id.right);
        LinearLayout fl = (LinearLayout) findViewById(R.id.left);

        if (fragmentManager.findFragmentByTag("right") == null && fr != null) {
            firstFragment = new FirstFragment();
            fragmentManager.beginTransaction().replace(R.id.right, firstFragment, "right").commit();
        }
//        if (fragmentManager.findFragmentByTag("left") == null && fl != null) {
//            secondFragment = new SecondFragment();
//            fragmentManager.beginTransaction().replace(R.id.left, secondFragment, "left").commit();
//        }
        if(fragmentManager.findFragmentByTag("left") == null && fl != null){
            recyclerViewActivity = new RecyclerViewFragment();
            fragmentManager.beginTransaction().replace(R.id.left, recyclerViewActivity, "left").commit();
        }
    }

}
