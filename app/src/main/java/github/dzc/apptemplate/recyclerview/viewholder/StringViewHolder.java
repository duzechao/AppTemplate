package github.dzc.apptemplate.recyclerview.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerviewViewHolder;

/**
 * Created by dzc on 16/1/22.
 */
public class StringViewHolder extends UltimateRecyclerviewViewHolder{
    public TextView tv;
    public StringViewHolder(View itemView,boolean isItem) {
        super(itemView);
        if(isItem){
            tv = (TextView) itemView;
        }
    }
}
