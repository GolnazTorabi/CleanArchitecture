package com.test.cleanArchRoomTest.character.view.character

import android.annotation.SuppressLint
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.cleanArchRoomTest.character.domain.model.CharactersData
import com.test.cleanArchRoomTest.character.domain.usecase.character.GetCharactersUseCase
import com.test.cleanArchRoomTest.character.domain.usecase.character.InsertCharacterUseCase
import com.test.cleanArchRoomTest.utils.ext.addTo
import com.test.cleanArchRoomTest.utils.rx.StickyAction
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CharacterViewModel @ViewModelInject constructor(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val insertCharacterUseCase: InsertCharacterUseCase
) :
    ViewModel() {

    private val disposables = CompositeDisposable()


    private var _charsList = MutableLiveData<List<CharactersData>>(arrayListOf())

    val charsList: LiveData<List<CharactersData>> get() = _charsList

    val showErrorGettingChars = StickyAction<Boolean>()
    val progressVisible = MutableLiveData<Boolean>()

    fun bound(hasNetwork: Boolean) {
        if (_charsList.value?.isEmpty()!!) {
            getCharactersUseCase.getCharacters(hasNetwork)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { handleResult(it) }
                .addTo(disposables)
        }

    }

    private fun handleResult(result: GetCharactersUseCase.Result) {
        when (result) {
            is GetCharactersUseCase.Result.Loading -> progressVisible.value = true
            is GetCharactersUseCase.Result.Success -> {
                insert(result.responseCharacter)
                _charsList.value = result.responseCharacter
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

    override fun onCleared() {
        disposables.clear()
    }


}