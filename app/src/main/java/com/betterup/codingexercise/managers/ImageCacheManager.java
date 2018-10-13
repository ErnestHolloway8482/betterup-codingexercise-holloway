package com.betterup.codingexercise.managers;

import android.widget.ImageView;

import javax.inject.Singleton;

/**
 * This interface is used to handle all image loading and caching within the app.
 */
@Singleton
public interface ImageCacheManager {
    /**
     * Returns if the {@link ImageCacheManager} has been initialized.
     *
     * @return true if initialized, false otherwise.
     */
    boolean isInitialized();

    /**
     * This will automatically load the url image contents into the {@link ImageView}.
     *
     * @param imageView is the {@link ImageView} to load the image contents into.
     * @param url       is the url image content that the {@link ImageView} will load.
     */
    void loadIntoImageView(final ImageView imageView, final String url);

    /**
     * This will remove all images from memory cache.
     */
    void clearAllImages();

    /**
     * This will nuke the disk cache for the images.
     */
    void clearDiskCache();
}
