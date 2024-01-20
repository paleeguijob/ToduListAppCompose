package realaof.realhon.realha.todulistapp.domain.usecase.todu

import app.cash.turbine.turbineScope
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import realaof.realhon.realha.todulistapp.base.extensions.BaseUnitTest
import realaof.realhon.realha.todulistapp.base.model.NetworkResponse
import realaof.realhon.realha.todulistapp.data.model.todu.ToduItem
import realaof.realhon.realha.todulistapp.domain.repository.todu.remote.ToduRepository

@OptIn(ExperimentalCoroutinesApi::class)
class GetToduListUseCaseTest : BaseUnitTest() {

    private val repository: ToduRepository = mockk()

    private lateinit var getToduListUseCase: GetToduListUseCase

    @Before
    override fun setup() {
        super.setup()

        getToduListUseCase = GetToduListUseCase(repository)
    }

    @Test
    fun getToduList_Success() = runTest{
        //Given
        val expect = getToduListData()
        val mockResponse = flowOf(NetworkResponse.Success(expect))

        coEvery { repository.getToduList() } returns mockResponse

        //When
        val response = getToduListUseCase.execute(Unit)

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