package com.hakkasuru.arcana.debugprefs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hakkasuru.arcana.api.repository.ArcanaRepository
import com.hakkasuru.arcana.ui.model.PreferenceItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PreferenceViewModel @Inject constructor(
    private val arcanaRepository: ArcanaRepository
) : ViewModel() {
    private val _state = MutableStateFlow<State>(State.OnIdle)
    val state: StateFlow<State>
        get() = _state.asStateFlow()

    sealed class Action {
        class FetchArcana(val cls: Class<*>) : Action()
    }

    sealed class State{
        class ShowArcana(val preferences: List<PreferenceItem>) : State()
        object OnError : State()
        object OnLoading: State()
        object OnIdle : State()
    }

    fun submitAction(action: Action) {
        when (action) {
            is Action.FetchArcana -> {
                viewModelScope.launch {
                    arcanaRepository.fetchArcana(action.cls)
                        .onStart { _state.value = State.OnLoading }
                        .catch { _state.value = State.OnError }
                        .collect {
                            _state.value = State.ShowArcana(it)
                        }
                }
            }
        }
    }
}