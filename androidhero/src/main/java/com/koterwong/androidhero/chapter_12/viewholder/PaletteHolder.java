package com.koterwong.androidhero.chapter_12.viewholder;


import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v7.graphics.Palette;
import android.view.Window;
import android.widget.ImageView;

import com.koterwong.androidhero.MainActivity;
import com.koterwong.androidhero.R;
import com.koterwong.androidhero.chapter_12.Chapter_12Fragment;

import butterknife.Bind;

/**
 * ================================================
 * Created By：Koterwong; Time: 2null16/null6/null6 2null:27
 * <p/>
 * Description:通过Palette获取Bitmap中的颜色信息。
 * =================================================
 */
public class PaletteHolder extends BaseHolder {

    @Bind(R.id.palette_img)
    ImageView mImageView;

    public PaletteHolder(Context context) {
        super(context);
    }

    @Override protected int getLayoutId() {
        return R.layout.view_palette;
    }

    @Override public void initData() {
        Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.palette);
        Palette.Builder builder = Palette.from(bitmap);
        builder.generate(new Palette.PaletteAsyncListener() {
            @Override public void onGenerated(Palette palette) {
                //充满活力的
                Palette.Swatch vibrantSwatch = palette.getVibrantSwatch();
                int rgb = 0xff000000;
                if (vibrantSwatch != null) {
                    rgb = vibrantSwatch.getRgb();
                    setStatueBarColor(rgb);
                    return;
                }
                //充满活力的亮
                Palette.Swatch lightVibrantSwatch = palette.getLightVibrantSwatch();
                if (lightVibrantSwatch != null) {
                    rgb = lightVibrantSwatch.getRgb();
                    setStatueBarColor(rgb);
                    return;
                }
                //充满活力的黑
                Palette.Swatch darkVibrantSwatch = palette.getDarkVibrantSwatch();
                if (darkVibrantSwatch != null) {
                    rgb = darkVibrantSwatch.getRgb();
                    setStatueBarColor(rgb);
                    return;
                }
                //柔和的
                Palette.Swatch mutedSwatch = palette.getMutedSwatch();
                if (mutedSwatch != null) {
                    rgb = mutedSwatch.getRgb();
                    setStatueBarColor(rgb);
                    return;
                }
                //柔和的亮
                Palette.Swatch lightMutedSwatch = palette.getLightMutedSwatch();
                if (lightMutedSwatch != null) {
                    rgb = lightMutedSwatch.getRgb();
                    setStatueBarColor(rgb);
                    return;
                }
                //柔和的黑
                Palette.Swatch darkMutedSwatch = palette.getDarkMutedSwatch();
                if (darkMutedSwatch != null) {
                    rgb = darkMutedSwatch.getRgb();
                    setStatueBarColor(rgb);
                }
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP) private void setStatueBarColor(int rgb) {
        if (mContext instanceof Activity) {
            MainActivity mMainUI = (MainActivity) mContext;
            Window window = mMainUI.getWindow();
            window.setStatusBarColor(rgb);
            Intent intent = new Intent(Chapter_12Fragment.ACTION);
            intent.putExtra(Chapter_12Fragment.NAME,rgb);
            mContext.sendBroadcast(intent);
        }
    }
}
