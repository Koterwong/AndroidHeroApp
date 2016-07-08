package com.koterwong.androidhero.chapter_06;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import com.koterwong.androidhero.R;

public class XfermodeView_RoundRect extends View {

    private Bitmap mOut;

    public XfermodeView_RoundRect(Context context) {
        super(context);
        initView();
    }

    public XfermodeView_RoundRect(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public XfermodeView_RoundRect(Context context, AttributeSet attrs,
                                  int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP) public void initView() {
        Bitmap mBitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.img_xmode);
        mOut = Bitmap.createBitmap(mBitmap.getWidth(),
                mBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(mOut);
        Paint mPaint = new Paint();
        mPaint.setAntiAlias(true);
        canvas.drawRoundRect(0, 0,
                mBitmap.getWidth(),
                mBitmap.getHeight(), 80, 80, mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(
                PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(mBitmap, 0, 0, mPaint);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(mOut, 0, 0, null);
    }
}
