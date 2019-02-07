package com.gudino.clasificados_ml.ui

import android.os.Parcelable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.gudino.clasificados_ml.R
import com.gudino.clasificados_ml.model.Classified
import kotlinx.android.synthetic.main.classified_item.view.*
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions

/**
 * Adapter to display list of classifieds
 */
class ClassifiedsListAdapter(private val listener: Action) : RecyclerView.Adapter<ClassifiedsListAdapter.ViewHolder>() {

    companion object {
        const val BORDER_RADIUS = 16
    }

    interface Action {
        fun tap(classifiedId: String)
    }

    private var classifiedList: List<Classified> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClassifiedsListAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.classified_item, parent, false))
    }

    override fun onBindViewHolder(holder: ClassifiedsListAdapter.ViewHolder, position: Int) {
        holder.bind(classifiedList[position], listener)
    }

    override fun getItemCount() = classifiedList.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(classified: Classified, listener: Action) = with(itemView) {
            Glide.with(itemView.context)
                    .load(classified.thumbnail)
                    .apply(RequestOptions().transforms(CenterCrop(), RoundedCorners(BORDER_RADIUS)))
                    .into(iv_thumbnail)
            tv_title.text = classified.title
            tv_price.text = classified.currency_id.plus(classified.price)
            setOnClickListener {
                listener.tap(classified.id)
            }
        }
    }

    fun loadClassifieds(classifieds: List<Classified>) {
        classifiedList = classifieds
        notifyDataSetChanged()
    }

    fun getClassifieds(): java.util.ArrayList<out Parcelable>? {
        return ArrayList(classifiedList)
    }
}