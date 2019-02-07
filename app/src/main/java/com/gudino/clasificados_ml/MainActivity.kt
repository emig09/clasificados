package com.gudino.clasificados_ml

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.gudino.clasificados_ml.model.Classified
import com.gudino.clasificados_ml.ui.ClassifiedDetailFragment
import com.gudino.clasificados_ml.ui.ClassifiedsListFragment

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: ClassifiedsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(ClassifiedsViewModel::class.java)

        viewModel.navigate.observe(this, Observer {
            it?.run { showClassifiedDetailFragment(it) }
        })

        if (savedInstanceState == null) {
            showClassifiedsListFragment()
        }
    }

    private fun showClassifiedsListFragment() =
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.container, ClassifiedsListFragment())
                    .commit()

    private fun showClassifiedDetailFragment(classifiedId: String) =
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container, ClassifiedDetailFragment.newInstance(classifiedId))
                    .addToBackStack(null)
                    .commit()
}