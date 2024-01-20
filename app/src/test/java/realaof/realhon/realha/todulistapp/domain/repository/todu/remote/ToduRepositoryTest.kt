package realaof.realhon.realha.todulistapp.domain.repository.todu.remote

import app.cash.turbine.test
import app.cash.turbine.turbineScope
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import realaof.realhon.realha.todulistapp.base.extensions.BaseUnitTest
import realaof.realhon.realha.todulistapp.base.model.NetworkResponse
import realaof.realhon.realha.todulistapp.data.model.todu.ToduItem
import realaof.realhon.realha.todulistapp.data.service.todu.ToduService

@OptIn(ExperimentalCoroutinesApi::class)
class ToduRepositoryTest : BaseUnitTest() {

    private val service: ToduService = mockk()

    private val dispatcher: CoroutineDispatcher = Dispatchers.IO

    private lateinit var repository: ToduRepository

    @Before
    override fun setup() {
        super.setup()

        repository = ToduRepositoryImp(service, dispatcher)
    }

    @Test
    fun getToduList_Success() = runTest {
        //Given
        val todus = getToduListData()
        val expectData = NetworkResponse.Success(todus)

        coEvery { service.getTuduList() } returns expectData

        //When
        //Then
        turbineScope {
            val response = repository.getToduList().testIn(backgroundScope)
            Assert.assertEquals(expectData, response.awaitItem())
            response.awaitComplete()
        }
    }

    @Test
    fun getToduList_Error() = runTest {
        //When
        //Don't mock any response from service,It's for test emit Flow catch exception

        //Then
        turbineScope {
            val response = repository.getToduList().testIn(backgroundScope)
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