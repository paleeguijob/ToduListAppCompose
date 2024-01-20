package realaof.realhon.realha.todulistapp.domain.repository.todu.local

import app.cash.turbine.turbineScope
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import realaof.realhon.realha.todulistapp.base.extensions.BaseUnitTest
import realaof.realhon.realha.todulistapp.data.local.todu.ToduLocalDao
import realaof.realhon.realha.todulistapp.data.model.todu.ToduItem

@OptIn(ExperimentalCoroutinesApi::class)
class ToduLocalRepositoryTest : BaseUnitTest() {

    private val dao: ToduLocalDao = mockk()

    private val dispatcher: CoroutineDispatcher = Dispatchers.IO

    private lateinit var repository: ToduLocalRepository

    @Before
    override fun setup() {
        super.setup()

        repository = ToduLocalRepositoryImp(dao, dispatcher)
    }

    @Test
    fun getLocalToduList_Success() = runTest {
        //Given
        val expect = getToduListData()

        //When
        coEvery { dao.getAll() } returns expect

        //Then
        turbineScope {
            val response = repository.getLocalToduList().testIn(backgroundScope)
            Assert.assertEquals(expect, response.awaitItem())
            response.awaitComplete()
        }
    }

    @Test
    fun insertToduListLocal_Success() = runTest {
        //Given
        val expect = getToduListData()

        coEvery { dao.updateTudoList(expect) } returns Unit

        //When
        repository.insertToduList(expect)

        //Then
        coVerify { dao.updateTudoList(expect) }
    }

    @Test
    fun insertToduLocal_Success() = runTest {
        //Given
        val expect = getToduListData().first()

        coEvery { dao.updateTodu(expect) } returns Unit

        //When
        repository.insertTodu(expect)

        //Then
        coVerify { dao.updateTodu(expect) }
    }

    @Test
    fun searchToduList_Success() = runTest {
        //Given
        val keyword = "RealHON RealAOF Stay Together everytime."
        val expect = getToduListData()

        //When
        coEvery { dao.searchTask(keyword, true) } returns expect

        //Then
        turbineScope {
            val response = repository.searchToduList(keyword, true).testIn(backgroundScope)
            Assert.assertEquals(expect, response.awaitItem())
            response.awaitComplete()
        }
    }

    private fun getToduListData() = listOf(
        ToduItem(
            id = 0,
            userId = 1,
            title = "RealAOF RealHON Stay Together",
            completed = true
        )
    )
}