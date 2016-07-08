package com.koterwong.androidhero.chapter_12;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.koterwong.androidhero.R;

/**
 * Description:
 * <p/>
 * Created By：Koterwong; Time: 2016/07/07 19:03
 */
public class AnimAActivity extends AppCompatActivity {

    private static final String TAG = "AnimAActivity";

    private Intent intent;


    @Override public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim_a);
        Log.d(TAG, "onCreate: ====");
        intent = new Intent(this, AnimBActivity.class);
    }

    // 设置不同动画效果
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void explode(View view) {
        intent.putExtra("flag", 0);
//        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        ActivityCompat.startActivity(this, intent, ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle());
    }

    // 设置不同动画效果
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void slide(View view) {
        intent.putExtra("flag", 1);
//        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        ActivityCompat.startActivity(this, intent, ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle());
    }

    // 设置不同动画效果
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void fade(View view) {
        intent.putExtra("flag", 2);
//        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        ActivityCompat.startActivity(this, intent, ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle());
    }

    // 设置不同动画效果
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void share(View view) {
        View fab = findViewById(R.id.fab_button);
        intent.putExtra("flag", 3);
//        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this,
//                // 创建多个共享元素
//                Pair.create(view, "share"),
//                Pair.create(fab, "fab")).toBundle());
        ActivityCompat.startActivity(this, intent, ActivityOptionsCompat.makeSceneTransitionAnimation(this,
                android.support.v4.util.Pair.create(view,"share"),
                android.support.v4.util.Pair.create(fab,"fab")).toBundle());
    }
}
