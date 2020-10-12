package com.test.cleanArchRoomTest.application.peresentation.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.test.cleanArchRoomTest.R
import com.test.cleanArchRoomTest.databinding.FragmentDashboardBinding
import com.test.cleanArchRoomTest.application.ext.addTo
import dagger.android.support.DaggerFragment
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

class DashboardFragment : DaggerFragment() {

    @Inject
    lateinit var dashboardViewModel: DashboardViewModel
    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private lateinit var binding: FragmentDashboardBinding

    private val disposables = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dashboard, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dashboardViewModel = ViewModelProvider(this, factory).get(DashboardViewModel::class.java)
        dashboardViewModel.bound()
        observeData()
    }

    private fun observeData() {
        dashboardViewModel.charsList.observe(viewLifecycleOwner, Observer {
            binding.textDashboard.text = it[0].info?.next
        })
    }

    override fun onResume() {
        super.onResume()
        dashboardViewModel.showErrorGettingChars.observe()
            .subscribe {
                context?.let { it1 ->
                    AlertDialog.Builder(it1)
                    /* .setTitle(getString(string.error_title))
                     .setMessage(getString(string.restaurant_list_error_message))
                     .setNeutralButton(getString(string.ok)) { dialog, _ -> dialog.dismiss() }*/
                }
            }.addTo(disposables)
    }

    override fun onPause() {
        disposables.clear()
        super.onPause()
    }

    override fun onDestroy() {
        dashboardViewModel.unbound()
        super.onDestroy()
    }
}