package com.test.cleanArchRoomTest.episode.view.episode

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.cleanArchRoomTest.R
import com.test.cleanArchRoomTest.databinding.EpisodesFragmentBinding
import com.test.cleanArchRoomTest.episode.domain.model.CharacterEpisodeCrossRef
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.disposables.CompositeDisposable

@AndroidEntryPoint
class EpisodesFragment : androidx.fragment.app.Fragment(), ShowDetail {


    private lateinit var binding: EpisodesFragmentBinding
    private lateinit var episodeAdapter: EpisodeAdapter

    private val disposables = CompositeDisposable()
    private val viewModel: EpisodesViewModel by viewModels()
    private val characterId by lazy {
        arguments?.getString("characterId", "")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.episodes_fragment, container, false)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        getEpisodes()
    }

    private fun initAdapter() {
        episodeAdapter = EpisodeAdapter(this)
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.listItem.layoutManager = layoutManager
        binding.listItem.adapter = episodeAdapter
    }

    private fun getEpisodes() {
        viewModel.getEpisodes(characterId)
        observeData()
    }

    private fun observeData() {
        viewModel.episodes.observe(viewLifecycleOwner, Observer {
            episodeAdapter.fillData(it.toMutableList())
        })
    }

    override fun onShowDetailClicked(id: String) {
        val bundle =
            bundleOf(
                "id" to CharacterEpisodeCrossRef(
                    id.toInt(),
                    characterId?.toInt() ?: 0
                )
            )
        findNavController().navigate(R.id.action_episodesFragment_to_episodeDetailFragment, bundle)
    }

    override fun onPause() {
        super.onPause()
        disposables.clear()
    }
}