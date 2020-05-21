package com.ironelder.withinno.component

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ironelder.withinno.R
import com.ironelder.withinno.domain.model.CrawlingDomainModel

class MainRecyclerAdapter : RecyclerView.Adapter<MainRecyclerAdapter.ItemMainRecycler>() {
    private val mItemList = arrayListOf<CrawlingDomainModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemMainRecycler {
        return ItemMainRecycler(
            LayoutInflater.from(parent.context).inflate(R.layout.item_main_recycler, parent, false)
        )
    }

    override fun getItemCount(): Int = mItemList.size

    override fun onBindViewHolder(holder: ItemMainRecycler, position: Int) {
        holder.setData(mItemList[position])
    }

    fun setData(list:List<CrawlingDomainModel>?){
        mItemList.clear()
        if(!list.isNullOrEmpty()){
            mItemList.addAll(list)
        }
        notifyDataSetChanged()
    }

    inner class ItemMainRecycler(val view: View) : RecyclerView.ViewHolder(view) {
        fun setData(model: CrawlingDomainModel) {
            Glide.with(view.context).load(model.imgUrl).into(view.findViewById(R.id.iv_poster))
            view.findViewById<TextView>(R.id.tv_title).text = model.title
        }
    }

}