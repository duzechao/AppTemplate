package github.dzc.apptemplate.sinaDemo;

import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import github.dzc.apptemplate.R;

public class SinaActivity extends AppCompatActivity {
    private FragmentTabHost mTabHost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sina);
        //初始化tabs
        initTabs();
    }


    private void initTabs(){
        mTabHost = (FragmentTabHost)findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        //初始化4个tabs界面

        Bundle bundle = new Bundle();
        bundle.putString("text","fragment1");

        Bundle bundle2 = new Bundle();
        bundle2.putString("text","fragment2");

        Bundle bundle3 = new Bundle();
        bundle3.putString("text","fragment3");

        Bundle bundle4 = new Bundle();
        bundle4.putString("text","fragment4");

        Bundle bundleCenter = new Bundle();
        bundleCenter.putString("text","centerFragment");
        View tab1 = getLayoutInflater().inflate(R.layout.sina_item, null);
        View tab2 = getLayoutInflater().inflate(R.layout.sina_item, null);
        View tab3 = getLayoutInflater().inflate(R.layout.sina_item, null);
        View tab4 = getLayoutInflater().inflate(R.layout.sina_item, null);
        View tabCenter = getLayoutInflater().inflate(R.layout.sina_center_item, null);

        //addTab(标题，跳转的Fragment，传递参数的Bundle)

        mTabHost.addTab(mTabHost.newTabSpec("tab1").setIndicator(tab1), SinaFragment.class, bundle);
        mTabHost.addTab(mTabHost.newTabSpec("tab2").setIndicator(tab2), SinaFragment.class, bundle2);
        mTabHost.addTab(mTabHost.newTabSpec("tabCenter").setIndicator(tabCenter), SinaFragment.class, bundleCenter);
        mTabHost.addTab(mTabHost.newTabSpec("tab3").setIndicator(tab3), SinaFragment.class, bundle3);
        mTabHost.addTab(mTabHost.newTabSpec("tab4").setIndicator(tab4), SinaFragment.class, bundle4);
        //设置tabs之间的分隔线不显示
        mTabHost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);
    }


}
