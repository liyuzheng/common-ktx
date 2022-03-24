package yz.l.common_library

import android.util.Log
import android.view.View

private var lastClickTime = 0L

fun clicks(delay: Long = 500L, block: () -> Unit) {
    val currTime = System.currentTimeMillis()
    if (currTime - lastClickTime > delay) {
        lastClickTime = currTime
        block()
    }else{
        Log.v("clicks", "view click repeat delay:$delay")
    }
}


inline fun <reified T : View> T.clicks(delay: Long = 500L, crossinline block: (T) -> Unit) = apply {
    triggerDelay = delay
    setOnClickListener {
        if (clickEnable()) {
            block(this)
        } else {
            Log.v("viewclicks", "view click repeat delay:$delay")
        }
    }
}

inline var <T : View>T.triggerLastTime: Long
    get() = if (getTag(R.id.triggerLastTimeKey) != null) getTag(R.id.triggerLastTimeKey) as Long else 0
    set(value) {
        setTag(R.id.triggerLastTimeKey, value)
    }

inline var <T : View> T.triggerDelay: Long
    get() = if (getTag(R.id.triggerDelayKey) != null) getTag(R.id.triggerDelayKey) as Long else -1
    set(value) {
        setTag(R.id.triggerDelayKey, value)
    }

inline fun <reified T : View> T.clickEnable(): Boolean {
    var clickable = false
    val currentClickTime = System.currentTimeMillis()
    if (currentClickTime - triggerLastTime >= triggerDelay) {
        clickable = true
    }
    triggerLastTime = currentClickTime
    return clickable
}

