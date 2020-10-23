package com.test.cleanArchRoomTest.application.peresentation.dashboard

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.cleanArchRoomTest.domain.model.CharactersData
import com.test.cleanArchRoomTest.domain.model.CharactersWithEpisode
import com.test.cleanArchRoomTest.domain.model.EpisodeData
import com.test.cleanArchRoomTest.domain.usecase.GetCharWithEpisodeUseCase
import com.test.cleanArchRoomTest.domain.usecase.GetCharactersUseCase
import com.test.cleanArchRoomTest.domain.usecase.GetEpisodesUseCase
import com.test.cleanArchRoomTest.utils.ext.addTo
import com.test.cleanArchRoomTest.utils.rx.StickyAction
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class DashboardViewModel @ViewModelInject constructor(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val getEpisodesUseCase: GetEpisodesUseCase,
    private val getCharacterEpisodesUseCase: GetCharWithEpisodeUseCase

) :
    ViewModel() {

    private val disposables = CompositeDisposable()
    val progressVisible = MutableLiveData<Boolean>()
    val charsList = MutableLiveData<List<CharactersData>>()
    val episodesList = MutableLiveData<List<EpisodeData>>()
    val episodesCharactersList = MutableLiveData<List<CharactersWithEpisode>>()
    val showErrorGettingChars = StickyAction<Boolean>()

    fun bound() {
        getCharactersUseCase.getCharacters()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { handleResult(it) }
            .addTo(disposables)

    }

    private fun handleResult(result: GetCharactersUseCase.Result) {
        when (result) {
            is GetCharactersUseCase.Result.Loading -> progressVisible.value = true
            is GetCharactersUseCase.Result.Success -> {
                boundEpisode()
                charsList.value = result.responseCharacter
            }
            is GetCharactersUseCase.Result.Failure -> showErrorGettingChars.trigger(true)
        }
    }

    private fun boundEpisode() {
        getEpisodesUseCase.getEpisodes()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { handleEpisodeResult(it) }
            .addTo(disposables)
    }

    private fun handleEpisodeResult(result: GetEpisodesUseCase.ResultEpisode?) {
        when (result) {
            is GetEpisodesUseCase.ResultEpisode.LoadingEpisode -> progressVisible.value = true
            is GetEpisodesUseCase.ResultEpisode.SuccessEpisode -> {
                episodesList.value = result.responseEpisode
                episodesCharactersList.value = getCharacterEpisodesUseCase.getCharacters()
            }
            is GetEpisodesUseCase.ResultEpisode.FailureEpisode -> showErrorGettingChars.trigger(true)
        }
    }

    fun unbound() {
        disposables.clear()
    }

    override fun onCleared() {
        super.onCleared()

    }


}