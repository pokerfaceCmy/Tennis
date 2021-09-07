package com.wetech.aus.tennis.app.domain.profile

import android.content.Context
import android.widget.ImageView
import coil.load
import com.luck.picture.lib.engine.ImageEngine
import com.luck.picture.lib.listener.OnImageCompleteCallback
import com.luck.picture.lib.widget.longimage.SubsamplingScaleImageView

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/8/31 15:27
 * @Desc: TODO
 * @GitHub：https://github.com/pokerfaceCmy
 */
object ColiEngine : ImageEngine {
    override fun loadImage(context: Context, url: String, imageView: ImageView) {
        imageView.load(url)
    }

    override fun loadImage(
        context: Context,
        url: String,
        imageView: ImageView,
        longImageView: SubsamplingScaleImageView?,
        callback: OnImageCompleteCallback?
    ) {
        imageView.load(url)
    }

    override fun loadImage(
        context: Context,
        url: String,
        imageView: ImageView,
        longImageView: SubsamplingScaleImageView?
    ) {
        imageView.load(url)
    }

    override fun loadFolderImage(context: Context, url: String, imageView: ImageView) {
        imageView.load(url)
    }

    override fun loadAsGifImage(context: Context, url: String, imageView: ImageView) {
        imageView.load(url)
    }

    override fun loadGridImage(context: Context, url: String, imageView: ImageView) {
        imageView.load(url)
    }
}