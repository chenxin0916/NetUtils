package view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class AskForHelperBgView extends View {

    private int mCenterX;
    private int mCenterY;
    private Paint mBgPaint;
    private int bgStartColor = Color.parseColor("#F3CD51");
    private int bgEndColor = Color.parseColor("#FA9A3A");
    private RectF mRoundBg;
    private int mWidth;
    private int mHeight;
    private Path mPath;
    private Paint mTraPaint;

    public AskForHelperBgView(Context context) {
        this(context,null);
    }

    public AskForHelperBgView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mRoundBg = new RectF();
        mBgPaint = new Paint();
        mTraPaint = new Paint();
        mTraPaint.setAntiAlias(true); //设置抗锯齿效果
        mTraPaint.setDither(true);
        mTraPaint.setColor(bgEndColor);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mCenterX = w/2;
        mCenterY = h/2;
        mWidth = w;
        mHeight = h;

        LinearGradient linearGradient = new LinearGradient(0,-h/2f,0,h/2f,bgStartColor,bgEndColor, Shader.TileMode.CLAMP);
        mBgPaint.setShader(linearGradient);


        mPath = new Path();
        mPath.moveTo(mWidth/4f,mHeight/4f);
        mPath.lineTo((mWidth/4f + mWidth/3f)/2f,mHeight/2f);
        mPath.lineTo(mWidth/3f,mHeight/4f);
        mPath.close();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mCenterX,mCenterY);
        mRoundBg.left =-mWidth/2f;
        mRoundBg.right = mWidth/2f;
        mRoundBg.top = -mHeight/2f;
        mRoundBg.bottom = mHeight/4f;
        float radius = Math.min(mWidth, mHeight) * 0.8f;
        canvas.drawPath(mPath,mTraPaint);
        canvas.drawRoundRect(mRoundBg, radius, radius,mBgPaint);

    }
}
