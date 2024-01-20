package realaof.realhon.realha.todulistapp.ui.screen.todus

import app.cash.turbine.turbineScope
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import realaof.realhon.realha.todulistapp.base.extensions.BaseUnitTest
import realaof.realhon.realha.todulistapp.base.model.BaseCommonError
import realaof.realhon.realha.todulistapp.base.model.NetworkResponse
import realaof.realhon.realha.todulistapp.base.model.enum.todu.FilterType
import realaof.realhon.realha.todulistapp.base.model.toBaseCommonError
import realaof.realhon.realha.todulistapp.data.model.todu.ToduItem
import realaof.realhon.realha.todulistapp.domain.mapper.todu.ToduMapper
import realaof.realhon.realha.todulistapp.domain.mapper.todu.TuduMapperImp
import realaof.realhon.realha.todulistapp.domain.usecase.todu.GetToduListUseCase
import realaof.realhon.realha.todulistapp.domain.usecase.todu.GetToduLocalListUseCase
import realaof.realhon.realha.todulistapp.domain.usecase.todu.PutToduListUseCase
import realaof.realhon.realha.todulistapp.domain.usecase.todu.PutToduUseCase
import realaof.realhon.realha.todulistapp.domain.usecase.todu.SearchToduListUseCase
import realaof.realhon.realha.todulistapp.ui.screen.detail.uimodel.DetailUi
import realaof.realhon.realha.todulistapp.ui.screen.todus.uimodel.todulanding.ToduLandingUiState
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
class ToduLandingViewModelTest : BaseUnitTest() {

    private val getToduListUseCase: GetToduListUseCase = mockk()
    private val getToduLocalListUseCase: GetToduLocalListUseCase = mockk()
    private val putToduListUseCase: PutToduListUseCase = mockk()
    private val searchToduListUseCase: SearchToduListUseCase = mockk()
    private val putToduUseCase: PutToduUseCase = mockk()
    private val mapper: ToduMapper = TuduMapperImp()

    private lateinit var viewModel: ToduLandingViewModel

    @Before
    override fun setup() {
        super.setup()

        viewModel = ToduLandingViewModel(
            getToduListUseCase = getToduListUseCase,
            getToduLocalListUseCase = getToduLocalListUseCase,
            putToduListUseCase = putToduListUseCase,
            searchToduListUseCase = searchToduListUseCase,
            putToduUseCase = putToduUseCase,
            mapper = mapper
        )
    }

    @Test
    fun getToduList_Success() = runTest {
        //Given
        val toduList = getToduListData()
        val mockResponse = Result.success(toduList)
        val expect = ToduLandingUiState(success = mapper.mapTo(toduList))

        coEvery { getToduListUseCase.execute(Unit) } returns mockResponse
        coEvery { putToduListUseCase.execute(any()) } returns Result.success(Unit)

        //When
        viewModel.getToduList()

        //Then
        turbineScope {
            val response = viewModel.toduLandingUiState.testIn(backgroundScope).awaitItem()
            Assert.assertEquals(expect.loading, response.loading)
            Assert.assertEquals(expect.error, response.error)
            Assert.assertEquals(expect.success, response.success)
        }
    }

    @Test
    fun getToduList_FailureAndGetToduListLocalDataSuccess() = runTest {
        //Given
        val error = BaseCommonError.NetworkError(IOException())
        val mockResponseApi = Result.failure<List<ToduItem>>(error)

        val toduList = getToduListData()
        val mockResponseLocal = Result.success(toduList)

        val expect = ToduLandingUiState(success = mapper.mapTo(toduList))

        coEvery { getToduListUseCase.execute(Unit) } returns mockResponseApi
        coEvery { putToduListUseCase.execute(any()) } returns Result.success(Unit)
        coEvery { getToduLocalListUseCase.execute(Unit) } returns mockResponseLocal

        //When
        viewModel.getToduList()

        //Then
        turbineScope {
            val response = viewModel.toduLandingUiState.testIn(backgroundScope).awaitItem()
            Assert.assertEquals(expect.loading, response.loading)
            Assert.assertEquals(expect.error, response.error)
            Assert.assertEquals(expect.success, response.success)
        }
    }

    @Test
    fun getToduList_Failure() = runTest {
        //Given
        val error = Throwable()
        val mockResponse = Result.failure<List<ToduItem>>(error)

        val expect = ToduLandingUiState(error = error.toBaseCommonError())

        coEvery { getToduListUseCase.execute(Unit) } returns mockResponse

        //When
        viewModel.getToduList()

        //Then
        turbineScope {
            val response = viewModel.toduLandingUiState.testIn(backgroundScope).awaitItem()
            Assert.assertEquals(expect.loading, response.loading)
            Assert.assertEquals(expect.error, response.error)
            Assert.assertEquals(expect.success, response.success)
        }
    }

    @Test
    fun getLocalToduList_Success() = runTest {
        //Given
        val toduList = getToduListData()
        val mockResponse = Result.success(toduList)
        val expect = ToduLandingUiState(success = mapper.mapTo(toduList))

        coEvery { getToduLocalListUseCase.execute(Unit) } returns mockResponse

        //When
        viewModel.getLocalToduList()

        //Then
        turbineScope {
            val response = viewModel.toduLandingUiState.testIn(backgroundScope).awaitItem()
            Assert.assertEquals(expect.loading, response.loading)
            Assert.assertEquals(expect.error, response.error)
            Assert.assertEquals(expect.success, response.success)
        }
    }

    @Test
    fun getLocalToduList_Failure() = runTest {
        //Given
        val error = Throwable()
        val mockResponse = Result.failure<List<ToduItem>>(error)
        val expect = ToduLandingUiState(error = error.toBaseCommonError())

        coEvery { getToduLocalListUseCase.execute(Unit) } returns mockResponse

        //When
        viewModel.getLocalToduList()

        //Then
        turbineScope {
            val response = viewModel.toduLandingUiState.testIn(backgroundScope).awaitItem()
            Assert.assertEquals(expect.loading, response.loading)
            Assert.assertEquals(expect.error, response.error)
            Assert.assertEquals(expect.success, response.success)
        }
    }

    @Test
    fun insertToduList_Success() = runTest {
        //Given
        val toduList = getToduListData()
        val mockResponse = Result.success(Unit)
        val expect = ToduLandingUiState(loading = true)

        coEvery { putToduListUseCase.execute(any()) } returns mockResponse

        //When
        viewModel.insertList(toduList)

        //Then
        turbineScope {
            val response = viewModel.toduLandingUiState.testIn(backgroundScope).awaitItem()
            Assert.assertEquals(expect.loading, response.loading)
            Assert.assertEquals(expect.error, response.error)
            Assert.assertEquals(expect.success, response.success)
        }
    }

    @Test
    fun insertToduList_Failure() = runTest {
        //Given
        val error = Throwable()
        val mockResponse = Result.failure<Unit>(error)
        val expect = ToduLandingUiState(error = error.toBaseCommonError())

        coEvery { putToduListUseCase.execute(any()) } returns mockResponse

        //When
        viewModel.insertList(getToduListData())

        //Then
        turbineScope {
            val response = viewModel.toduLandingUiState.testIn(backgroundScope).awaitItem()
            Assert.assertEquals(expect.loading, response.loading)
            Assert.assertEquals(expect.error, response.error)
            Assert.assertEquals(expect.success, response.success)
        }
    }

    @Test
    fun searchToduList_Success() = runTest {
        //Given
        val keyword = "RealAOF RealHON Stay Together"
        val toduList = getToduListData()
        val mockResponse = Result.success(toduList)

        val expect = ToduLandingUiState(success = mapper.mapTo(toduList))

        coEvery { searchToduListUseCase.execute(any()) } returns mockResponse

        //When
        viewModel.search(keyword, FilterType.ALL)
        viewModel.search(keyword, FilterType.ACTIVE)
        viewModel.search(keyword, FilterType.DONE)

        //Then
        turbineScope {
            val response = viewModel.toduLandingUiState.testIn(backgroundScope).awaitItem()
            Assert.assertEquals(expect.loading, response.loading)
            Assert.assertEquals(expect.error, response.error)
            Assert.assertEquals(expect.success, response.success)
        }
    }

    @Test
    fun searchToduList_Failure() = runTest {
        //Given
        val keyword = "RealAOF RealHON Stay Together"
        val error = Throwable()
        val mockResponse = Result.failure<List<ToduItem>>(error)

        val expect = ToduLandingUiState(error = error.toBaseCommonError())

        coEvery { searchToduListUseCase.execute(any()) } returns mockResponse

        //When
        viewModel.search(keyword, FilterType.DONE)

        //Then
        turbineScope {
            val response = viewModel.toduLandingUiState.testIn(backgroundScope).awaitItem()
            Assert.assertEquals(expect.loading, response.loading)
            Assert.assertEquals(expect.error, response.error)
            Assert.assertEquals(expect.success, response.success)
        }
    }

    @Test
    fun insertDetail_Success() = runTest {
        //Given
        val keyword = "RealAOF RealHON Stay Together"
        val detail = getDetail()
        val toduList = getToduListData()

        val mockSearchResponse = Result.success(toduList)
        val mockResponse = Result.success(Unit)

        val expect = ToduLandingUiState(success = mapper.mapTo(toduList))

        coEvery { searchToduListUseCase.execute(any()) } returns mockSearchResponse
        coEvery { putToduUseCase.execute(mapper.mapTo(detail)) } returns mockResponse

        //When
        viewModel.insertDetail(detail, keyword, FilterType.ALL)
        viewModel.insertDetail(detail, keyword, FilterType.ACTIVE)
        viewModel.insertDetail(detail, keyword, FilterType.DONE)

        //Then
        turbineScope {
            val response = viewModel.toduLandingUiState.testIn(backgroundScope).awaitItem()
            Assert.assertEquals(expect.loading, response.loading)
            Assert.assertEquals(expect.error, response.error)
            Assert.assertEquals(expect.success, response.success)
        }
    }

    @Test
    fun mappLandingUiToDetailUi() = runTest {
        //Given
        val landingUi = mapper.mapTo(getToduListData())
        val detailUi = mapper.mapTo(landingUi.toduListUi.first().todus.first())

        //When
        val mapResult = viewModel.mapLandingUiToDetailUi(landingUi.toduListUi.first().todus.first())

        //Then
        Assert.assertEquals(detailUi, mapResult)
    }

    private fun getToduListData() = listOf(
        ToduItem(
            id = 0,
            userId = 1,
            title = "RealAOF RealHON Stay Together",
            completed = true
        )
    )

    private fun getDetail() = DetailUi(
        id = 0,
        userId = 1,
        user = "User1",
        title = "RealAOF RealHON Stay Together",
        completed = true
    )
}