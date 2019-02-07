package com.gudino.clasificados_ml.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.ViewGroup
import com.gudino.clasificados_ml.ClassifiedsViewModel
import com.gudino.clasificados_ml.R
import kotlinx.android.synthetic.main.classified_detail.*

/**
 * Displays detail of a classified
 */
class ClassifiedDetailFragment : Fragment() {

    private lateinit var viewModel: ClassifiedsViewModel
    private lateinit var classifiedDetailPicturesAdapter: ClassifiedDetailPicturesAdapter
    private lateinit var classifiedDetailAdapter: ClassifiedDetailAdapter

    companion object {
        private const val CLASSIFIED_ID = "CLASSIFIED_ID"

        fun newInstance(classifiedId: String) = ClassifiedDetailFragment().apply {
            arguments = Bundle().apply {
                putString(CLASSIFIED_ID, classifiedId)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = activity?.run {
            ViewModelProviders.of(this).get(ClassifiedsViewModel::class.java)
        } ?: throw Exception("Invalid Activity")

        viewModel.loadClassifiedDetail(arguments?.getString(CLASSIFIED_ID)!!)

        viewModel.classifiedDetail.observe(this, Observer {
            it?.let {
                classifiedDetailPicturesAdapter.loadPictures(it.pictures)
                classifiedDetailAdapter.loadDetail(it)
            }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
            inflater.inflate(R.layout.classified_detail, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        classifiedDetailPicturesAdapter = ClassifiedDetailPicturesAdapter()
        rv_pics.adapter = classifiedDetailPicturesAdapter

        classifiedDetailAdapter = ClassifiedDetailAdapter()
        rv_detail.adapter = classifiedDetailAdapter
    }
}