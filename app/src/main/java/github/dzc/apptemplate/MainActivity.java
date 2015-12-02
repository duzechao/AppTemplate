package github.dzc.apptemplate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import git.dzc.downloadmanagerlib.download.DownloadManager;
import git.dzc.downloadmanagerlib.download.DownloadTask;
import github.dzc.apptemplate.event.MessageEvent;
import github.dzc.apptemplate.network.HttpUtils;

public class MainActivity extends AppCompatActivity {

    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
        HttpUtils.initHttpClientIntercept();
        tv = (TextView) findViewById(R.id.textView);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this,Main2Activity.class));
//                HttpUtils.get("http://www.baidu.com", new Callback() {
//                    @Override
//                    public void onFailure(Request request, IOException e) {
//
//                    }
//
//                    @Override
//                    public void onResponse(Response response) throws IOException {
//                        //  tv.setText(response.body().string());
//                        Log.d("response", response.toString());
//                        Log.d("response", response.body().string());
//                        // Log.d("response", response.cacheResponse().body().string());
//                        Response cachedResponse = response.cacheResponse();
//                        if (cachedResponse != null) {
//                            Log.d("cached", cachedResponse.toString());
//                        }
//
//
//                    }
//                });
                DownloadTask task = new DownloadTask();
                task.setId("id222");
                task.setSaveDirPath(getExternalCacheDir().getPath() + "/");
                task.setFileName("aaa.jpg");
                task.setUrl("http://d.hiphotos.baidu.com/baike/w%3D268/sign=151eb56428381f309e198aaf91004c67/21a4462309f790521313c0620cf3d7ca7bcbd55a.jpg");
                DownloadManager.getInstance(MainActivity.this).addDownloadTask(task);
            }
        });

       // Log.d("cached",client.getCache().getDirectory().toString());
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

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

    }

    @Subscribe
    public void onEvent(MessageEvent event){
        Log.d("test","onEvent");
        tv.setText(event.getMsg()+"    "+event.getPerson().getName());
    }

}
