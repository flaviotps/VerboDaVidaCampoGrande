package com.flaviotps.verbodavidacampogrande;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class activityNoticias extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticias);

        LoadWebView();
    }

    private void LoadWebView(){

        WebView wv = findViewById(R.id.webview);
        wv.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.setWebChromeClient(new WebChromeClient());

        wv.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);

                return true;
            }



            @Override
            public void onPageFinished(WebView wv, String url) {
                // TODO Auto-generated method stub

                wv.loadUrl("javascript:(function(){ "+
                        "document.body.innerHTML = document.getElementsByClassName(\"met_page_wrapper\")[0].innerHTML;"+
                        "document.getElementsByClassName(\"met_content met_page_head_wrap\")[0].style.display = 'none'"+


                        "})()");

                wv.setVisibility(View.VISIBLE);

            }

        });
        // Load the webpage
        wv.loadUrl("http://verbodavida.com/campogranderj/secao/noticias/");

    }
}
