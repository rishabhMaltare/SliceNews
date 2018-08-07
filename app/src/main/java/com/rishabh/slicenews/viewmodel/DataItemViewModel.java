package com.rishabh.slicenews.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.text.TextUtils;
import android.widget.ImageView;

import com.rishabh.slicenews.R;
import com.rishabh.slicenews.model.Article;
import com.squareup.picasso.Picasso;

/**
 * Created by Gregory Rasmussen on 7/26/17.
 */

public class DataItemViewModel extends BaseObservable {

    private Article article;

    public DataItemViewModel(Article article) {
        this.article = article;
    }

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        if (TextUtils.isEmpty(imageUrl)) {
            imageUrl = null;
        }
        Picasso.get()
                .load(imageUrl)
                .error(R.drawable.ic_launcher_foreground)
                .placeholder(R.drawable.ic_launcher_background)
                .into(view);
    }

    public void setUp() {
        // perform set up tasks, such as adding listeners
    }

    public void tearDown() {
        // perform tear down tasks, such as removing listeners
    }

    @Bindable
    public String getTitle() {
        return !TextUtils.isEmpty(article.getTitle()) ? article.getTitle() : "";
    }

    @Bindable
    public String getImageUrl() {
        return !TextUtils.isEmpty(article.getUrlToImage()) ? article.getUrlToImage() : "";
    }

}
