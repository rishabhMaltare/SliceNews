package com.rishabh.slicenews.viewmodel;

import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.graphics.Palette;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.rishabh.slicenews.R;
import com.rishabh.slicenews.model.Article;
import com.rishabh.slicenews.view.DetailActivity;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

/**
 * Created by Rishabh on 04-08-2018.
 */

public class DataItemViewModel extends BaseObservable {

    private Article article;
    //public SingleLiveEvent navigateToDetails = new SingleLiveEvent();

    public DataItemViewModel(Article article) {
        this.article = article;
    }

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(final ImageView view, String imageUrl) {
        if (TextUtils.isEmpty(imageUrl)) {
            imageUrl = null;
        }
        Picasso.get()
                .load(imageUrl)
                .error(R.drawable.ic_launcher_foreground)
                .placeholder(R.drawable.ic_launcher_background)
                .into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        assert view != null;
                        view.setImageBitmap(bitmap);
                        Palette.from(bitmap)
                                .generate(new Palette.PaletteAsyncListener() {
                                    @Override
                                    public void onGenerated(Palette palette) {
                                        ((View) view.getParent())
                                                .setBackgroundColor(palette.getDominantColor(Color.WHITE));
                                    }
                                });
                    }

                    @Override
                    public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {
                    }
                });
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
    public String getDescription() {
        return !TextUtils.isEmpty(article.getDescription()) ? article.getDescription() : "";
    }

    @Bindable
    public String getImageUrl() {
        return !TextUtils.isEmpty(article.getUrlToImage()) ? article.getUrlToImage() : "";
    }

    public void goToDetails(View view) {
        //navigateToDetails.call();
        //Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(article.getUrl()));
        Intent intent = new Intent(view.getContext(), DetailActivity.class);
        intent.putExtra("Url", article.getUrl());
        view.getContext().startActivity(intent);
    }


}
