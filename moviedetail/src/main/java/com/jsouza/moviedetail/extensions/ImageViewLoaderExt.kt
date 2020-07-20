package com.jsouza.moviedetail.extensions

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.GenericTransitionOptions
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.jsouza.moviedetail.R
import com.jsouza.moviedetail.utils.Constants.Companion.IMAGE_MAX_HEIGHT_POSTER
import com.jsouza.moviedetail.utils.Constants.Companion.IMAGE_MAX_WIDTH_POSTER

private var placeHolderId: Int = R.drawable.place_holder
private var errorImageId: Int = R.drawable.error_image

fun ImageView.loadPosterImage(url: String? = null) {

    val requestBuilder = setupGlide(this)

    placeHolderId.let {
        requestBuilder
            .placeholder(placeHolderId)
            .override(IMAGE_MAX_WIDTH_POSTER, IMAGE_MAX_HEIGHT_POSTER)
            .error(errorImageId)
    }

    requestBuilder
        .load(url)
        .into(this)
}

fun ImageView.loadBackdropImage(url: String? = null) {

    val requestBuilder = setupGlide(this)

    placeHolderId.let {
        requestBuilder
            .error(errorImageId)
    }

    requestBuilder
        .load(url)
        .into(this)
}

private fun setupGlide(
    imageView: ImageView,
    onLoadCompleted: () -> Unit = {},
    onError: () -> Unit = {}
): RequestBuilder<Drawable> {

    return Glide.with(imageView)
        .`as`(Drawable::class.java)
        .transition(GenericTransitionOptions.with(R.anim.fade_in))
        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
        .listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                onError()
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                onLoadCompleted()
                return false
            }
        })
}
