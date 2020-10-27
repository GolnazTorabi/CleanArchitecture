package com.test.cleanArchRoomTest.application.peresentation.dashboard.episode

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.cleanArchRoomTest.domain.usecase.episode.GetCharacterEpisodesUseCase
import com.test.cleanArchRoomTest.utils.ext.addTo
import com.test.cleanArchRoomTest.utils.rx.StickyAction
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class EpisodesViewModel @ViewModelInject constructor(private val getCharacterEpisodesUseCase: GetCharacterEpisodesUseCase) :
    ViewModel() {

    val episodes = MutableLiveData<List<String>>()
    private val disposables = CompositeDisposable()
    val showErrorGettingChars = StickyAction<Boolean>()

    fun getEpisodes(id: String?) {
        getCharacterEpisodesUseCase.getEpisodes(id!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { handleResult(it) }
            .addTo(disposables)
    }

    private fun handleResult(result: GetCharacterEpisodesUseCase.Result?) {
        when (result) {
            is GetCharacterEpisodesUseCase.Result.Success -> {
                episodes.value = handleData(result.responseCharacter)
            }
            is GetCharacterEpisodesUseCase.Result.Failure -> showErrorGettingChars.trigger(true)
        }
    }

    private fun handleData(value: List<String>): ArrayList<String> {
        val data: ArrayList<String> = value[0].split(",", "[", "]") as ArrayList<String>
        data.removeAt(0)
        data.removeAt(data.size - 1)
        return data
    }

    fun unbound() {
        disposables.clear()
    }

}