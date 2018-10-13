package com.betterup.codingexercise.bindings;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.underarmour.android.mmf.managers.IErrorDisplayManager;
import com.underarmour.android.mmf.managers.ImageCacheManager;

/**
 * Defines a custom <strong>app:command</strong> attribute and binds the appropriate View event handlers to it.
 * This attribute can be used to bind a View's event (i.e. click) to an arbitrary method.
 * <p>
 * Example:
 * <code><Button app:command="@{viewModel.exampleCommand}"/></code>
 * <p>
 */
public class CommonViewBindings {
    private static ImageCacheManager imageCacheManager;
    private static IErrorDisplayManager errorDisplayManager;

    public static void setImageCacheManager(final ImageCacheManager manager) {
        if (imageCacheManager == null) {
            imageCacheManager = manager;
        }
    }

    public static void setErrorDisplayManager(final IErrorDisplayManager manager) {
        if (errorDisplayManager == null) {
            errorDisplayManager = manager;
        }
    }

    public static void clearManagerResources() {
        if (imageCacheManager != null) {
            imageCacheManager = null;
        }

        if (errorDisplayManager != null) {
            errorDisplayManager = null;
        }
    }

    /**
     * Binds <c>view</c>'s {@link View#setOnClickListener(View.OnClickListener)} to <strong>command</strong>.
     *
     * @param view     The {@link View} that'll invoke the command.
     * @param runnable The {@link Runnable} action to be executed.
     */
    @BindingAdapter("app:command")
    public static void bindOnClick(final View view, final Runnable runnable) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != runnable) {
                    runnable.run();
                }
            }
        });
    }

    /**
     * Binds <c>view</c>'s {@link View#setOnClickListener(View.OnClickListener)} to <strong>command</strong>.
     *
     * @param view     The {@link android.support.v7.widget.Toolbar} that'll invoke the command.
     * @param runnable The {@link Runnable} action to be executed.
     */
    @BindingAdapter("app:navigationOnClick")
    public static void bindOnToolbarNavigationClick(final android.support.v7.widget.Toolbar view, final Runnable runnable) {
        view.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if (null != runnable) {
                    runnable.run();
                }
            }
        });
    }

    /**
     * Binds <c>{@link WebView}</c>'s to <strong>command</strong>.
     * This allows the user to specify a url that will automatically be loaded into the {@link WebView}.
     *
     * @param view The {@link WebView} that'll invoke the command.
     * @param url  The url HTML content that the {@link WebView} will load.
     */
    @BindingAdapter("app:url")
    public static void bindWebViewLoad(final WebView view, final String url) {
        if (url == null) {
            return;
        }

        //Displays the loading spinner while the web page loads.
        errorDisplayManager.displayProgressDialog("Loading Article Details...");

        WebViewClient client = new WebViewClient() {
            @Override
            public void onPageFinished(final WebView view, final String url) {
                super.onPageFinished(view, url);

                //Dismisses the loading spinner after the web page loads.
                errorDisplayManager.dismissProgressDialog();
            }
        };

        view.setWebChromeClient(new WebChromeClient());
        view.setWebViewClient(client);
        view.getSettings().setJavaScriptEnabled(true);
        view.getSettings().setDomStorageEnabled(true);
        view.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        view.getSettings().setUserAgentString("Mozilla/5.0 (iPhone; U; CPU like Mac OS X; en) AppleWebKit/420+ (KHTML, like Gecko) Version/3.0 Mobile/1A543a Safari/419.3");
        view.loadUrl(url);
    }

    /**
     * Binds <c>{@link ImageView}</c>'s to <strong>command</strong>.
     * This allows the user to specify a url that will automatically be loaded into the {@link ImageView}.
     *
     * @param view The {@link ImageView} that'll invoke the command.
     * @param url  The url image content that the {@link ImageView} will load.
     */
    @BindingAdapter("app:imageUrl")
    public static void bindLoadImage(final ImageView view, final String url) {
        if (imageCacheManager == null) {
            return;
        }

        imageCacheManager.loadIntoImageView(view, url);
    }

    /**
     * Binds <c>{@link RecyclerView}</c>'s to <strong>command</strong>.
     * This allows the user to specify a layout manager for a {@link RecyclerView}
     *
     * @param view          The {@link RecyclerView} that'll invoke the command.
     * @param layoutManager The layout manager that the {@link RecyclerView} will use.
     */
    @BindingAdapter("app:layoutManager")
    public static void bindLoadImage(final RecyclerView view, final RecyclerView.LayoutManager layoutManager) {
        view.setLayoutManager(layoutManager);
    }
}
