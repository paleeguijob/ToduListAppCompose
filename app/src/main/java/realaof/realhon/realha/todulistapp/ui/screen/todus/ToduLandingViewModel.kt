package realaof.realhon.realha.todulistapp.ui.screen.todus

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import realaof.realhon.realha.todulistapp.base.model.BaseCommonError
import realaof.realhon.realha.todulistapp.base.model.enum.todu.FilterType
import realaof.realhon.realha.todulistapp.base.model.toBaseCommonError
import realaof.realhon.realha.todulistapp.data.model.todu.ToduItem
import realaof.realhon.realha.todulistapp.domain.mapper.todu.ToduMapper
import realaof.realhon.realha.todulistapp.domain.usecase.todu.GetToduListUseCase
import realaof.realhon.realha.todulistapp.domain.usecase.todu.GetToduLocalListUseCase
import realaof.realhon.realha.todulistapp.domain.usecase.todu.PutToduListUseCase
import realaof.realhon.realha.todulistapp.domain.usecase.todu.PutToduUseCase
import realaof.realhon.realha.todulistapp.domain.usecase.todu.SearchToduListUseCase
import realaof.realhon.realha.todulistapp.ui.screen.detail.uimodel.DetailUi
import realaof.realhon.realha.todulistapp.ui.screen.todus.uimodel.todulanding.ToduLandingUiState
import javax.inject.Inject

@HiltViewModel
class ToduLandingViewModel @Inject constructor(
    private val getToduListUseCase: GetToduListUseCase,
    private val getToduLocalListUseCase: GetToduLocalListUseCase,
    private val putToduListUseCase: PutToduListUseCase,
    private val searchToduListUseCase: SearchToduListUseCase,
    private val putToduUseCase: PutToduUseCase,
    private val mapper: ToduMapper
) : ViewModel() {

    private val _toduLandingUiState = MutableStateFlow(ToduLandingUiState(loading = true))
    val toduLandingUiState: StateFlow<ToduLandingUiState> get() = _toduLandingUiState.asStateFlow()

    fun getToduList() = viewModelScope.launch {

        getToduListUseCase.execute(Unit)
            .onSuccess { response ->

                insertList(response)

                _toduLandingUiState.set(
                    ToduLandingUiState(success = mapper.mapTo(response))
                )
            }
            .onFailure { error ->
                if (error is BaseCommonError.NetworkError) {
                    getLocalToduList()
                } else {
                    _toduLandingUiState.set(
                        ToduLandingUiState(error = error.toBaseCommonError())
                    )
                }
            }
    }

    fun getLocalToduList() = viewModelScope.launch {
        getToduLocalListUseCase.execute(Unit)
            .onSuccess { response ->
                _toduLandingUiState.set(
                    ToduLandingUiState(success = mapper.mapTo(response))
                )
            }
            .onFailure { error ->
                _toduLandingUiState.set(
                    ToduLandingUiState(error = error.toBaseCommonError())
                )
            }
    }

    fun insertList(list: List<ToduItem>) = viewModelScope.launch {
        putToduListUseCase.execute(PutToduListUseCase.Input(list))
            .onSuccess { }
            .onFailure { error ->
                _toduLandingUiState.set(
                    ToduLandingUiState(error = error.toBaseCommonError())
                )
            }
    }

    fun search(keyword: String, status: FilterType) {
        val completed = when (status) {
            FilterType.ALL -> null
            FilterType.ACTIVE -> false
            FilterType.DONE -> true
        }

        searchToduList(keyword, completed)
    }

    fun mapLandingUiToDetailUi(tuduUi: ToduLandingUiState.ToduLandingUi.ToduListUi.ToduUi): DetailUi =
        mapper.mapTo(tuduUi)

    fun insertDetail(detailUi: DetailUi, keyword: String, status: FilterType) =
        viewModelScope.launch {
            val input = mapper.mapTo(detailUi)

            putToduUseCase.execute(input)
                .onSuccess { search(keyword, status) }
                .onFailure { error -> }
        }

    private fun searchToduList(keyword: String, completed: Boolean?) = viewModelScope.launch {
        searchToduListUseCase.execute(SearchToduListUseCase.Input(keyword, completed))
            .onSuccess { response ->
                _toduLandingUiState.set(
                    ToduLandingUiState(success = mapper.mapTo(response))
                )
            }
            .onFailure { error ->
                _toduLandingUiState.set(
                    ToduLandingUiState(error = error.toBaseCommonError())
                )
            }
    }

    private fun MutableStateFlow<ToduLandingUiState>.set(uiState: ToduLandingUiState) {
        this.value = uiState
        toduLandingUiState.value.landingUiState.updateState(uiState)
    }
}