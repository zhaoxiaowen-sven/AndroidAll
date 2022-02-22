package com.sven.androidall;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.sven.androidall.leak.LeakUtils;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.cache.CacheRequest;
import okhttp3.internal.cache.CacheStrategy;
import okhttp3.internal.cache.InternalCache;

public class LaunchActivity extends AppCompatActivity {

    private static final String TAG = "LaunchActivity";
    Button mButton;
    TextView mEditText;
    ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        mButton = findViewById(R.id.test);
        mImageView = findViewById(R.id.iv);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ARouter.getInstance().build(HANDLER_PAGE).navigation();
//                ARouter.getInstance().build(EVENT_PAGE).navigation();
//                ARouter.getInstance().build(LIFE_CYCLE).navigation();
//                ARouter.getInstance().build(AOP).navigation();
//                ARouter.getInstance().build(ASM).navigation();
//                ARouter.getInstance().build(COROUTINE).navigation();

//                Intent intent = new Intent(LaunchActivity.this, CoroutineActivity.class);
//                startActivity(intent);
//                loadImage();


                OkHttpClient client = new OkHttpClient.Builder().cache(new CacheImpl())
                LeakUtils.getInstance(LaunchActivity.this);
            }
        });
    }

    private void loadImage() {
        String url = "https://www.baidu.com/img/PCtm_d9c8750bed0b3c7d089fa7d55720d6cf.png";
        Glide.with(this)
                //.asBitmap()// 生命周期
                .load(url) // 网络图片，本地资源，二进制流等等

                .placeholder(R.drawable.ic_ultraviolet) // 占位符，不能用网络资源做占位符
                .error(R.drawable.bg_clear_day) //
                .override(300, 300) // 限制图片尺寸，根据imageView
                .centerCrop()
                // 填充策略
                .fitCenter() // 完全显示，但是不一定能填充整个imageView
                .centerCrop() // 填充整个imageView 但是不能完全显示

                // 缓存策略
                .skipMemoryCache(true) // 跳过内存缓存

                // 磁盘缓存的 4 种策略
                // 磁盘不缓存
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                // 原始图片
//                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                // 降低分辨率后的图片
//                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                // 缓存所有版本的图片
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
                // 图片加载优先级，但是不一定能够完全安装这个优先级加载
                .priority(Priority.HIGH)

                .into(mImageView);
    }

    private class CacheImpl implements InternalCache {
        @Nullable
        @Override
        public Response get(Request request) throws IOException {
            return null;
        }

        @Nullable
        @Override
        public CacheRequest put(Response response) throws IOException {
            return null;
        }

        @Override
        public void remove(Request request) throws IOException {

        }

        @Override
        public void update(Response cached, Response network) {

        }

        @Override
        public void trackConditionalCacheHit() {

        }

        @Override
        public void trackResponse(CacheStrategy cacheStrategy) {

        }
    }
}
