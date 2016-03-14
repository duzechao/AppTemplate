package github.dzc.apptemplate.fragment;

import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import github.dzc.apptemplate.R;

public class FragmentTestActivity extends AppCompatActivity implements BlankFragment.OnFragmentInteractionListener ,BlankFragment.OnCurrentFragmentChangedListener{

    private Handler handler;
    final static String TAG = "FragmentTestActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_test);
        handler = new Handler();
        addFragments(4);

        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                showStack();
            }
        });
    }

    private void addFragments(int count){
        for(int i=0;i<count;i++){
            getSupportFragmentManager().beginTransaction().add(R.id.content,BlankFragment.newInstance("fragment"+i),"fragment"+i).addToBackStack("fragment"+i).commit();
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.content,BlankFragment.newInstance("fragment"+count),"fragment"+count).commit();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                getSupportFragmentManager().beginTransaction().show(getSupportFragmentManager().findFragmentByTag("fragment2")).commit();
////                getSupportFragmentManager().beginTransaction().addToBackStack("dddd").commit();
////                getSupportFragmentManager().beginTransaction().addToBackStack("eeee").commit();
////                getSupportFragmentManager().beginTransaction().addToBackStack("ffff").commit();
//            }
//        },1000);
    }

    private void showStack(){
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(getSupportFragmentManager().getBackStackEntryCount()>0){
                    Log.d(TAG,"statckCount:"+getSupportFragmentManager().getBackStackEntryCount());
                    for(int i=0;i<getSupportFragmentManager().getBackStackEntryCount();i++){
                        BlankFragment blankFragment = (BlankFragment)(getSupportFragmentManager().findFragmentByTag(getSupportFragmentManager().getBackStackEntryAt(i).getName()));
                        Log.d(TAG,"currentFragmentStack :"+i+"   "+ blankFragment.getTitle()+"   tag:"+blankFragment.getTag()+"   id:"+blankFragment.getId()+"    isVisible "+blankFragment.isVisible());
                    }
                    Log.d("","============================");
                }
            }
        },2000);

    }

    @Override
    public void change(BlankFragment fragment) {

    }

    @Override
    public void onFragmentInteraction(String tag, boolean isOnce) {

    }
}
