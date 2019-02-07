package com.gudino.clasificados_ml.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.gudino.clasificados_ml.R
import com.gudino.clasificados_ml.model.Classified
import com.gudino.clasificados_ml.model.UIItem
import com.gudino.clasificados_ml.model.UIItem.Companion.BUTTONS_TYPE
import com.gudino.clasificados_ml.model.UIItem.Companion.PRICE_TYPE
import com.gudino.clasificados_ml.model.UIItem.Companion.TEXT_TYPE
import com.gudino.clasificados_ml.model.UIItem.Companion.TITLE_TYPE
import kotlinx.android.synthetic.main.classified_detail_item_buttons.view.*
import kotlinx.android.synthetic.main.classified_detail_item_price.view.*
import kotlinx.android.synthetic.main.classified_detail_item_text.view.*
import kotlinx.android.synthetic.main.classified_detail_item_title.view.*

/**
 * Main adapter for the detail list
 */
class ClassifiedDetailAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: ArrayList<UIItem> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TEXT_TYPE -> TextViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.classified_detail_item_text, parent, false))
            BUTTONS_TYPE -> ButtonsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.classified_detail_item_buttons, parent, false))
            TITLE_TYPE -> TitleViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.classified_detail_item_title, parent, false))
            PRICE_TYPE -> PriceViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.classified_detail_item_price, parent, false))
            else -> throw IllegalArgumentException("Cannot render other type!")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (items[position].type) {
            TEXT_TYPE -> (holder as TextViewHolder).bind(items[position].text)
            BUTTONS_TYPE -> (holder as ButtonsViewHolder).bind()
            TITLE_TYPE -> (holder as TitleViewHolder).bind(items[position].text)
            PRICE_TYPE -> (holder as PriceViewHolder).bind(items[position].text)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position].type) {
            0 -> TEXT_TYPE
            1 -> BUTTONS_TYPE
            2 -> TITLE_TYPE
            3 -> PRICE_TYPE
            else -> -1
        }
    }

    override fun getItemCount() = items.size

    fun loadDetail(classified: Classified) {
        items.clear()
        items.add(UIItem(TITLE_TYPE, classified.title.plus(" *${classified.condition}*")))
        items.add(UIItem(PRICE_TYPE, classified.currency_id.plus(classified.price)))
        items.add(UIItem(BUTTONS_TYPE))
        notifyDataSetChanged()
    }

    inner class TextViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(text: String?) = with(itemView) {
            tv_text.text = text
        }
    }

    inner class ButtonsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind() = with(itemView) {
            bt_buy.setOnClickListener { Toast.makeText(itemView.context, "Buy!", Toast.LENGTH_LONG).show() }
            bt_add_to_cart.setOnClickListener { Toast.makeText(itemView.context, "Added to cart!", Toast.LENGTH_LONG).show() }
        }
    }

    inner class TitleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(title: String?) = with(itemView) {
            tv_title.text = title
        }
    }

    inner class PriceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(price: String?) = with(itemView) {
            tv_price.text = price
        }
    }
}