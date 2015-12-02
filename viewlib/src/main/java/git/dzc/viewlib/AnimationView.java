package git.dzc.viewlib;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Animatable;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.TextureView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by dzc on 15/11/24.
 */
public class AnimationView extends TextView implements Animatable {
    private Paint measurePaint = new Paint();
    private Paint alphtPaint = new Paint();
    private String oldText = "";
    private String newText;
    private char[] oldChar;
    private char[] newChar;
    private float charWidth = 0;
    public AnimationView(Context context) {
        super(context);
    }

    public AnimationView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AnimationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public AnimationView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        if(charWidth<=0){
            charWidth = measurePaint.measureText(String.valueOf(oldChar[0]));
        }
        if(newText==null){
            canvas.drawText(oldText,getPaddingLeft(),getPaddingTop(),getPaint());
        }else{
            for(int i=0;i<oldChar.length;i++){
                char c = oldChar[i];
                canvas.drawText(String.valueOf(c),getPaddingLeft()+i*charWidth,getPaddingTop(),null);
            }
        }
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
