package com.rishabh.slicenews.view;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.rishabh.slicenews.R;
import com.rishabh.slicenews.databinding.ActivityMainBinding;
import com.rishabh.slicenews.util.EndlessRecyclerOnScrollListener;
import com.rishabh.slicenews.viewmodel.DataViewModel;

public class MainActivity extends AppCompatActivity {
    private DataViewModel dataViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = getRootView();
        initRecyclerView(view);
    }

    private View getRootView() {
        return getBinding().getRoot();
    }

    private void initRecyclerView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.data_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore() {
                dataViewModel.setUp();
            }
        });
    }

    private ViewDataBinding getBinding() {
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        dataViewModel = new DataViewModel();
        binding.setViewModel(dataViewModel);
        return binding;
    }

    @Override
    protected void onPause() {
        super.onPause();
        dataViewModel.tearDown();
    }

    @Override
    protected void onResume() {
        super.onResume();
        dataViewModel.setUp();
    }
}
