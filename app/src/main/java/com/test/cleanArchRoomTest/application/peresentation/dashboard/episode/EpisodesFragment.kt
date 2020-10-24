package com.test.cleanArchRoomTest.application.peresentation.dashboard.episode

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.cleanArchRoomTest.R
import com.test.cleanArchRoomTest.databinding.EpisodesFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.disposables.CompositeDisposable

@AndroidEntryPoint
class EpisodesFragment : Fragment(), ShowDetail {

    private val disposables = CompositeDisposable()
    private lateinit var binding: EpisodesFragmentBinding
    private val viewModel: EpisodesViewModel by viewModels()
    private lateinit var episodeAdapter: EpisodeAdapter
    private var episodes = ArrayList<String>()
    private val id by lazy {
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
        viewModel.getEpisodes(id)
        observeData()
    }

    private fun observeData() {
        viewModel.episodes.observe(viewLifecycleOwner, Observer {
            episodes.addAll(it[0].split(",", "[", "]"))
            episodes.removeAt(0)
            episodes.removeAt(episodes.size - 1)
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
        //TODO go to detail list
    }
}