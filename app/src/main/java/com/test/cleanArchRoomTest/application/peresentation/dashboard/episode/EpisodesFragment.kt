package com.test.cleanArchRoomTest.application.peresentation.dashboard.episode

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.cleanArchRoomTest.R
import com.test.cleanArchRoomTest.databinding.EpisodesFragmentBinding
import com.test.cleanArchRoomTest.domain.model.CharacterEpisodeCrossRef
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.disposables.CompositeDisposable

@AndroidEntryPoint
class EpisodesFragment : Fragment(), ShowDetail {

    private val disposables = CompositeDisposable()
    private lateinit var binding: EpisodesFragmentBinding
    private val viewModel: EpisodesViewModel by viewModels()
    private lateinit var episodeAdapter: EpisodeAdapter
    private var episodes = ArrayList<String>()
    private val characterId by lazy {
        arguments?.getString("characterId", "")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.episodes_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initAdapter()
        getEpisodes()
    }

    private fun initAdapter() {
        episodeAdapter = EpisodeAdapter(episodes, this)
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
            it.forEach { data ->
                episodes.add(data.substringAfterLast("/"))
            }
            episodeAdapter.notifyDataSetChanged()
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.unbound()
    }

    override fun onPause() {
        super.onPause()
        disposables.clear()
    }

    override fun onShowDetailClicked(id: String) {
        Log.d(TAG, "onShowDetailClicked: $id.substring(0,id.length-1).toInt()")
        val bundle =
            bundleOf("id" to CharacterEpisodeCrossRef(id.toInt(), characterId?.toInt() ?: 0))
        findNavController().navigate(R.id.action_episodesFragment_to_episodeDetailFragment, bundle)

    }
}