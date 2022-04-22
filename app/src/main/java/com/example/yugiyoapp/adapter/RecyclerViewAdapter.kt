package com.example.yugiyoapp.adapter

import android.annotation.SuppressLint
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.yugiyoapp.R
import com.example.yugiyoapp.model.Restoran


class RecyclerViewAdapter(private val dataSet: List<Restoran>, val listener: OnAdapterListener) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val viewItem: ConstraintLayout
        val titleText: TextView
        val imageRestoran: ImageView
        val ratingText: TextView
        val ratingCount: TextView
        val timeText: TextView
        val distanceText: TextView
        val shippingText: TextView
        val menuText: TextView
        val shippingDiscount: TextView

        init {
            viewItem = view.findViewById(R.id.contraint_item_layout)
            titleText = view.findViewById(R.id.title_text)
            ratingText = view.findViewById(R.id.rating_text)
            ratingCount = view.findViewById(R.id.rating_count)
            imageRestoran = view.findViewById(R.id.img_item)
            timeText = view.findViewById(R.id.time_text)
            distanceText = view.findViewById(R.id.distance_text)
            shippingText = view.findViewById(R.id.shipping_text)
            menuText = view.findViewById(R.id.menu_text)
            shippingDiscount = view.findViewById(R.id.shipping_discount)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_restoran_list, viewGroup, false)

        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.imageRestoran.setImageResource(dataSet[position].imageResto)
        viewHolder.titleText.text = dataSet[position].title
        viewHolder.ratingText.text = String.format("%.1f", dataSet[position].rating)
        viewHolder.ratingCount.text = "("+dataSet[position].ratingCount.toString()+")"
        viewHolder.timeText.text = dataSet[position].time
        viewHolder.distanceText.text = " . "+dataSet[position].distance
        viewHolder.shippingText.text = dataSet[position].shippingPrice
        viewHolder.menuText.text = dataSet[position].menu.joinToString(", ")
        viewHolder.shippingDiscount.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG)
        viewHolder.viewItem.setOnClickListener {
            listener.onClick( dataSet[position] )
        }
    }

    override fun getItemCount() = dataSet.size

    interface OnAdapterListener {
        fun onClick(result: Restoran)
    }
}
