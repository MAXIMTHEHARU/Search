package com.android.imagesearch.feature.bookmark

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.android.imagesearch.SearchItemModel
import com.android.imagesearch.Utils.getDateFromTimestampWithFormat
import com.bumptech.glide.Glide
import com.android.imagesearch.databinding.SearchItemBinding

class BookmarkAdapter(var mContext: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    // 북마크된 아이템들을 저장하는 리스트
    var items = mutableListOf<SearchItemModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = SearchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        // 이미지 로딩 라이브러리(Glide)를 사용해 썸네일 이미지를 로드한다.
        Glide.with(mContext)
            .load(items[position].url)
            .into((holder as ItemViewHolder).iv_thum_image)

        holder.tv_title.text = items[position].title
        holder.iv_like.visibility = View.GONE // '좋아요' 아이콘을 숨김
        holder.tv_datetime.text =
            getDateFromTimestampWithFormat(
                items[position].dateTime,
                "yyyy-MM-dd'T'HH:mm:ss.SSS+09:00",
                "yyyy-MM-dd HH:mm:ss"
            )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ItemViewHolder(binding: SearchItemBinding) : RecyclerView.ViewHolder(binding.root) {
        var iv_thum_image = binding.searchImage
        var iv_like= binding.searchFillFavorite
        var tv_title= binding.searchName
        var tv_datetime= binding.searchDatetime
        var cl_item = binding.clThumbItem


        init {
            // 북마크 페이지에서는 '좋아요' 아이콘을 숨긴다.
            iv_like.visibility = View.GONE

            // 아이템 클릭 리스너 설정
            cl_item.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    items.removeAt(position)
                    notifyItemRemoved(position)
                }
            }
        }
    }

}
