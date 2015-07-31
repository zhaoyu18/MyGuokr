package com.example.wangzhaoyu.myguokr.ui.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.wangzhaoyu.myguokr.AppController;
import com.example.wangzhaoyu.myguokr.R;
import com.example.wangzhaoyu.myguokr.core.SPUtils;

import org.apache.http.impl.cookie.BasicClientCookie;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * TODO
 * @author wangzhaoyu
 */
public class LoginActivity extends AppCompatActivity {
    private static final String TAG = LoginActivity.class.getSimpleName();
    // 登录页保存Cookie
    public static final String LOGIN_URL = "https://account.guokr.com/sign_in/?display=mobile";
    public static final String SUCCESS_URL_1 = "http://m.guokr.com/";
    public static final String SUCCESS_URL_2 = "http://www.guokr.com/";
    public static final String COOKIE_TOKEN_KEY = "_32353_access_token";
    public static final String COOKIE_UKEY_KEY = "_32353_ukey";

    @InjectView(R.id.toolbar)
    Toolbar mToolbar;
    @InjectView(R.id.login_web)
    WebView mLoginWeb;

    private WebViewClient mWebClient;
    private String mStrCookie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);

        //toolbar
        mToolbar.setTitle("登录果壳");
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        //clear cookie
        CookieSyncManager.createInstance(AppController.getInstance());
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.removeAllCookie();
        cookieManager.hasCookies();
        cookieManager.removeSessionCookie();
        CookieSyncManager.getInstance().sync();

        //web view clint
        mWebClient = new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.equals(SUCCESS_URL_1) || url.equals(SUCCESS_URL_2)) {
                    // login ok
                    if (parseRawCookie(mStrCookie)) {
                        mLoginWeb.stopLoading();
                        SPUtils.getInstance().putString(SPUtils.KEYS.STRING_COOKIE, mStrCookie);
                        Toast.makeText(LoginActivity.this, "cookie: " + mStrCookie, Toast.LENGTH_SHORT)
                                .show();
                        setResult(RESULT_OK);
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "parse cookie error", Toast.LENGTH_SHORT)
                                .show();
                    }
                } else {
                    view.loadUrl(url);
                }
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                CookieManager cookieManager = CookieManager.getInstance();
                if (cookieManager != null) {
                    mStrCookie = cookieManager.getCookie(url);
                    if (LOGIN_URL.equals(url)) {
                        mLoginWeb.stopLoading();
                    }
                }
                if (parseRawCookie(mStrCookie)) {
                    mLoginWeb.stopLoading();
                    SPUtils.getInstance().putString(SPUtils.KEYS.STRING_COOKIE, mStrCookie);
                    setResult(RESULT_OK);
                    finish();
                } else {
                    super.onPageFinished(view, url);
                }
            }
        };

        //web view
        mLoginWeb.setWebViewClient(mWebClient);
        mLoginWeb.getSettings().setJavaScriptEnabled(true);
        mLoginWeb.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        mLoginWeb.loadUrl(LOGIN_URL);
    }

    private boolean parseRawCookie(String rawCookie) {
        try {
            String tmpToken = "";
            String tmpUkey = "";
            if (!TextUtils.isEmpty(rawCookie)) {
                String[] rawCookieParams = rawCookie.split(";");
                for (String rawCookieParam : rawCookieParams) {
                    String rawCookieParamNameAndValue[] = rawCookieParam.trim().split("=");
                    if (rawCookieParamNameAndValue.length != 2) {
                        continue;
                    }
                    String paramName = rawCookieParamNameAndValue[0].trim();
                    String paramValue = rawCookieParamNameAndValue[1].trim();
                    BasicClientCookie clientCookie = new BasicClientCookie(paramName, paramValue);
                    clientCookie.setDomain("guokr.com");
                    clientCookie.setPath("/");
//                    HttpFetcher.getDefaultHttpClient().getCookieStore().addCookie(clientCookie);
                    if (COOKIE_TOKEN_KEY.equals(paramName)) {
                        SPUtils.getInstance().putString(SPUtils.KEYS.ACCESS_TOKEN, paramValue);
                        tmpToken = paramValue;
                    } else if (COOKIE_UKEY_KEY.equals(paramName)) {
                        SPUtils.getInstance().putString(SPUtils.KEYS.USER_UKEY, paramValue);
                        tmpUkey = paramValue;
                    }
                }
            }
            return !(TextUtils.isEmpty(tmpUkey) || TextUtils.isEmpty(tmpToken));
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
