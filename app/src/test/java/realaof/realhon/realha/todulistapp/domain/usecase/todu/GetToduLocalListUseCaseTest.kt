package realaof.realhon.realha.todulistapp.domain.usecase.todu

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import realaof.realhon.realha.todulistapp.base.extensions.BaseUnitTest
import realaof.realhon.realha.todulistapp.base.model.NetworkResponse
import realaof.realhon.realha.todulistapp.data.model.todu.ToduItem
import realaof.realhon.realha.todulistapp.domain.repository.todu.local.ToduLocalRepository

@OptIn(ExperimentalCoroutinesApi::class)
class GetToduLocalListUseCaseTest : BaseUnitTest() {

    private val repository: ToduLocalRepository = mockk()

    private lateinit var getToduLocalListUseCase: GetToduLocalListUseCase

    @Before
    override fun setup() {
        super.setup()

        getToduLocalListUseCase = GetToduLocalListUseCase(repository)
    }

    @Test
    fun getToduLocalList_Success() = runTest {
        //Given
        val expect = getToduListData()
        val mockResponse = flowOf(expect)

        coEvery { repository.getLocalToduList() } returns mockResponse

        //When
        val response = getToduLocalListUseCase.execute(Unit)

        //Then
        Assert.assertEquals(expect, response.getOrNull())
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