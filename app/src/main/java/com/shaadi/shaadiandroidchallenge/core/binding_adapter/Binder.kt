/*
package com.shaadi.shaadiandroidchallenge.core.binding_adapter

import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView

class Binder {
    companion object {
        @BindingAdapter("setLoader")
        @JvmStatic
        fun bindProgressLoader(frameLayout: FrameLayout, progressModel: ProgressModel) {
            var overlay = frameLayout.findViewById<View>(R.id.llProgress)

            if (overlay == null) {
                overlay = LayoutInflater.from(frameLayout.context)
                    .inflate(R.layout.fx_full_progress, null)
                frameLayout.addView(overlay)
            }

            //TODO find alternative if exists

            val tvProgressMessage = overlay.findViewById<TextView>(R.id.tvProgressMessage)

            if (tvProgressMessage != null) {
                tvProgressMessage.text = progressModel.message
            }

            overlay.visibility = if (progressModel.doLoading) View.VISIBLE else View.GONE
        }
    }
}*/
