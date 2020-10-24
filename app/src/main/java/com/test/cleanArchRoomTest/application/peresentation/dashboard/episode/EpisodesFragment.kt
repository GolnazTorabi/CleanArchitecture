package com.test.cleanArchRoomTest.application.peresentation.dashboard.episode

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.test.cleanArchRoomTest.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EpisodesFragment : Fragment() {

    private val viewModel: EpisodesViewModel by viewModels()
    private val id by lazy {
        arguments?.getString("characterId", "")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.episodes_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getEpisodes()
    }

    private fun getEpisodes() {
        viewModel.getEpisodes(id)
        observeData()
    }

    private fun observeData() {
        viewModel.episodes.observe(viewLifecycleOwner, Observer {
            Log.d(TAG, "observeData: $it")
        })
    }

    override fun onStop() {
        super.onStop()
        viewModel.unbound()
    }
}