package demo.imappcart.com.water_drop;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by 黑白 on 2017/11/2.
 */

public class ToucuPullView extends View {

    //圆形画笔
    private Paint Kcircle;
    //圆形的半径
    private int kCircleRadius = 100;
    //
    private int kCirclePointX, kCirclePointY;

    //进度值
    private float kProgress;
    //可拖动的参数
    private int kDragHeight = 800;

    public ToucuPullView(Context context) {
        this(context, null);
    }

    public ToucuPullView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ToucuPullView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 初始化方法
     */
    private void init() {
        //初始化
        Kcircle = new Paint(Paint.ANTI_ALIAS_FLAG);
        //设置抗锯齿
        Kcircle.setAntiAlias(true);
        //这只防抖动
        Kcircle.setDither(true);
        //设置为填充方式
        Kcircle.setStyle(Paint.Style.FILL);
        Kcircle.setColor(0xff000000);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(kCirclePointX, kCirclePointY, kCircleRadius, Kcircle);
    }

    /**
     * 当进行测量时触发
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //宽度的类型
        int kWidthSize = MeasureSpec.getSize(widthMeasureSpec);
        int kWidthModle = MeasureSpec.getMode(widthMeasureSpec);

        int kHeightSize = MeasureSpec.getSize(heightMeasureSpec);
        int kHeightModle = MeasureSpec.getMode(heightMeasureSpec);

        //
        int kHeight = (int) (kDragHeight * kProgress + 0.5f) + getPaddingTop() + getPaddingBottom();
        int kWidth = 2 * kCircleRadius + getPaddingLeft() + getPaddingRight();


        int measurWidth, measurHeight;
        //判断宽度是不是一个确定的值  初始化
        if (kWidthModle == MeasureSpec.EXACTLY) {
            //确切的值
            measurWidth = kWidthSize;
        } else if (kWidthModle == MeasureSpec.AT_MOST) {
            //最多
            measurWidth = Math.min(kWidthSize, kWidth);
        } else {
            measurWidth = kWidth;
        }


        if (kHeightModle == MeasureSpec.EXACTLY) {
            //确切的值
            measurHeight = kHeightSize;
        } else if (kHeightModle == MeasureSpec.AT_MOST) {
            //最多
            measurHeight = Math.min(kHeightSize, kHeight);
        } else {
            measurHeight = kHeight;
        }

        //设置宽度和高度
        setMeasuredDimension(measurWidth, measurHeight);

    }

    /**
     * 当大小改变时触发
     *
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //绘画一个圆形  绘画在控件的中心  向右移动两位相当于除二
        kCirclePointX = getWidth() >> 1;
        kCirclePointY = getHeight() >> 1;

    }

    /**
     * 设置进度
     *
     * @param progress 进度
     */
    public void setProgress(float progress) {
        //传递成功
        Log.d("zzzzzz", progress + "");
        kProgress = progress;
        //重新测量
        requestLayout();

    }
}
