package com.test.cleanArchRoomTest.episode.view.episode

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.cleanArchRoomTest.episode.domain.usecase.episode.GetCharacterEpisodesUseCase
import com.test.cleanArchRoomTest.utils.ext.addTo
import com.test.cleanArchRoomTest.utils.rx.StickyAction
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class EpisodesViewModel @ViewModelInject constructor(private val getCharacterEpisodesUseCase: GetCharacterEpisodesUseCase) :
    ViewModel() {

    private var _episodes = MutableLiveData<List<String>>(arrayListOf())

    val episodes: LiveData<List<String>> get() = _episodes
    private val disposables = CompositeDisposable()
    val showErrorGettingChars = StickyAction<Boolean>()


    fun getEpisodes(id: String?) {
        if (_episodes.value?.isEmpty()!!) {
            getCharacterEpisodesUseCase.getEpisodes(id!!)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { handleResult(it) }
                .addTo(disposables)
        }
    }

    private fun handleResult(result: GetCharacterEpisodesUseCase.Result?) {
        when (result) {
            is GetCharacterEpisodesUseCase.Result.Success -> {
                _episodes.value = handleData(result.responseCharacter)

            }
            is GetCharacterEpisodesUseCase.Result.Failure -> showErrorGettingChars.trigger(true)
        }
    }

    private fun handleData(value: List<String>): ArrayList<String> {
        val data = value[0].split(",", "[", "]") as ArrayList<String>
        val changedData = ArrayList<String>()
        data.removeAt(0)
        data.removeAt(data.size - 1)
        data.forEach { changeData ->
            changedData.add(changeData.substringAfterLast("/"))
        }
        return changedData
    }

    override fun onCleared() {
        disposables.clear()
    }
}
