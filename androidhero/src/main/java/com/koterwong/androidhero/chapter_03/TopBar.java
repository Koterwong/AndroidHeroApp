package com.koterwong.androidhero.chapter_03;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.koterwong.androidhero.R;
import com.koterwong.androidhero.utils.DeviceUtils;

/**
 * ================================================
 * Created By：Koterwong; Time: 2016/06/01 08:54
 * <p>
 * Description:
 * =================================================
 */
public class TopBar extends RelativeLayout {

    private int mLeftTextColor;
    private int mRightTextColor;
    private int mTitleTextColor;

    private CharSequence mLeftTitle;
    private CharSequence mRightTitle;
    private CharSequence mTitle;

    private Drawable mLeftBg;
    private Drawable mRightBg;

    private float mTitleSize;

    private LayoutParams mLeftParams, mRightParams, mTitleParams;
    private Button mLeftBtn;
    private Button mRightBtn;
    private TextView mTitleTv;

    public TopBar(Context context) {
        this(context, null);
    }

    public TopBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.init(context, attrs);
        this.initView(context);
        this.initListener();
    }

    private void initListener() {
        mLeftBtn.setOnClickListener(new OnClickListener() {
            @Override public void onClick(View v) {
                if (mListener!=null){
                    mListener.onLeftClick(v);
                }
            }
        });
        mRightBtn.setOnClickListener(new OnClickListener() {
            @Override public void onClick(View v) {
                if (mListener!=null){
                    mListener.onRightClick(v);
                }
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void initView(Context context) {

        mLeftBtn = new Button(context);
        mRightBtn = new Button(context);
        mTitleTv = new TextView(context);

        mLeftBtn.setText(mLeftTitle);
        mLeftBtn.setTextColor(mLeftTextColor);
        mLeftBtn.setBackground(mLeftBg);
        mLeftParams = new LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        mLeftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        addView(mLeftBtn,mLeftParams);

        mRightBtn.setText(mRightTitle);
        mRightBtn.setTextColor(mRightTextColor);
        mRightBtn.setBackground(mRightBg);
        mRightParams  = new LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        mRightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        addView(mRightBtn,mRightParams);

        mTitleTv.setText(mTitle);
        mTitleTv.setTextColor(mTitleTextColor);
        mTitleTv.setTextSize(DeviceUtils.px2dp(getContext(),mTitleSize));
        mTitleTv.setGravity(Gravity.CENTER);
        mTitleParams = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.MATCH_PARENT);
        mTitleParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        addView(mTitleTv,mTitleParams);
    }

    private void init(Context context, AttributeSet attrs) {
        setBackgroundColor(0xFFF59563);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TopBar);
        mTitleSize = typedArray.getDimension(R.styleable.TopBar_titleTextSize, 12);
        mTitleTextColor = typedArray.getColor(R.styleable.TopBar_titleTextColor, 0);
        mTitle = typedArray.getText(R.styleable.TopBar_title);

        mLeftTitle = typedArray.getText(R.styleable.TopBar_leftText);
        mLeftBg = typedArray.getDrawable(R.styleable.TopBar_leftBackground);
        mLeftTextColor = typedArray.getColor(R.styleable.TopBar_leftTextColor, 0);

        mRightTextColor = typedArray.getColor(R.styleable.TopBar_rightTextColor, 0);
        mRightBg = typedArray.getDrawable(R.styleable.TopBar_rightBackground);
        mRightTitle = typedArray.getText(R.styleable.TopBar_rightText);

        typedArray.recycle();
    }

    @Override protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    private OnTopBarListener mListener;

    public void setOnTopbarClickListener(OnTopBarListener listener){
        mListener = listener;
    }
    public interface OnTopBarListener{
        void onLeftClick(View view);
        void onRightClick(View view);
    }

    public void setButtonVisible(int id,boolean flag){
        if (flag){
            if (id==0){
                mLeftBtn.setVisibility(View.VISIBLE);
            }else if (id==1){
                mRightBtn.setVisibility(View.VISIBLE);
            }
        }else{
            if (id==0){
                mLeftBtn.setVisibility(View.INVISIBLE);
            }else if (id==1){
                mRightBtn.setVisibility(View.INVISIBLE);
            }
        }
    }
}
