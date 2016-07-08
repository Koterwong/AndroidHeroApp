package com.koterwong.androidhero.xpuzzle.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.koterwong.androidhero.R;
import com.koterwong.androidhero.xpuzzle.adapter.GridPicListAdapter;
import com.koterwong.androidhero.xpuzzle.util.ScreenUtil;
import com.koterwong.androidhero.utils.LogUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class XPuzzleMainActivity extends Activity implements OnClickListener {

    private static final int REQUEST_CODE_IMAGE = 0x001;
    private static final int REQUEST_CODE_CARAME = 0x002;
    public static String TEMP_IMAGE_PATH = Environment.getExternalStorageDirectory().getPath() + "/temp.png";

    private List<Bitmap> mPicList = new ArrayList<>();
    private int[] mResPicId = new int[]{R.drawable.pic1, R.drawable.pic2, R.drawable.pic3, R.drawable.pic4,
            R.drawable.pic5, R.drawable.pic6, R.drawable.pic7, R.drawable.pic8, R.drawable.pic9,
            R.drawable.pic10, R.drawable.pic11, R.drawable.pic12, R.drawable.pic13, R.drawable.pic14,
            R.drawable.pic15, R.mipmap.ic_launcher};

    private PopupWindow mPopupWindow;
    private View mPopupView;
    private GridView mPicGridView;
    private TextView mTypeSelectedTv;

    private int mType = 2;
    private String[] mCustomItems = new String[]{"本地图册", "相机拍照"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xpuzzle_main);
        this.initViews();
        this.initEvent();
        mPicGridView.setAdapter(new GridPicListAdapter(XPuzzleMainActivity.this, mPicList));
    }

    private void initViews() {
        mPicGridView = (GridView) findViewById(R.id.gv_xpuzzle_main_pic_list);
        Bitmap[] bitmaps = new Bitmap[mResPicId.length];
        for (int i = 0; i < bitmaps.length; i++) {
            bitmaps[i] = BitmapFactory.decodeResource(getResources(), mResPicId[i]);
            mPicList.add(bitmaps[i]);
        }
        mTypeSelectedTv = (TextView) findViewById(R.id.tv_puzzle_main_type_selected);

        //初始化PopupWindow相关
        LayoutInflater mLayoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        mPopupView = mLayoutInflater.inflate(R.layout.xpuzzle_main_type_selected, null);
        TextView mTvType2 = (TextView) mPopupView.findViewById(R.id.tv_main_type_2);
        TextView mTvType3 = (TextView) mPopupView.findViewById(R.id.tv_main_type_3);
        TextView mTvType4 = (TextView) mPopupView.findViewById(R.id.tv_main_type_4);
        mTvType2.setOnClickListener(this);
        mTvType3.setOnClickListener(this);
        mTvType4.setOnClickListener(this);
    }

    private void initEvent() {
        // Item点击监听
        mPicGridView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int position, long arg3) {
                if (position == mResPicId.length - 1) {
                    showDialogCustom();
                } else {
                    Intent intent = new Intent(XPuzzleMainActivity.this, PuzzleMain.class);
                    intent.putExtra("picSelectedID", mResPicId[position]);
                    intent.putExtra("mType", mType);
                    startActivity(intent);
                }
            }
        });

        //选择难度
        mTypeSelectedTv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                popupShow(v);
            }
        });
    }

    /**
     * 显示打开相机和选择图库的对话框
     */
    private void showDialogCustom() {
        AlertDialog.Builder builder = new AlertDialog.Builder(XPuzzleMainActivity.this);
        builder
                .setTitle("选择：")
                .setItems(mCustomItems, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (0 == which) {
                            //打开图库
                            Intent intent = new Intent(Intent.ACTION_PICK, null);
                            intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                            startActivityForResult(intent, REQUEST_CODE_IMAGE);
                        } else if (1 == which) {
                            //打开相机
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(TEMP_IMAGE_PATH)));
                            startActivityForResult(intent, REQUEST_CODE_CARAME);
                        }
                    }
                });
        builder.create().show();
    }

    /**
     * 调用图库相机回调方法
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE_IMAGE && data != null) {
                // 相册返回
                Cursor cursor = this.getContentResolver().query(data.getData(), null, null, null, null);
                if (cursor != null && cursor.moveToFirst()) {
                    String imagePath = cursor.getString(cursor.getColumnIndex("_data"));
                    Intent intent = new Intent(XPuzzleMainActivity.this, PuzzleMain.class);
                    intent.putExtra("picPath", imagePath);
                    intent.putExtra("mType", mType);
                    cursor.close();
                    startActivity(intent);
                }
            } else if (requestCode == REQUEST_CODE_CARAME) {
                // 相机返回
                Intent intent = new Intent(XPuzzleMainActivity.this, PuzzleMain.class);
                intent.putExtra("mPicPath", TEMP_IMAGE_PATH);
                intent.putExtra("mType", mType);
                startActivity(intent);
            }
        }
    }

    /**
     * 显示选择难度的popup window
     */
    private void popupShow(View view) {
        // 显示popup window
        float density = ScreenUtil.getDeviceDensity(this);
        mPopupWindow = new PopupWindow(mPopupView, (int) density * 80, (int) density * 140);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        int[] locations = new int[2];
        view.getLocationOnScreen(locations);
        LogUtils.e(locations[0] + "===" + locations[1] + "");
        mPopupWindow.showAtLocation(view, Gravity.NO_GRAVITY, locations[0], (int) (locations[1] + 40 * density));
    }

    /**
     * popup window item点击事件
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // Type
            case R.id.tv_main_type_2:
                mType = 2;
                mTypeSelectedTv.setText("2 X 2");
                break;
            case R.id.tv_main_type_3:
                mType = 3;
                mTypeSelectedTv.setText("3 X 3");
                break;
            case R.id.tv_main_type_4:
                mType = 4;
                mTypeSelectedTv.setText("4 X 4");
                break;
            default:
                break;
        }
        mPopupWindow.dismiss();
    }
}
