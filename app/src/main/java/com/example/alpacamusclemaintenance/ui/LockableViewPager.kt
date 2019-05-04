package com.example.alpacamusclemaintenance.ui

// @see: https://github.com/guardian/android-supportlibrary-extensions/blob/master/library/src/main/java/android/support/v4/view/LockableViewPager.java

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

class LockableViewPager : ViewPager {

    private var isPagingEnabled = true

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return performClick()
    }

    override fun performClick(): Boolean {
        return this.isPagingEnabled && super.performClick()
    }

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        return this.isPagingEnabled && super.onInterceptTouchEvent(event)
    }

    fun setPagingEnabled(b: Boolean) {
        this.isPagingEnabled = b
    }
}
