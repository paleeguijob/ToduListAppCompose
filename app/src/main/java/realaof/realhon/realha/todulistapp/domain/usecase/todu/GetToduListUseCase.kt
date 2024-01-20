package realaof.realhon.realha.todulistapp.domain.usecase.todu

import kotlinx.coroutines.flow.last
import realaof.realhon.realha.todulistapp.base.model.BaseCommonError
import realaof.realhon.realha.todulistapp.base.model.toDataOrThrow
import realaof.realhon.realha.todulistapp.base.usecase.BaseSuspendUseCase
import realaof.realhon.realha.todulistapp.data.model.todu.ToduItem
import realaof.realhon.realha.todulistapp.domain.repository.todu.remote.ToduRepository
import realaof.realhon.realha.todulistapp.domain.repository.todu.local.ToduLocalRepository
import java.io.IOException
import javax.inject.Inject

class GetToduListUseCase @Inject constructor(
    private val repository: ToduRepository
) : BaseSuspendUseCase<Unit, List<ToduItem>>() {

    override suspend fun create(input: Unit): List<ToduItem> =
        repository.getToduList().last().toDataOrThrow()
}