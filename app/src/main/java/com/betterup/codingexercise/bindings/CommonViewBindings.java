package com.betterup.codingexercise.bindings;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.betterup.codingexercise.managers.ImageCacheManager;

import javax.inject.Inject;

/**
 * Defines a custom <strong>app:command</strong> attribute and binds the appropriate View event handlers to it.
 * This attribute can be used to bind a View's event (i.e. click) to an arbitrary method.
 * <p>
 * Example:
 * <code><Button app:command="@{viewModel.exampleCommand}"/></code>
 * <p>
 */
public class CommonViewBindings {
    @Inject
    static ImageCacheManager imageCacheManager;

    /**
     * Binds <c>view</c>'s {@link View#setOnClickListener(View.OnClickListener)} to <strong>command</strong>.
     *
     * @param view     The {@link View} that'll invoke the command.
     * @param runnable The {@link Runnable} action to be executed.
     */
    @BindingAdapter("app:command")
    public static void bindOnClick(final View view, final Runnable runnable) {
        view.setOnClickListener(v -> executeRunnable(runnable));
    }

    /**
     * Binds <c>view</c>'s {@link View#setOnClickListener(View.OnClickListener)} to <strong>command</strong>.
     *
     * @param view     The {@link android.support.v7.widget.Toolbar} that'll invoke the command.
     * @param runnable The {@link Runnable} action to be executed.
     */
    @BindingAdapter("app:navigationOnClick")
    public static void bindOnToolbarNavigationClick(final android.support.v7.widget.Toolbar view, final Runnable runnable) {
        view.setNavigationOnClickListener(v -> executeRunnable(runnable));
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

    /**
     * Convenience method for executing {@link Runnable}.
     *
     * @param runnable is a {@link Runnable}
     */
    private static void executeRunnable(final Runnable runnable) {
        if (runnable != null) {
            runnable.run();
        }
    }
}
