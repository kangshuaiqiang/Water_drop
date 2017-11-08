package demo.imappcart.com.water_drop;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    //记录按下时的点的位置
    private float kTouchMoveStarY = 0;
    //设置一个最大值  代表最多移动600
    private static final float TOUCH_MAX_Y = 600;
    private ToucuPullView kPullview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        kPullview = (ToucuPullView) findViewById(R.id.touchPull);

        //拉动父控件相应自定义View
        findViewById(R.id.mainTouch).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        kTouchMoveStarY = event.getY();
                        ///代表消费
                        return true;

                    case MotionEvent.ACTION_MOVE:
                        float y = event.getY();
                        //代表往下拉
                        if (y >= kTouchMoveStarY) {
                            //计算打动多少
                            float movesize = y - kTouchMoveStarY;

                            //j进度计算  如果大于最大移动范围取1   如果没有就用就去除数
                            float progress = movesize >= TOUCH_MAX_Y ? 1 : movesize / TOUCH_MAX_Y;
                            kPullview.setProgress(progress);
                        }
                        return true;

                    default:
                        break;
                }

                return false;
            }
        });
    }
}
