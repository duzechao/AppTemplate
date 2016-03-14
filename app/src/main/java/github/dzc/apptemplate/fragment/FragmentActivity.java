package github.dzc.apptemplate.fragment;

import android.net.Uri;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import github.dzc.apptemplate.R;

public class FragmentActivity extends AppCompatActivity implements BlankFragment.OnFragmentInteractionListener ,BlankFragment.OnCurrentFragmentChangedListener{


    private BlankFragment currentFragment;
    private BlankFragment fragment2;
    private BlankFragment prevFragment;

    private int count = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        BlankFragment fragment1 = BlankFragment.newInstance("fragment1");

        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                Log.d("activity ","onBackStackChanged");

            }
        });
        show(fragment1);
    }

    @Override
    public void onFragmentInteraction(String tag,boolean isOnce) {
        if(isOnce){
            BlankFragment fragment = BlankFragment.newInstance(tag,isOnce);
            show(fragment);
        }else{
            if(fragment2==null){
                fragment2 = BlankFragment.newInstance(tag,false);
            }
            show(fragment2);
        }
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(getSupportFragmentManager().getBackStackEntryCount()>0){
                    for(int i=0;i<getSupportFragmentManager().getBackStackEntryCount();i++){
                        BlankFragment blankFragment = (BlankFragment)(getSupportFragmentManager().findFragmentByTag(getSupportFragmentManager().getBackStackEntryAt(i).getName()));
                        Log.d("111111","currentFragmentStack :"+i+"   "+ blankFragment.getTitle()+"   tag:"+blankFragment.getTag()+"    isVisible "+blankFragment.isVisible());
                    }
                    Log.d("","============================");
                }
            }
        },2000);

    }
    private Handler handler = new Handler();

    public void show(BlankFragment fragment){
        count++;
//        if(currentFragment!=fragment){
//            prevFragment = currentFragment;
//            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//
//            if(getSupportFragmentManager().findFragmentByTag(fragment.getFTag())==null){
//                Log.d("Activity",fragment.getTitle()+"==null");
//                fragmentTransaction.add(R.id.content,fragment,fragment.getFTag()).addToBackStack(fragment.getFTag());
//                if(currentFragment!=null&&currentFragment!=fragment){
//                    fragmentTransaction.hide(currentFragment);
//                }
//            }else{
//                Log.d("Activity",fragment.getTitle()+"!=null");
//                fragmentTransaction.show(fragment);
//                if(currentFragment!=null&&currentFragment!=fragment){
//                    fragmentTransaction.hide(currentFragment);
//                }
//            }
//            fragmentTransaction.commitAllowingStateLoss();
//            currentFragment = fragment;
//        }
        prevFragment = currentFragment;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if(getSupportFragmentManager().getBackStackEntryCount()>0){
            String topTag = getSupportFragmentManager().getBackStackEntryAt(getSupportFragmentManager().getBackStackEntryCount()-1).getName();
            BlankFragment topFragment = (BlankFragment) getSupportFragmentManager().findFragmentByTag(topTag);
            if(fragment.isAdded()){
                if(topFragment!=fragment){
                    fragmentTransaction.hide(topFragment);
                }
                fragmentTransaction.show(fragment);
            }else{
                if(count>5){

                    fragmentTransaction.replace(R.id.content,fragment,fragment.getFTag());
                    fragmentTransaction.addToBackStack(fragment.getFTag());
                }else{
                    if(currentFragment!=null&&currentFragment!=fragment){
                        fragmentTransaction.hide(currentFragment);
                    }
                    fragmentTransaction.hide(topFragment).add(R.id.content,fragment,fragment.getFTag()).show(fragment);
                    fragmentTransaction.addToBackStack(fragment.getFTag());
                }

            }
        }else{
            fragmentTransaction.add(R.id.content,fragment,fragment.getFTag()).addToBackStack(fragment.getFTag());
        }
        fragmentTransaction.commitAllowingStateLoss();
        currentFragment = fragment;

    }

    @Override
    public void onBackPressed() {
//        if (!getSupportFragmentManager().popBackStackImmediate()) {
//        }else{
//
//        }
//        supportFinishAfterTransition();

//        if(getSupportFragmentManager().getBackStackEntryCount()<=1){
//            finish();
//        }else{
//            if(prevFragment!=currentFragment){
//                if(currentFragment.isOnce()){
//                    getSupportFragmentManager().popBackStack(null,0);
//                }else{
//                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                    fragmentTransaction.hide(currentFragment);
//                    fragmentTransaction.show(prevFragment).commitAllowingStateLoss();
//                }
//            }else{
//                getSupportFragmentManager().popBackStack(null,0);
//            }
//        }
        Log.d("","fragments count :"+getSupportFragmentManager().getBackStackEntryCount());
        if(getSupportFragmentManager().getBackStackEntryCount()>=2){
            String tag = getSupportFragmentManager().getBackStackEntryAt(getSupportFragmentManager().getBackStackEntryCount()-1).getName();
            String tagIndex2 = getSupportFragmentManager().getBackStackEntryAt(getSupportFragmentManager().getBackStackEntryCount()-2).getName();
            BlankFragment topFragment = (BlankFragment) getSupportFragmentManager().findFragmentByTag(tag);
            BlankFragment index2Fragment = (BlankFragment) getSupportFragmentManager().findFragmentByTag(tagIndex2);
            getSupportFragmentManager().popBackStack();
            if(topFragment.isOnce()){
                getSupportFragmentManager().popBackStack(tag,1);
//                getSupportFragmentManager().beginTransaction().show(index2Fragment).commitAllowingStateLoss();
            }else{
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                int fragmentCount = getSupportFragmentManager().getBackStackEntryCount();
                if(prevFragment!=null){
                    for(int i=fragmentCount-1;i<fragmentCount;i--){
                        BlankFragment blankFragment = (BlankFragment) getSupportFragmentManager().findFragmentByTag(getSupportFragmentManager().getBackStackEntryAt(i).getName());
                        if(blankFragment!=prevFragment && blankFragment.isVisible()){
                            fragmentTransaction.hide(blankFragment);
                        }else{
                            break;
                        }
                    }
                    fragmentTransaction.show(prevFragment);
                }

                fragmentTransaction.commit();
            }

        }else{
            finish();
        }
    }

    @Override
    public void change(BlankFragment fragment) {
        currentFragment = fragment;
        Log.d("","currentFragment tag="+currentFragment.getTag());

    }
}
