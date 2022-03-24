package yz.l.common_library

import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

inline fun <reified T : ViewDataBinding> AppCompatActivity.binding(
    @LayoutRes resId: Int
): Lazy<T> = lazy { DataBindingUtil.setContentView<T>(this, resId) }