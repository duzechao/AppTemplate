package github.dzc.apptemplate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.marshalchen.ultimaterecyclerview.UltimateRecyclerviewViewHolder;
import com.marshalchen.ultimaterecyclerview.UltimateViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import github.dzc.apptemplate.fragment.FragmentActivity;
import github.dzc.apptemplate.fragment.FragmentTestActivity;
import github.dzc.apptemplate.lollianimation.Animation1Activity;
import github.dzc.apptemplate.recyclerview.CoordinatorLayoutActivity;
import github.dzc.apptemplate.recyclerview.RecyclerViewActivity;
import github.dzc.apptemplate.recyclerview.RecyclerViewDifferentViewTypeActivity;
import github.dzc.apptemplate.recyclerview.viewholder.StringViewHolder;
import github.dzc.apptemplate.sinaDemo.SinaActivity;

public class MainActivity extends BaseActivity {

    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;

    private List<Class> classes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    private void initView(){
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new StringAdapter(classes));

    }
    private void initData(){
        classes = new ArrayList<>();
        classes.add(RecyclerViewActivity.class);
        classes.add(RecyclerViewDifferentViewTypeActivity.class);
        classes.add(CoordinatorLayoutActivity.class);
        classes.add(PopupWindowActivity.class);
        classes.add(FragmentActivity.class);
        classes.add(FragmentTestActivity.class);
        classes.add(Animation1Activity.class);
        classes.add(SinaActivity.class);
    }

    class StringAdapter extends RecyclerView.Adapter<StringViewHolder>{
        List<Class> classes;

        public StringAdapter(List<Class> classes) {
            this.classes = classes;
        }

        @Override
        public StringViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view  = LayoutInflater.from(MainActivity.this).inflate(R.layout.string_item,null);
            return new StringViewHolder(view,true);
        }

        @Override
        public void onBindViewHolder(StringViewHolder holder, int position) {
            final Class c = classes.get(position);
            holder.tv.setText(c.getSimpleName());
            holder.tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this,c));
                }
            });
        }

        @Override
        public int getItemCount() {
            return classes.size();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
