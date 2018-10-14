package com.betterup.codingexercise.managers;

import android.content.Context;
import android.widget.ImageView;

import com.betterup.codingexercise.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * This class is the concrete implementation of {@link ImageCacheManager}.
 */
@Singleton
public class ImageCacheManagerImpl implements ImageCacheManager {
    private final Glide glide;

    /**
     * CONSTRUCTOR
     *
     * @param context is the {@link android.app.Application} context.
     */
    @Inject
    public ImageCacheManagerImpl(final Context context) {
        glide = Glide.get(context);
    }

    /**
     * Returns if the {@link ImageCacheManager} has been initialized.
     *
     * @return true if initialized, false otherwise.
     */
    @Override
    public boolean isInitialized() {
        return (glide != null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void loadIntoImageView(final ImageView imageView, final String url) {
        if (imageView == null) {
            return;
        }

        RequestOptions requestOptions = new RequestOptions();
        requestOptions
                .error(R.drawable.ic_account_circle_black)
                .fitCenter();

        glide.with(glide.getContext())
                .load(url)
                .apply(requestOptions)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView);
    }

    /**
     * This will remove all images from memory cache and wipe the disk cache.
     */
    @Override
    public void clearAllImages() {
        glide.clearMemory();
    }

    @Override
    public void clearDiskCache() {
        glide.clearDiskCache();
    }
}
