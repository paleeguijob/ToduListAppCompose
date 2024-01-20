package realaof.realhon.realha.todulistapp.domain.repository.todu.local

import kotlinx.coroutines.flow.Flow
import realaof.realhon.realha.todulistapp.data.model.todu.ToduItem

interface ToduLocalRepository {

    suspend fun insertToduList(list: List<ToduItem>)

    suspend fun insertTodu(todu: ToduItem)

    suspend fun getLocalToduList(): Flow<List<ToduItem>>

    suspend fun searchToduList(keyword: String, completed: Boolean?): Flow<List<ToduItem>>
}