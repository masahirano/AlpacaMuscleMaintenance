package com.example.alpacamusclemaintenance

import android.content.Context
//import android.support.design.internal.BottomNavigationItemView
//import android.support.design.internal.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.util.AttributeSet
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView

// @see http://rozkey.hatenablog.com/entry/2018/02/13/070000
class CustomBottomNavigationView : BottomNavigationView {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        disableShiftMode()
    }

    private fun disableShiftMode() {
        val menuView = getBottomMenuView()!!
        try {
            menuView.javaClass.getDeclaredField("mShiftingMode").run {
                isAccessible = true
                setBoolean(menuView, false)
                isAccessible = false
            }
            for (i in 0 until menuView.childCount) {
                val item = menuView.getChildAt(i) as BottomNavigationItemView
//                item.setShiftingMode(false)
//                item.setChecked(item.itemData.isChecked)
            }
        } catch (e: NoSuchFieldException) {
            Log.e("BNVHelper", "Unable to get shift mode field", e)
        } catch (e: IllegalAccessException) {
            Log.e("BNVHelper", "Unable to change value of shift mode", e)
        }
    }

    private fun getBottomMenuView(): BottomNavigationMenuView? {
        var menuView: Any? = null
        try {
            val field = BottomNavigationView::class.java.getDeclaredField("mMenuView")
            field.isAccessible = true
            menuView = field.get(this)
        } catch (e: NoSuchFieldException) {
            Log.e("FieldError", e.message)
        } catch (e: IllegalAccessException) {
            Log.e("IllegalAccessError", e.message)
        }
        return menuView as BottomNavigationMenuView?
    }
}