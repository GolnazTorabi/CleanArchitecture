package com.test.cleanArchRoomTest.episode.view.episode.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
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

    private val viewModel: EpisodeDetailViewModel by viewModels()
    private val ids by lazy {
        arguments?.getParcelable<CharacterEpisodeCrossRef>("id") as CharacterEpisodeCrossRef
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.episode_detail_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.title.text = getString(R.string.episode_detail, ids.episodeId)
        getEpisodeData()
    }

    private fun getEpisodeData() {
        viewModel.getEpisodeDetail(ids.episodeId.toString(), ids.characterId.toString())
        observeEpisodeDetail()
    }

    private fun observeEpisodeDetail() {
        viewModel.episodeData.observe(viewLifecycleOwner, Observer { data ->
            setEpisodeData(data)
        })
    }

    private fun setEpisodeData(data: EpisodeData?) {
        binding.name.text = getString(R.string.episode_name, data?.name)
        binding.created.text = getString(R.string.episode_created, data?.created)
        binding.episode.text = getString(R.string.episode_episode, data?.episode)
        binding.url.text = getString(R.string.episode_url, data?.url)
        binding.character.text = getString(R.string.episode_character, data?.characters)
    }

    override fun onPause() {
        super.onPause()
        disposables.clear()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.unbound()
    }
}