package com.wetech.aus.tennis.widget

import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import androidx.annotation.AttrRes
import androidx.appcompat.widget.AppCompatImageView
import com.wetech.aus.tennis.app.R

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/7/28 10:34
 * @Desc: TODO
 * @GitHub：https://github.com/pokerfaceCmy
 */
class TrapezoidImgView : AppCompatImageView {
    //constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int)
    //(context, attrs, defStyleAttr)
    private var mPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var mDrawable: Drawable? = null
    private var mWidth: Int = 0
    private var mHeight: Int = 0
    private var mShader: BitmapShader? = null
    private var mBitmap: Bitmap? = null
    private var mMatrix: Matrix? = null

    private var imgTop: Int = 0
    private var imgBottom: Int = 0


    constructor(context: Context) : super(context) {
        init(null, 0)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet?, @AttrRes defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(attrs, defStyleAttr)
    }


    private fun init(attrs: AttributeSet?, @AttrRes defStyleAttr: Int) {
        mPaint.style = Paint.Style.FILL
        mDrawable = drawable

        val array = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.TrapezoidImageView,
            defStyleAttr,
            0
        )

        imgTop = array.getDimensionPixelSize(R.styleable.TrapezoidImageView_imgTop, 0)
        imgBottom = array.getDimensionPixelSize(R.styleable.TrapezoidImageView_imgBottom, 0)
        array.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        mWidth = measuredWidth
        mHeight = measuredHeight
    }

    override fun onDraw(canvas: Canvas?) {
        if (mDrawable == null) return
        else {
            initBitmapShader()
            canvasTrapezoid(canvas!!)
        }
    }

    private fun initBitmapShader() {
        mShader = BitmapShader(getSrcBitmap(), Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        val scale =
            (width * 1.0f / getSrcBitmap().width).coerceAtLeast(height * 1.0f / getSrcBitmap().height)
        mMatrix = Matrix()
        mMatrix?.setScale(scale, scale)
        mShader?.setLocalMatrix(mMatrix)
        mPaint.shader = mShader
    }

    private fun canvasTrapezoid(canvas: Canvas) {
        canvas.drawPath(trapezoidPath(), mPaint)
    }

    private fun trapezoidPath(): Path {
        val path = Path()
        path.moveTo(0F, 0F)
        path.lineTo(imgTop.toFloat(), 0F)
        path.lineTo(imgTop.toFloat(), mHeight.toFloat())
        path.lineTo(imgTop.toFloat() - imgBottom.toFloat(), mHeight.toFloat())
        path.close()
        return path
    }


    private fun getSrcBitmap(): Bitmap {
        if (mDrawable is BitmapDrawable) {
            return (mDrawable as BitmapDrawable).bitmap
        }
        val bitmapWidth = mDrawable?.intrinsicWidth
        val bitmapHeight = mDrawable?.intrinsicHeight

        mBitmap = bitmapWidth?.let {
            bitmapHeight?.let { it1 ->
                Bitmap.createBitmap(
                    it,
                    it1,
                    Bitmap.Config.ARGB_8888
                )
            }
        }
        val canvasBitmap = mBitmap?.let { Canvas(it) }
        bitmapWidth?.let { bitmapHeight?.let { it1 -> mDrawable?.setBounds(0, 0, it, it1) } }
        canvasBitmap?.let { mDrawable?.draw(it) }
        return mBitmap as Bitmap
    }

}