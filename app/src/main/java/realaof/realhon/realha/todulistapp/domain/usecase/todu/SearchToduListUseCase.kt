package realaof.realhon.realha.todulistapp.domain.usecase.todu

import kotlinx.coroutines.flow.last
import realaof.realhon.realha.todulistapp.base.usecase.BaseSuspendUseCase
import realaof.realhon.realha.todulistapp.data.model.todu.ToduItem
import realaof.realhon.realha.todulistapp.domain.repository.todu.local.ToduLocalRepository
import javax.inject.Inject

class SearchToduListUseCase @Inject constructor(
    private val repository: ToduLocalRepository
) : BaseSuspendUseCase<SearchToduListUseCase.Input, List<ToduItem>>() {

    override suspend fun create(input: Input): List<ToduItem> =
        repository.searchToduList(input.keyword, input.completed).last()

    data class Input(val keyword: String, val completed: Boolean?)
}