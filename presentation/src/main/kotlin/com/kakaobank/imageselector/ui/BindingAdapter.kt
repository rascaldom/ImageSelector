package com.kakaobank.imageselector.ui

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.paging.PagingData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import com.kakaobank.imageselector.R
import domain.model.ThumbnailModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("bind:list")
fun setItems(view: RecyclerView, items: PagingData<ThumbnailModel.Element>?) {
    CoroutineScope(Dispatchers.Main).launch {
        (view.adapter as ViewListAdapter).run {
            submitData(items ?: PagingData.empty())
        }
    }
}

@BindingAdapter("bind_image")
fun setImage(view: ImageView, url: String?) {
    Glide.with(view.context)
        .load(url)
        .placeholder(R.drawable.outline_image_black_24)
        .error(R.drawable.outline_image_not_supported_black_24)
        .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
        .into(view)
}

@BindingAdapter("bind_text_datetime")
fun setTextDatetime(view: TextView, datetime: String) {
    view.text = try {
        SimpleDateFormat("yyyy-MM-dd", Locale.KOREA).format(Date(SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").parse(datetime).time))
    } catch (e: Exception) {
        e.printStackTrace()
        datetime
    }
}