package com.test.cleanArchRoomTest.episode.view.episode.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.test.cleanArchRoomTest.R
import com.test.cleanArchRoomTest.databinding.EpisodeDetailFragmentBinding
import com.test.cleanArchRoomTest.episode.domain.model.CharacterEpisodeCrossRef
import com.test.cleanArchRoomTest.episode.domain.model.EpisodeData
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.disposables.CompositeDisposable

@AndroidEntryPoint
class EpisodeDetailFragment : Fragment() {

    private val disposables = CompositeDisposable()
    private lateinit var binding: EpisodeDetailFragmentBinding
    private val args: EpisodeDetailFragmentArgs by navArgs()


    private val viewModel: EpisodeDetailViewModel by viewModels()
    private val ids by lazy {
        args.episodeId
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.episode_detail_fragment, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.title.text = getString(R.string.episode_detail, ids.episodeId)
        getEpisodeData()
    }

    private fun getEpisodeData() {
        viewModel.getEpisodeDetail(ids.episodeId.toString(), ids.characterId.toString())
    }

    override fun onPause() {
        super.onPause()
        disposables.clear()
    }

}