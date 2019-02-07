package com.gudino.clasificados_ml.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.gudino.clasificados_ml.ClassifiedsViewModel
import com.gudino.clasificados_ml.R
import kotlinx.android.synthetic.main.classifieds_list_fragment.*

/**
 * Displays list of classifieds
 */
class ClassifiedsListFragment : Fragment(), ClassifiedsListAdapter.Action {

    companion object {
        const val ITEMS_SEARCHED = "ITEMS_SEARCHED"
    }

    private lateinit var viewModel: ClassifiedsViewModel
    private lateinit var adapter: ClassifiedsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        viewModel = activity?.run {
            ViewModelProviders.of(this).get(ClassifiedsViewModel::class.java)
        } ?: throw Exception("Invalid Activity")

        viewModel.classifieds.observe(this, Observer {
            it?.let { adapter.loadClassifieds(it) }
        })

        viewModel.errors.observe(this, Observer {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
            inflater.inflate(R.layout.classifieds_list_fragment, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = ClassifiedsListAdapter(this)
        classifieds_list.layoutManager = LinearLayoutManager(activity)
        classifieds_list.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        classifieds_list.adapter = adapter

        if (savedInstanceState != null) {
            adapter.loadClassifieds(savedInstanceState.getParcelableArrayList(ITEMS_SEARCHED))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        val searchMenu = menu.findItem(R.id.action_search)
        val searchView = searchMenu.actionView as SearchView
        searchView.queryHint = resources.getString(R.string.search)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    closeKeyboard()
                    viewModel.getClassifiedsByQuery(it)
                }
                return true
            }

            override fun onQueryTextChange(query: String?) = false
        })

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(ITEMS_SEARCHED, adapter.getClassifieds())
    }

    private fun closeKeyboard() {
        val imm = activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view!!.windowToken, 0)
    }

    override fun tap(classifiedId: String) {
        viewModel.navigate(classifiedId)
    }
}