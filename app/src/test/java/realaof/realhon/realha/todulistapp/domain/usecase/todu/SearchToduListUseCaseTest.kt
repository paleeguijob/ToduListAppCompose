package realaof.realhon.realha.todulistapp.domain.usecase.todu

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import realaof.realhon.realha.todulistapp.base.extensions.BaseUnitTest
import realaof.realhon.realha.todulistapp.data.model.todu.ToduItem
import realaof.realhon.realha.todulistapp.domain.repository.todu.local.ToduLocalRepository

@OptIn(ExperimentalCoroutinesApi::class)
class SearchToduListUseCaseTest : BaseUnitTest() {

    private val repository: ToduLocalRepository = mockk()

    private lateinit var searchToduListUseCase: SearchToduListUseCase

    @Before
    override fun setup() {
        super.setup()

        searchToduListUseCase = SearchToduListUseCase(repository)
    }

    @Test
    fun searchToduList_Success() = runTest {
        //Given
        val expect = getToduListData()
        val mockResponse = flowOf(expect)

        coEvery { repository.searchToduList(any(), any()) } returns mockResponse

        //When
        val response = searchToduListUseCase.execute(
            SearchToduListUseCase.Input("Todu", true)
        )

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