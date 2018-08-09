package com.rishabh.slicenews.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.rishabh.slicenews.R;

public class DetailActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        String url = getIntent().getExtras().getString("Url");

        this.webView = findViewById(R.id.webview);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        WebViewClient webViewClient = new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            public void onPageFinished(WebView view, String url) {
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            }
        };

        webView.setWebViewClient(webViewClient);

        webView.loadUrl(url);
    }
}
