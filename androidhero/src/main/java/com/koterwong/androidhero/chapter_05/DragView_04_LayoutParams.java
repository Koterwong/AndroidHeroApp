package com.koterwong.androidhero.chapter_05;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.koterwong.androidhero.R;

/**
 * ================================================
 * Created By：Koterwong; Time: 2016/06/02 12:58
 * <p/>
 * Description: Use LeftMargin
 * =================================================
 */
public class DragView_04_LayoutParams extends View {

    public DragView_04_LayoutParams(Context context) {
        super(context);
        initView();
    }

    public DragView_04_LayoutParams(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP) private void initView() {
        setBackgroundColor(getResources().getColor(R.color.brown));
        setElevation(5f);
    }

    @Override protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(getResources().getColor(R.color.colorPrimary));
        paint.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));

        String text = "LayoutParams(bug)";
        float width = paint.measureText(text);
        float height = (paint.descent() + paint.ascent());
        canvas.drawText(text, 0, text.length(), getWidth() / 2 - width / 2, getHeight() / 2 - height / 2, paint);
    }

    private int mListX;
    private int mLastY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getRawX();
        int y = (int) event.getRawY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mListX = (int) event.getRawX();
                mLastY = (int) event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                int dx = x - mListX;
                int dy = y - mLastY;
                ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) getLayoutParams();
//                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) getLayoutParams();
                layoutParams.leftMargin = dx;
                layoutParams.topMargin = dy;
                setLayoutParams(layoutParams);
                break;
        }
        return true;
    }
}
