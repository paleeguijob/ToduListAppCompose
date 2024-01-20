package realaof.realhon.realha.todulistapp.domain.usecase.todu

import realaof.realhon.realha.todulistapp.base.usecase.BaseSuspendUseCase
import realaof.realhon.realha.todulistapp.data.model.todu.ToduItem
import realaof.realhon.realha.todulistapp.domain.repository.todu.local.ToduLocalRepository
import javax.inject.Inject

class PutToduListUseCase @Inject constructor(
    private val repository: ToduLocalRepository
) : BaseSuspendUseCase<PutToduListUseCase.Input, Unit>() {

    override suspend fun create(input: Input) {
        repository.insertToduList(input.list)
    }

    data class Input(val list: List<ToduItem>)
}