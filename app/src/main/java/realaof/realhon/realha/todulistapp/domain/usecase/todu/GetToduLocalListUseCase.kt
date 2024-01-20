package realaof.realhon.realha.todulistapp.domain.usecase.todu

import kotlinx.coroutines.flow.last
import realaof.realhon.realha.todulistapp.base.usecase.BaseSuspendUseCase
import realaof.realhon.realha.todulistapp.data.model.todu.ToduItem
import realaof.realhon.realha.todulistapp.domain.repository.todu.local.ToduLocalRepository
import javax.inject.Inject

class GetToduLocalListUseCase @Inject constructor(
    private val repository: ToduLocalRepository
): BaseSuspendUseCase<Unit, List<ToduItem>>() {

    override suspend fun create(input: Unit): List<ToduItem>  =
        repository.getLocalToduList().last()
}