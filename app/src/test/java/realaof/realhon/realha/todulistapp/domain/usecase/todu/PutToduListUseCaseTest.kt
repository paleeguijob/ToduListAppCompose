package realaof.realhon.realha.todulistapp.domain.usecase.todu

import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import realaof.realhon.realha.todulistapp.base.extensions.BaseUnitTest
import realaof.realhon.realha.todulistapp.data.model.todu.ToduItem
import realaof.realhon.realha.todulistapp.domain.repository.todu.local.ToduLocalRepository

@OptIn(ExperimentalCoroutinesApi::class)
class PutToduListUseCaseTest : BaseUnitTest() {

    private val repository: ToduLocalRepository = mockk()

    private lateinit var putToduListUseCase: PutToduListUseCase

    @Before
    override fun setup() {
        super.setup()

        putToduListUseCase = PutToduListUseCase(repository)
    }

    @Test
    fun putToduList_Success() = runTest {
        //Given
        val insertData = getToduListData()

        //When
        putToduListUseCase.execute(PutToduListUseCase.Input(insertData))

        //Then
        coVerify { repository.insertToduList(insertData) }
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