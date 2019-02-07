package com.gudino.clasificados_ml.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.gudino.clasificados_ml.R
import com.gudino.clasificados_ml.model.Picture
import kotlinx.android.synthetic.main.classified_detail_image.view.*

/**
 * Displays pictures at the top of detail classified detail screen
 */
class ClassifiedDetailPicturesAdapter : RecyclerView.Adapter<ClassifiedDetailPicturesAdapter.ViewHolder>() {

    companion object {
        const val BORDER_RADIUS = 16
    }

    private var pictures: List<Picture> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClassifiedDetailPicturesAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.classified_detail_image, parent, false))
    }

    override fun onBindViewHolder(holder: ClassifiedDetailPicturesAdapter.ViewHolder, position: Int) {
        holder.bind(pictures[position])
    }

    override fun getItemCount(): Int {
        return pictures.size
    }

    fun loadPictures(pictureList: List<Picture>) {
        pictures = pictureList
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(picture: Picture) = with(itemView) {
            Glide.with(this)
                    .load(picture.secure_url)
                    .apply(RequestOptions().transforms(CenterCrop(), RoundedCorners(BORDER_RADIUS)))
                    .into(iv_picture)
        }
    }
}