package com.android.imagesearch

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.android.imagesearch.MainActivity
import com.android.imagesearch.SearchItemModel
import com.android.imagesearch.Utils.getDateFromTimestampWithFormat
import com.android.imagesearch.databinding.SearchItemBinding
import com.bumptech.glide.Glide

class SearchAdapter(private val mContext: Context) : RecyclerView.Adapter<SearchAdapter.ItemViewHolder>() {

    var items = ArrayList<SearchItemModel>()

    fun clearItem() {
        items.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = SearchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem = items[position]

        Glide.with(mContext)
            .load(currentItem.url)
            .into(holder.iv_thum_image)

        holder.iv_like.visibility = if (currentItem.isLike) View.VISIBLE else View.INVISIBLE
        holder.tv_title.text = currentItem.title
        holder.tv_datetime.text = getDateFromTimestampWithFormat(
            currentItem.dateTime,
            "yyyy-MM-dd'T'HH:mm:ss.SSS+09:00",
            "yyyy-MM-dd HH:mm:ss"
        )
    }

    override fun getItemCount() = items.size

    inner class ItemViewHolder(binding: SearchItemBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        var iv_thum_image: ImageView = binding.searchImage
        var iv_like: ImageView = binding.searchFillFavorite
        var tv_title: TextView = binding.searchName
        var tv_datetime: TextView = binding.searchDatetime
        var cl_item = binding.clThumbItem



        init {
            iv_like.visibility = View.GONE
            iv_thum_image.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            val position = adapterPosition.takeIf { it != RecyclerView.NO_POSITION } ?: return
            val item = items[position]

            item.isLike = !item.isLike

            if (item.isLike) {
                (mContext as MainActivity).addLikedItem(item)
            } else {
                (mContext as MainActivity).removeLikedItem(item)
            }

            notifyItemChanged(position)
        }
    }
}
