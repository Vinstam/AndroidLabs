package com.alekseeva.androidlabs

import android.content.res.ColorStateList
import android.graphics.Color
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class PhoneAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var mPhoneList: ArrayList<PhoneModel> = ArrayList()

    fun setupPhones(list: ArrayList<PhoneModel>) {
        mPhoneList.clear()
        mPhoneList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is PhonesViewHolder) {
            holder.bind(mPhone = mPhoneList[position])
        }
    }

    override fun getItemCount(): Int {
        return mPhoneList.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.recycler_item, parent, false)
        return PhonesViewHolder(itemView)
    }

    class PhonesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(mPhone: PhoneModel) {
            itemView.findViewById<TextView>(R.id.tv_name).text = mPhone.name
            itemView.findViewById<TextView>(R.id.tv_price).text = mPhone.price
            itemView.findViewById<TextView>(R.id.tv_brand).text = mPhone.brand

            val swatchSize = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 16f, itemView.resources.displayMetrics
            ).toInt()
            val colorSwatchContainer = itemView.findViewById<LinearLayout>(R.id.color_swatch_container)

            if (mPhone.colorSwatches.count() == 0) {
                itemView.findViewById<TextView>(R.id.tv_color_swatch).text = "Нет доступных цветов"
            }

            mPhone.colorSwatches.forEach { t ->
                val newSwatch = LayoutInflater.from(itemView.context).inflate(R.layout.color_swatch, null)
                newSwatch.backgroundTintList = ColorStateList.valueOf(Color.parseColor(t))
                colorSwatchContainer.addView(newSwatch, swatchSize, swatchSize)
            }
        }
    }
}