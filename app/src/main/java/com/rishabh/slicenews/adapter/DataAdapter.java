package com.rishabh.slicenews.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.rishabh.slicenews.R;
import com.rishabh.slicenews.databinding.ArticleItemBinding;
import com.rishabh.slicenews.model.Article;
import com.rishabh.slicenews.viewmodel.DataItemViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rishabh on 04-08-2018.
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataViewHolder> {
    private static final String TAG = "DataAdapter";
    private List<Article> data;

    public DataAdapter() {
        this.data = new ArrayList<>();
    }

    @Override
    public DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.article_item,
                new FrameLayout(parent.getContext()), false);
        return new DataViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DataViewHolder holder, int position) {
        Article dataModel = data.get(position);
        holder.setViewModel(new DataItemViewModel(dataModel));
    }

    @Override
    public int getItemCount() {
        return this.data.size();
    }

    @Override
    public void onViewAttachedToWindow(DataViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        holder.bind();
    }

    @Override
    public void onViewDetachedFromWindow(DataViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.unbind();
    }

    public void updateData(@Nullable List<Article> data) {
        if (data == null || data.isEmpty()) {
            this.data.clear();
        } else {
            this.data.clear();
            this.data.addAll(data);
        }
        notifyDataSetChanged();
    }

    static class DataViewHolder extends RecyclerView.ViewHolder {
        ArticleItemBinding binding;

        DataViewHolder(View itemView) {
            super(itemView);
            bind();
        }

        void bind() {
            if (binding == null) {
                binding = DataBindingUtil.bind(itemView);
            }
        }

        void unbind() {
            if (binding != null) {
                binding.unbind(); // Don't forget to unbind
            }
        }

        void setViewModel(DataItemViewModel viewModel) {
            if (binding != null) {
                binding.setViewModel(viewModel);
            }
        }
    }
}
