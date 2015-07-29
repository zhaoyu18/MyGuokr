package com.example.wangzhaoyu.myguokr.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.wangzhaoyu.myguokr.R;
import com.example.wangzhaoyu.myguokr.core.net.Network;
import com.example.wangzhaoyu.myguokr.databinding.ActivityPhotoPickerBinding;
import com.example.wangzhaoyu.myguokr.model.response.ArticleList;
import com.example.wangzhaoyu.myguokr.network.HttpClient;
import com.example.wangzhaoyu.myguokr.network.api.ArticleService;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * @author wangzhaoyu
 */
public class PhotoPickerActivity extends AppCompatActivity {
    private static final String TAG = PhotoPickerActivity.class.getSimpleName();
    private ActivityPhotoPickerBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_photo_picker);
        Glide.with(this).load("http://goo.gl/gEgYUd").into(mBinding.testImage);

        ArticleService articleService = HttpClient.getInstance().getArticleService();
        articleService.getArticleList(
                Network.Parameters.RetrieveType.BY_SUBJECT,
                20,
                0,
                new Callback<ArticleList>() {
                    @Override
                    public void success(ArticleList list, Response response) {
                        Toast.makeText(PhotoPickerActivity.this, list.getTotal() + "", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {

                    }
                });
    }
}
