package com.test.cleanArchRoomTest.application.peresentation.dashboard.episode.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.cleanArchRoomTest.domain.model.EpisodeData
import com.test.cleanArchRoomTest.domain.usecase.episode.GetEpisodeUseCase
import com.test.cleanArchRoomTest.domain.usecase.episode.InsertCharacterEpisodeCrossUseCase
import com.test.cleanArchRoomTest.domain.usecase.episode.InsertEpisodeUseCase
import com.test.cleanArchRoomTest.utils.ext.addTo
import com.test.cleanArchRoomTest.utils.rx.StickyAction
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class EpisodeDetailViewModel @ViewModelInject constructor(
    private val getEpisodeUseCase: GetEpisodeUseCase,
    private val insertEpisodeUseCase: InsertEpisodeUseCase,
    private val insertCharacterEpisodeCrossUseCase: InsertCharacterEpisodeCrossUseCase
) : ViewModel() {
    private val disposables = CompositeDisposable()
    val episodeData = MutableLiveData<EpisodeData>()
    val showErrorGettingChars = StickyAction<Boolean>()

    fun unbound() {
        disposables.clear()
    }

    fun getEpisodeDetail(id: String,characterId:String) {
        getEpisodeUseCase.getEpisodes(id, true)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { handleData(it,characterId) }
            .addTo(disposables)
    }

    private fun handleData(it: GetEpisodeUseCase.Result?,characterId:String) {
        when (it) {
            is GetEpisodeUseCase.Result.Success -> {
                episodeData.value = it.episode
                insertEpisode(it.episode)
                insertCharacterEpisode(it.episode.episodeId,characterId)
            }
            is GetEpisodeUseCase.Result.Failure -> showErrorGettingChars.trigger(true)
        }
    }

    private fun insertCharacterEpisode(episodeId: Int?, characterId: String) {

    }

    private fun insertEpisode(episode: EpisodeData) {

    }
}