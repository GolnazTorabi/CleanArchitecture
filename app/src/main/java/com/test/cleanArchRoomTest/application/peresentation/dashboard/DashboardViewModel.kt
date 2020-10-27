package com.test.cleanArchRoomTest.application.peresentation.dashboard

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.cleanArchRoomTest.domain.model.CharactersData
import com.test.cleanArchRoomTest.domain.usecase.character.GetCharactersUseCase
import com.test.cleanArchRoomTest.domain.usecase.character.InsertCharacterUseCase
import com.test.cleanArchRoomTest.utils.ext.addTo
import com.test.cleanArchRoomTest.utils.rx.StickyAction
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableMaybeObserver
import io.reactivex.schedulers.Schedulers

class DashboardViewModel @ViewModelInject constructor(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val insertCharacterUseCase: InsertCharacterUseCase
) :
    ViewModel() {

    private val disposables = CompositeDisposable()
    val progressVisible = MutableLiveData<Boolean>()
    val charsList = MutableLiveData<List<CharactersData>>()
    val showErrorGettingChars = StickyAction<Boolean>()

    fun bound(hasNetwork: Boolean) {
        getCharactersUseCase.getCharacters(hasNetwork)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { handleResult(it) }
            .addTo(disposables)

    }

    private fun handleResult(result: GetCharactersUseCase.Result) {
        when (result) {
            is GetCharactersUseCase.Result.Loading -> progressVisible.value = true
            is GetCharactersUseCase.Result.Success -> {
                insert(result.responseCharacter)
                charsList.value = result.responseCharacter
            }
            is GetCharactersUseCase.Result.Failure -> showErrorGettingChars.trigger(true)
        }
    }

    @SuppressLint("CheckResult")
    private fun insert(data: List<CharactersData>) {
        insertCharacterUseCase.insertCharacters(data)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    fun unbound() {
        disposables.clear()
    }

    override fun onCleared() {
        super.onCleared()

    }


}