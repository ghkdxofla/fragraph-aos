package com.depromeet.fragraph.core.binding

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.depromeet.fragraph.base.GlideApp
import com.depromeet.fragraph.base.ui.IRecyclerViewAdapter
import com.depromeet.fragraph.core.ext.FRAGRAPH_HISTORY_FORMAT
import com.depromeet.fragraph.core.ext.JUST_DAY
import com.depromeet.fragraph.core.ext.enums.backgroundResourceId
import com.depromeet.fragraph.core.ext.enums.toNormal
import com.depromeet.fragraph.core.ext.miliSecondsToStringFormat
import com.depromeet.fragraph.domain.model.enums.IncenseTitle

@BindingAdapter("bind_items")
fun <T> RecyclerView.setItems(items: List<T>) {
    (adapter as IRecyclerViewAdapter<T>).setItems(items)
}

@BindingAdapter("image_from_url")
fun ImageView.bindImageFromUrl(imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        GlideApp.with(this.context)
            .load(imageUrl)
            .placeholder(ColorDrawable(Color.GRAY))
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(this)
    }
}

@BindingAdapter("bind_total_value")
fun ProgressBar.bindTotalValue(value: Int) {
    this.max = value
}

@BindingAdapter("bind_progress_value")
fun ProgressBar.bindProgressValue(value: Int) {
    this.progress = value
}

@BindingAdapter("bind_incense_title_text_normal")
fun TextView.bindIncenseTitleTextNormal(value: IncenseTitle) {
    this.text = value.toNormal()
}

@BindingAdapter("bind_history_date")
fun TextView.bindHistoryDate(value: Long) {
    this.text = "${value.miliSecondsToStringFormat(FRAGRAPH_HISTORY_FORMAT)}. ${
        value.miliSecondsToStringFormat(JUST_DAY)
    }요일"
}

@BindingAdapter("bind_history_background")
fun ImageView.bindHistoryBackground(value: IncenseTitle) {
    GlideApp.with(this.context)
        .load(value.backgroundResourceId())
        .placeholder(ColorDrawable(Color.GRAY))
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)
}

