package github.dzc.apptemplate.lollianimation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import github.dzc.apptemplate.R;

public class Animation2Activity extends AppCompatActivity {

    @Bind(R.id.img)
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation2);
        ButterKnife.bind(this);
        img.setImageResource(R.mipmap.img);
    }
}
