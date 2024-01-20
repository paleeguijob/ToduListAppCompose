package realaof.realhon.realha.todulistapp.domain.usecase.todu

import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import realaof.realhon.realha.todulistapp.base.extensions.BaseUnitTest
import realaof.realhon.realha.todulistapp.data.model.todu.ToduItem
import realaof.realhon.realha.todulistapp.domain.repository.todu.local.ToduLocalRepository

@OptIn(ExperimentalCoroutinesApi::class)
class PutToduUseCaseTest : BaseUnitTest() {

    private val repository: ToduLocalRepository = mockk()

    private lateinit var putToduUseCase: PutToduUseCase

    @Before
    override fun setup() {
        super.setup()

        putToduUseCase = PutToduUseCase(repository)
    }

    @Test
    fun putTodu_Success() = runTest {
        //Given
        val insertData = ToduItem(
            id = 0,
            userId = 1,
            title = "RealHON RealAO Stay Together everytime.",
            completed = true
        )

        //When
        putToduUseCase.execute(insertData)

        //Then
        coVerify { repository.insertTodu(insertData) }
    }
}