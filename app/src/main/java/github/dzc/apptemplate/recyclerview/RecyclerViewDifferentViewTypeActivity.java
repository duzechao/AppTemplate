package github.dzc.apptemplate.recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.marshalchen.ultimaterecyclerview.UltimateDifferentViewTypeAdapter;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerviewViewHolder;
import com.marshalchen.ultimaterecyclerview.itemTouchHelper.SimpleItemTouchHelperCallback;
import com.marshalchen.ultimaterecyclerview.multiViewTypes.DataBinder;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import github.dzc.apptemplate.BaseActivity;
import github.dzc.apptemplate.R;

public class RecyclerViewDifferentViewTypeActivity extends BaseActivity {

    @Bind(R.id.recycler_view)
    UltimateRecyclerView recyclerView;
    View headerView;
    DifferentViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_different_view_type);
        ButterKnife.bind(this);
        initView();
    }

    private void initView(){
        headerView = LayoutInflater.from(this).inflate(R.layout.head_view,null);

        adapter = new DifferentViewAdapter(getData(),getData());
        recyclerView.setLayoutManager(new LinearLayoutManager(RecyclerViewDifferentViewTypeActivity.this));
        recyclerView.setAdapter(adapter);
//        recyclerView.setParallaxHeader(headerView);//这里无法设置头部  因为adapter重写了方法
//        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(adapter);
//
//        final ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
//        itemTouchHelper.attachToRecyclerView(recyclerView.mRecyclerView);//不同类型之间的交换需要重新实现交换的方法
    }

    //设置view的类型
    enum ViewType{
        ITEM1,ITEM2
    }
    //第一种view的viewHolder
    class ViewHolder1 extends UltimateRecyclerviewViewHolder{
        TextView tv;

        public ViewHolder1(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv);

        }
    }
    //第二种view的viewHolder
    class ViewHolder2 extends UltimateRecyclerviewViewHolder{
        TextView tv;

        public ViewHolder2(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv);
        }
    }

    //第一种view的数据绑定
    class DataBinder1 extends com.marshalchen.ultimaterecyclerview.multiViewTypes.DataBinder<ViewHolder1>{
        private List<String> data;

        public DataBinder1(UltimateDifferentViewTypeAdapter dataBindAdapter, List<String> data) {
            super(dataBindAdapter);
            this.data = data;
        }

        @Override
        public ViewHolder1 newViewHolder(ViewGroup parent) {
            //这个方法会在adapter的onCreateViewHolder调用  这里设置view的布局
            View view = LayoutInflater.from(RecyclerViewDifferentViewTypeActivity.this).inflate(R.layout.string_item_2,parent,false);
            return new ViewHolder1(view);
        }

        @Override
        public void bindViewHolder(ViewHolder1 holder, int position) {
            //这个方法绑定数据   这个方法会在onBindViewHolder中调用
            holder.tv.setText("type 1  item:"+data.get(position));
        }

        @Override
        public int getItemCount() {
            return data==null?0:data.size();
        }
    }

    //同DataBinder1
    class DataBinder2 extends com.marshalchen.ultimaterecyclerview.multiViewTypes.DataBinder<ViewHolder2>{
        private List<String> data;

        public DataBinder2(UltimateDifferentViewTypeAdapter dataBindAdapter, List<String> data) {
            super(dataBindAdapter);
            this.data = data;
        }

        @Override
        public ViewHolder2 newViewHolder(ViewGroup parent) {
            View view = LayoutInflater.from(RecyclerViewDifferentViewTypeActivity.this).inflate(R.layout.string_item,parent,false);
            return new ViewHolder2(view);
        }

        @Override
        public void bindViewHolder(ViewHolder2 holder, int position) {
            holder.tv.setText("type 2  item:"+data.get(position));
        }

        @Override
        public int getItemCount() {
            return data==null?0:data.size();
        }
    }
    private class DifferentViewAdapter extends UltimateDifferentViewTypeAdapter<ViewType>{
        public DifferentViewAdapter(List<String> data1, List<String> data2) {
            this.data1 = data1;
            this.data2 = data2;

            //下面这两句一定要调用  这个是将你的View的类型和数据传递给adapter
            putBinder(ViewType.ITEM1,new DataBinder1(this,data1));
            putBinder(ViewType.ITEM2,new DataBinder2(this,data2));
        }

        private List<String> data1;
        private List<String> data2;

        @Override
        public ViewType getEnumFromPosition(int position) {
            //根据item的position返回对应的类型
            //这个根据需要自定义返回类型  这里按奇偶数返回不同类型
            return ViewType.values()[position%2];
        }

        @Override
        public ViewType getEnumFromOrdinal(int ordinal) {
            return ViewType.values()[ordinal];
            //根据enum的序号返回对应的值  这个没什么好说的  一般这里就这样写
        }

        @Override
        public UltimateRecyclerviewViewHolder getViewHolder(View view) {
            return new UltimateRecyclerviewViewHolder(view);
        }

        @Override
        public UltimateRecyclerviewViewHolder onCreateViewHolder(ViewGroup parent) {

            return new UltimateRecyclerviewViewHolder(parent);
        }

//        @Override
//        public UltimateRecyclerviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            if (viewType == VIEW_TYPES.FOOTER) {
//                UltimateRecyclerviewViewHolder viewHolder = getViewHolder(customLoadMoreView);
//                if (getAdapterItemCount() == 0)
//                    viewHolder.itemView.setVisibility(View.GONE);
//                return viewHolder;
//            } else if (viewType == VIEW_TYPES.HEADER) {
//                if (customHeaderView != null)
//                    return getViewHolder(customHeaderView);
//            } else if (viewType == VIEW_TYPES.CHANGED_FOOTER) {
//                UltimateRecyclerviewViewHolder viewHolder = getViewHolder(customLoadMoreView);
//                if (getAdapterItemCount() == 0)
//                    viewHolder.itemView.setVisibility(View.GONE);
//                return viewHolder;
//            }
//            return super.onCreateViewHolder(parent,viewType);
//        }

        @Override
        public int getAdapterItemCount() {
            int count =0;
            for(ViewType viewType:ViewType.values()){
                count+=getDataBinder(viewType).getItemCount();
            }
            //这里返回所有不同类型的item的总和
            return count;
        }

//        @Override
//        public int getItemCount() {
//            int headerOrFooter = 0;
//            if (customHeaderView != null) headerOrFooter++;
//            if (customLoadMoreView != null) headerOrFooter++;
//            return getAdapterItemCount() + headerOrFooter;
//        }

        @Override
        public long generateHeaderId(int position) {
            return 0;
        }

        @Override
        public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
            return null;
        }

        @Override
        public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {

        }
    }
}
