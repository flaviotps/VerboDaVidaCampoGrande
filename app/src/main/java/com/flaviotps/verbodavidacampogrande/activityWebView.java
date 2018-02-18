package com.flaviotps.verbodavidacampogrande;

import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;


public class activityWebView extends AppCompatActivity {

    public static final int ACTIVITY_NOTICIAS = 0;
    public static final int ACTIVITY_VERBO = 1;
    public static final int ACTIVITY_EVENTOS = 2;
    public static final int ACTIVITY_MENSAGENS = 3;
    public static final int ACTIVITY_AUDIOS = 4;
    public static final int ACTIVITY_VIDEOS = 5;
    public static WebView mWebView;
    public static boolean displayControls = false;
    public static WebView cachedWebView;
    public static boolean isPlaying = false;
    private static WebView AudioWebView;
    private ProgressBar mProgressBar;
    private int mTargetActivity = 0;
    private Menu toolbarMenu;

    public static void stopWebViewAudio() {

        if (cachedWebView == null)
            return;

        isPlaying = false;

        cachedWebView.loadUrl("javascript:(function(){ " +

                "if(typeof document.getElementsByTagName(\"Audio\")[0] !== \"undefined\") {" +
                "document.getElementsByTagName(\"Audio\")[0].stop(); }" +

                "})()");


    }

    public static void playWebViewAudio() {


        isPlaying = true;
        if (cachedWebView == null)
            return;

        cachedWebView.loadUrl("javascript:(function(){ " +

                "if(typeof document.getElementsByTagName(\"Audio\")[0] !== \"undefined\") {" +
                "document.getElementsByTagName(\"Audio\")[0].play(); }" +

                "})()");


    }

    public static void playNewWebView() {

        isPlaying = true;
        if (AudioWebView == mWebView) {
            if (cachedWebView != mWebView) {
                cachedWebView = mWebView;
            }
        }

        playWebViewAudio();


    }

    public static void RestartWebViewAudio() {


        if (cachedWebView == null)
            return;

        isPlaying = false;

        cachedWebView.loadUrl("javascript:(function(){ " +

                "if(typeof document.getElementsByTagName(\"Audio\")[0] !== \"undefined\") {" +
                "document.getElementsByTagName(\"Audio\")[0].stop();" +
                "document.getElementsByTagName(\"Audio\")[0].currentTime = 0; }" +

                "})()");


    }

    public boolean onCreateOptionsMenu(Menu menu) {

        // TODO Auto-generated method stub
        getMenuInflater().inflate(R.menu.menu, menu);
        //SubMenu subMenu = (SubMenu) menu.getItem(1).getSubMenu();
        //   subMenu.getItem(1).setVisible(false);
        toolbarMenu = menu;

        Log.i("AAA", String.valueOf((mWebView.getUrl().contains(getURL(ACTIVITY_AUDIOS)) && getURL(ACTIVITY_AUDIOS).length() > mWebView.getUrl().length())));

        if (mWebView.getUrl().contains("http://verbodavida.com/campogranderj/audios/")) {
            toolbarMenu.findItem(R.id.play_pause).setVisible(false);
            toolbarMenu.findItem(R.id.stop).setVisible(false);
            toolbarMenu.findItem(R.id.play).setVisible(true);
        } else {
            toolbarMenu.findItem(R.id.play_pause).setVisible(false);
            toolbarMenu.findItem(R.id.stop).setVisible(false);
            toolbarMenu.findItem(R.id.play).setVisible(false);
        }


        supportInvalidateOptionsMenu();
        invalidateOptionsMenu();


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {


            case R.id.play_pause:
                activityWebView.playWebViewAudio();
                return true;

            case R.id.stop:
                activityWebView.RestartWebViewAudio();
                return true;

            case R.id.play:
                activityWebView.playNewWebView();
                return true;

            default:

                return super.onOptionsItemSelected(item);
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        mTargetActivity = getIntent().getExtras().getInt("ACTIVITY_ID");
        mProgressBar = findViewById(R.id.progressBar2);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        getSupportActionBar().setTitle(Html.fromHtml("<font color='#FFFFFF'>" + getMyActivityTitle(mTargetActivity) + "</font>"));

        LoadWebView(mTargetActivity);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private String getMyActivityTitle(int Target) {
        switch (Target) {

            case ACTIVITY_NOTICIAS:
                return "Notícias";


            case ACTIVITY_VERBO:
                return "O Verbo da Vida";

            case ACTIVITY_EVENTOS:
                return "Eventos";

            case ACTIVITY_MENSAGENS:
                return "Mensagens";

            case ACTIVITY_AUDIOS:
                return "Áudios";

            case ACTIVITY_VIDEOS:
                return "Vídeos";

            default:
                return "Verbo da Vida - Campo Grande";


        }


    }

    private String getURL(int Target) {
        switch (Target) {
            case ACTIVITY_NOTICIAS:
                return "http://verbodavida.com/campogranderj/secao/noticias";


            case ACTIVITY_VERBO:
                return "http://verbodavida.com/campogranderj/igreja/o-verbo-da-vida/";

            case ACTIVITY_EVENTOS:
                return "http://verbodavida.com/campogranderj/eventos/lista/";

            case ACTIVITY_MENSAGENS:
                return "http://verbodavida.com/campogranderj/secao/mensagens/";

            case ACTIVITY_AUDIOS:
                return "http://verbodavida.com/campogranderj/secao/audios/";

            case ACTIVITY_VIDEOS:
                return "http://verbodavida.com/campogranderj/secao/videos/";

        }
        return "http://verbodavida.com/campogranderj/";
    }

    private void LoadWebView(int mTargetActivity) {

        mWebView = findViewById(R.id.webview);
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.addJavascriptInterface(new WebAppInterface(this), "Android");
        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                mWebView.setVisibility(View.INVISIBLE);
                mProgressBar.setVisibility(View.VISIBLE);
                return true;
            }


            @Override
            public void onPageFinished(final WebView wv, String url) {
                // TODO Auto-generated method stub


                wv.loadUrl("javascript:(function(){ " +


                        "if(typeof document.getElementsByClassName(\"mejs-container svg wp-audio-shortcode mejs-audio\")[0] !== \"undefined\"){ document.getElementsByClassName(\"mejs-container svg wp-audio-shortcode mejs-audio\")[0].style.display = 'none'; }" +


                        "var floatingMenu = document.getElementById(\"met_mobile_bar_bottom_button\");" +
                        "if(typeof floatingMenu !== \"undefined\"){ floatingMenu.style.display = 'none'; }" +

                        "var y = document.getElementsByClassName(\"met_content met_page_head_wrap\")[0];" +
                        "if(typeof y !== \"undefined\"){ y.style.display = 'none'; }" +

                        "var menu_institucional = document.getElementsByClassName(\"menu_institucional\")[0];" +
                        "if(typeof menu_institucional !== \"undefined\"){ menu_institucional.style.display = 'none'; }" +

                        "var menu = document.getElementsByClassName(\"met_content clearfix\")[0];" +
                        "if(typeof menu !== \"undefined\"){ menu.style.display = 'none'; }" +

                        "var z = document.getElementsByClassName(\"met_sidebar_box clearfix widget_met_post_tabs_widget\")[0];" +
                        "if(typeof z !== \"undefined\"){ z.style.display = 'none'; }" +

                        "var w = document.getElementsByClassName(\"searchform\")[0];" +
                        "if(typeof w !== \"undefined\"){ w.style.display = 'none'; }" +


                        " function showAndroidToast() {\n" +
                        "        Android.EnableAudioControls();\n" +
                        "    }" +
                        "if(typeof document.getElementsByClassName(\"mejs-container svg wp-audio-shortcode mejs-audio\")[0] !== \"undefined\"){" +
                        "showAndroidToast(); }" +

                        "})()");


                mProgressBar.setVisibility(View.GONE);

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        wv.setVisibility(View.VISIBLE);
                    }
                }, 500);


            }

        });

        mWebView.loadUrl(getURL(mTargetActivity));

    }

    public class WebAppInterface {

        Context mContext;


        WebAppInterface(Context c) {
            mContext = c;
        }

        @JavascriptInterface
        public void EnableAudioControls() {
            displayControls = true;
            AudioWebView = mWebView;
            supportInvalidateOptionsMenu();
            invalidateOptionsMenu();

        }

    }
}
