package realaof.realhon.realha.todulistapp.domain.repository.todu.local

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import realaof.realhon.realha.todulistapp.data.local.todu.ToduLocalDao
import realaof.realhon.realha.todulistapp.data.mapper.todu.ToduLocalDataMapper
import realaof.realhon.realha.todulistapp.data.model.todu.ToduItem
import javax.inject.Inject

class ToduLocalRepositoryImp @Inject constructor(
    private val toduLocalDao: ToduLocalDao,
    private val dispatcher: CoroutineDispatcher
) : ToduLocalRepository {

    override suspend fun insertToduList(list: List<ToduItem>): Unit =
        withContext(dispatcher) {
            toduLocalDao.updateTudoList(list)
        }

    override suspend fun insertTodu(todu: ToduItem): Unit =
        withContext(dispatcher){
            toduLocalDao.updateTodu(todu)
        }

    override suspend fun getLocalToduList(): Flow<List<ToduItem>> =
        withContext(dispatcher) {
            val response = toduLocalDao.getAll()
            flow { emit(response) }
        }

    override suspend fun searchToduList(
        keyword: String,
        completed: Boolean?
    ): Flow<List<ToduItem>> =
        withContext(dispatcher) {
            val response = toduLocalDao.searchTask(keyword = keyword, completed)
            flow { emit(response) }
        }

}