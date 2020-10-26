package com.test.cleanArchRoomTest.application.peresentation.dashboard.episode.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.test.cleanArchRoomTest.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EpisodeDetailFragment : Fragment() {

    private val viewModel: EpisodeDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.episode_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

}