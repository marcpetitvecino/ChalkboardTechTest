package com.marcpetit.chalkboardtechtest.presentation.birthdaylist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marcpetit.chalkboardtechtest.domain.birthday.BirthdayDomainModel
import com.marcpetit.chalkboardtechtest.domain.birthdaylist.GetBirthdayListUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

sealed class BirthdayListEvent {
    object InitEvent: BirthdayListEvent()
}

sealed class BirthdayListViewState {
    class ShowData(val birthdayList: List<BirthdayDomainModel>): BirthdayListViewState()
    object Loading: BirthdayListViewState()
    object Error: BirthdayListViewState()
}

class BirthdayListFragmentViewModel(private val getBirthdayListUseCase: GetBirthdayListUseCase)
    : ViewModel() {

    private val mutableViewState: MutableLiveData<BirthdayListViewState> = MutableLiveData()
    val viewState: LiveData<BirthdayListViewState>
        get() = mutableViewState

    fun dispatch(event: BirthdayListEvent) {
        when (event) {
            BirthdayListEvent.InitEvent -> getBirthdayList()
        }
    }

    private fun getBirthdayList() {
        mutableViewState.postValue(BirthdayListViewState.Loading)
        viewModelScope.launch {
            getBirthdayListUseCase(Unit).catch {
                mutableViewState.postValue(BirthdayListViewState.Error)
            }.collect {
                mutableViewState.postValue(BirthdayListViewState.ShowData(it))
            }
        }
    }

}