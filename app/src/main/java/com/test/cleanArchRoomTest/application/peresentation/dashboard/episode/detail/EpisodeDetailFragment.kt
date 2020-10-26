package com.test.cleanArchRoomTest.application.peresentation.dashboard.episode.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.room.util.DBUtil
import com.test.cleanArchRoomTest.R
import com.test.cleanArchRoomTest.databinding.EpisodeDetailFragmentBinding
import com.test.cleanArchRoomTest.domain.model.CharacterEpisodeCrossRef
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EpisodeDetailFragment : Fragment() {

    private lateinit var binding:EpisodeDetailFragmentBinding

    private val viewModel: EpisodeDetailViewModel by viewModels()
    private val ids by lazy {
        arguments?.getParcelable<CharacterEpisodeCrossRef>("id") as CharacterEpisodeCrossRef
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.episode_detail_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

}