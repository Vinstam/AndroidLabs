package com.alekseeva.androidlabs

import android.content.res.ColorStateList
import android.graphics.Color
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.marginRight
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso


class EarbudsAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var mEarbudsList: ArrayList<EarbudsModel> = ArrayList()

    fun setupEarbuds(list: ArrayList<EarbudsModel>) {
        mEarbudsList.clear()
        mEarbudsList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is EarbudsViewHolder) {
            holder.bind(mEarbuds = mEarbudsList[position])
        }
    }

    override fun getItemCount(): Int {
        return mEarbudsList.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.recycler_item, parent, false)
        return EarbudsViewHolder(itemView)
    }

    class EarbudsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(mEarbuds: EarbudsModel) {
            itemView.findViewById<TextView>(R.id.tv_name).text = mEarbuds.name
            itemView.findViewById<TextView>(R.id.tv_price).text = mEarbuds.price
            itemView.findViewById<TextView>(R.id.tv_brand).text = mEarbuds.brand

            val swatchSize = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 16f, itemView.resources.displayMetrics
            ).toInt()
            val colorSwatchContainer = itemView.findViewById<LinearLayout>(R.id.color_swatch_container)

            if (mEarbuds.colorSwatches.count() == 0) {
                itemView.findViewById<TextView>(R.id.tv_color_swatch).text = "Нет доступных цветов"
            }

            mEarbuds.colorSwatches.forEach { t ->
                val newSwatch = LayoutInflater.from(itemView.context).inflate(R.layout.color_swatch, null)
                newSwatch.backgroundTintList = ColorStateList.valueOf(Color.parseColor(t))
                colorSwatchContainer.addView(newSwatch, swatchSize, swatchSize)
            }
        }
    }
}