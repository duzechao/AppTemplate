package github.dzc.apptemplate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;

import java.util.ArrayList;
import java.util.List;

import github.dzc.apptemplate.recyclerview.viewholder.StringViewHolder;

/**
 * Created by dzc on 16/1/21.
 */
public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData(100);

    }
    public List<String> data;
    public void initData(int num){
        data = new ArrayList<>();
        for(int i=0;i<num;i++){
            data.add("item  "+i);
        }
    }
    public void setRecyclerViewData(UltimateRecyclerView recyclerView){
        recyclerView.setAdapter(new StringAdapter(data));
    }

    class StringAdapter extends RecyclerView.Adapter<StringViewHolder>{
        List<String> data;

        public StringAdapter(List<String> data) {
            this.data = data;
        }

        @Override
        public StringViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view  = LayoutInflater.from(BaseActivity.this).inflate(R.layout.string_item,null);
            return new StringViewHolder(view,true);
        }

        @Override
        public void onBindViewHolder(StringViewHolder holder, int position) {
            holder.tv.setText(data.get(position));
        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }

    public List<String> getData(){
        List<String> data = new ArrayList<>();
        for(int i=0;i<100;i++){
            data.add("item  "+i);
        }
        return data;
    }

}
