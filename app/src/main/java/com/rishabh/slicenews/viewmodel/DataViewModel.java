package com.rishabh.slicenews.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.support.annotation.NonNull;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.rishabh.slicenews.BR;
import com.rishabh.slicenews.adapter.DataAdapter;
import com.rishabh.slicenews.model.Article;
import com.rishabh.slicenews.model.News;
import com.rishabh.slicenews.util.IntroFit;
import com.rishabh.slicenews.util.RetroUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Rishabh on 04-08-2018.
 * <p>
 * The use of DataViewModel is
 */

public class DataViewModel extends BaseObservable {
    private static final String TAG = "DataViewModel";
    private DataAdapter adapter;
    private List<Article> data;
    private List<Article> allData = new ArrayList<>();
    private final SearchView.OnQueryTextListener onQueryTextListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            Log.d("Test", "query = " + query); // <- query is empty.
            filterNewsBasedOnTitle(query);
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            Log.d(TAG, "onQueryTextChange: text = " + newText);
            filterNewsBasedOnTitle(newText);
            return false;
        }
    };
    private int page;
    private boolean latestFirst;
    private String query;
    private String queryHint;

    public DataViewModel() {
        data = new ArrayList<>();
        adapter = new DataAdapter();
    }

    @BindingAdapter("query")
    public static void setQuery(SearchView searchView, String queryText) {
        searchView.setQuery(queryText, false);
    }

    @BindingAdapter("queryTextListener")
    public static void setOnQueryTextListener(SearchView searchView, SearchView.OnQueryTextListener
            listener) {
        searchView.setOnQueryTextListener(listener);
    }

    private void filterNewsBasedOnTitle(String text) {
        if (TextUtils.isEmpty(text)) {
            resetFilter();
        } else {
            List<Article> articles = new ArrayList<>();
            for (int i = 0; i < data.size(); i++) {
                if (data.get(i).getTitle().toLowerCase().contains(text.toLowerCase())) {
                    articles.add(data.get(i));
                }
            }
            data.clear();
            data.addAll(articles);
            notifyPropertyChanged(BR.data);
        }
    }

    private void resetFilter() {
        data.clear();
        data.addAll(allData);
        notifyPropertyChanged(BR.data);
    }

    public void setUp() {
        page++;
        populateData(page);
    }

    private void populateData(int page) {
        Map<String, String> map = getStringStringMap(page);
        Log.d(TAG, "populateData: page = " + page);
        Call<News> call = RetroUtil.getRetroService(IntroFit.class).getResponse(map);
        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(@NonNull Call<News> call, @NonNull retrofit2.Response<News> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: response = " + response.body());
                    data.addAll(response.body().getArticles());
                    notifyPropertyChanged(BR.data);
                }
            }

            @Override
            public void onFailure(@NonNull Call<News> call, @NonNull Throwable t) {
                // TODO: 05-08-2018 Handle failure!!
                Log.d(TAG, "onFailure: ");
            }
        });
    }

    @NonNull
    private Map<String, String> getStringStringMap(int page) {
        Map<String, String> map = new HashMap<>();
        map.put("country", "in");
        map.put("pageSize", "5");
        map.put("page", String.valueOf(page));
        map.put("apiKey", "3363a374df9f4660a260a187915f0937");
        return map;
    }

    public void tearDown() {
    }

    @Bindable
    public List<Article> getData() {
        updateAllData();
        return this.data;
    }

    private void updateAllData() {
        for (int i = 0; i < data.size(); i++) {
            if (!allData.contains(data.get(i))) {
                allData.add(data.get(i));
            }
        }
    }

    public void setData(List<Article> data) {
        this.data = data;
    }

    @Bindable
    public DataAdapter getAdapter() {
        return this.adapter;
    }

    public void sortList(View view) {
        Collections.sort(data, new Comparator<Article>() {
            @Override
            public int compare(Article article, Article t1) {
                if (latestFirst) {
                    return article.getPublishedAt().compareTo(t1.getPublishedAt());
                } else {
                    return t1.getPublishedAt().compareTo(article.getPublishedAt());
                }
            }
        });
        latestFirst = !latestFirst;
        notifyPropertyChanged(BR.data);
    }

    @Bindable
    public String getQuery() {
        return query;
    }

    @Bindable
    public String getQueryHint() {
        return "Search Articles";
    }

    public SearchView.OnQueryTextListener getOnQueryTextListener() {
        return onQueryTextListener;
    }
}

