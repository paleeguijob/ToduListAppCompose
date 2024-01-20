package realaof.realhon.realha.todulistapp.domain.repository.todu.remote

import kotlinx.coroutines.flow.Flow
import realaof.realhon.realha.todulistapp.base.model.BaseCommonError
import realaof.realhon.realha.todulistapp.base.model.NetworkResponse
import realaof.realhon.realha.todulistapp.data.model.todu.ToduItem

interface ToduRepository {

    suspend fun getToduList(): Flow<NetworkResponse<List<ToduItem>, BaseCommonError>>
}