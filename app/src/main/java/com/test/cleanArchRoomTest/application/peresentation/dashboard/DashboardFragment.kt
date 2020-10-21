package com.test.cleanArchRoomTest.application.peresentation.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.test.cleanArchRoomTest.R
import com.test.cleanArchRoomTest.databinding.FragmentDashboardBinding
import com.test.cleanArchRoomTest.utils.ext.addTo
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.disposables.CompositeDisposable

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    private val dashboardViewModel: DashboardViewModel by viewModels()

    private lateinit var binding: FragmentDashboardBinding

    private val disposables = CompositeDisposable()

    private lateinit var data: String

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
        dashboardViewModel.bound()
        observeData()
        observeEpisodes()
        // observeCharacterEpisode()
    }

  /*  private fun observeCharacterEpisode() {
        dashboardViewModel.episodesCharactersList.observe(viewLifecycleOwner, Observer {
            binding.textDashboard.text = data.plus(it[0].character.name + it[0].episodes[0].name)
        })
    }*/

    private fun observeEpisodes() {
        dashboardViewModel.episodesList.observe(viewLifecycleOwner, Observer {
            binding.textDashboard.text = data.plus(it[0].name)
        })
    }

    private fun observeData() {
        dashboardViewModel.charsList.observe(viewLifecycleOwner, Observer {
            data = it[0].name.toString()
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

