package github.dzc.apptemplate;

import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PopupWindowActivity extends AppCompatActivity {

    private TextView tv;
    private EditText et;
    private PopupWindow popupWindow;
    private TextView tvContent;
    private AlertDialog alertDialog;
    private ListView listView;

    private List<String> stringList = new ArrayList<>();
    private List<String> stringList2 = new ArrayList<>();
    private BaseAdapter baseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_window);
        for(int i=0;i<20;i++){
            stringList.add("string1111  "+i);
            stringList2.add("string22222  "+i);
        }
        baseAdapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return stringList.size();
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView textView = new TextView(PopupWindowActivity.this);
                textView.setText(stringList.get(position));
                return textView;
            }

        };
        listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(baseAdapter);
        tvContent = (TextView) findViewById(R.id.tv_content);
        tv = (TextView) findViewById(R.id.tv);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = LayoutInflater.from(PopupWindowActivity.this).inflate(R.layout.popup_window,null);
                et = (EditText) view.findViewById(R.id.et);
                et.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        tv.setText(et.getText());
                    }
                });
                popupWindow = new PopupWindow(view,WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.MATCH_PARENT);
                popupWindow.setFocusable(true);
                popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        stringList.clear();
                        stringList.addAll(stringList2);
                        baseAdapter.notifyDataSetChanged();
                    }
                });
                popupWindow.showAsDropDown(tv);
//                view.setMinimumWidth(getWindowManager().getDefaultDisplay().getWidth());
//                alertDialog = new AlertDialog.Builder(PopupWindowActivity.this).setView(view).show();
            }
        });
    }
}
