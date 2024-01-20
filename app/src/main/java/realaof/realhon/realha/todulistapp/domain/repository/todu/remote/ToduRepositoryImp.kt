package realaof.realhon.realha.todulistapp.domain.repository.todu.remote

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import realaof.realhon.realha.todulistapp.base.model.BaseCommonError
import realaof.realhon.realha.todulistapp.base.model.NetworkResponse
import realaof.realhon.realha.todulistapp.data.local.todu.ToduLocalDao
import realaof.realhon.realha.todulistapp.data.mapper.todu.ToduLocalDataMapper
import realaof.realhon.realha.todulistapp.data.model.todu.ToduItem
import realaof.realhon.realha.todulistapp.data.service.todu.ToduService
import javax.inject.Inject

class ToduRepositoryImp @Inject constructor(
    private val service: ToduService,
    private val dispatcher: CoroutineDispatcher
) : ToduRepository {

    override suspend fun getToduList(): Flow<NetworkResponse<List<ToduItem>, BaseCommonError>> =
        withContext(dispatcher) {
            flow {
                emit(service.getTuduList())
            }.catch { error ->
                emit(NetworkResponse.OtherError(error))
            }
        }
}