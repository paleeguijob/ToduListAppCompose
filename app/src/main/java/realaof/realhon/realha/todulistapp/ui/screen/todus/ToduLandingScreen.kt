package realaof.realhon.realha.todulistapp.ui.screen.todus

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import realaof.realhon.realha.todulistapp.R
import realaof.realhon.realha.todulistapp.base.compose.error.BaseErrorScreen
import realaof.realhon.realha.todulistapp.base.compose.loading.BaseLoading
import realaof.realhon.realha.todulistapp.base.compose.search.SearchBar
import realaof.realhon.realha.todulistapp.base.compose.search.rememberEditableSearchInputState
import realaof.realhon.realha.todulistapp.base.model.BaseCommonError
import realaof.realhon.realha.todulistapp.base.model.enum.todu.FilterType
import realaof.realhon.realha.todulistapp.ui.screen.detail.ToduDetailBottomSheetScreen
import realaof.realhon.realha.todulistapp.ui.screen.todus.compose.list.FilterList
import realaof.realhon.realha.todulistapp.ui.screen.todus.compose.list.ToduList
import realaof.realhon.realha.todulistapp.ui.screen.todus.uimodel.button.StatusState
import realaof.realhon.realha.todulistapp.ui.screen.todus.uimodel.button.rememberStatusButtonState
import realaof.realhon.realha.todulistapp.ui.screen.todus.uimodel.todulanding.ToduLandingUiState
import realaof.realhon.realha.todulistapp.ui.theme.dimen

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ToduLandingScreen(
    modifier: Modifier = Modifier,
    viewModel: ToduLandingViewModel = hiltViewModel()
) {

    //First call the api
    rememberSaveable(saver = ToduLandingUiState.Saver) {
        viewModel.getToduList()
        ToduLandingUiState(loading = true)
    }

    val uiState by viewModel.toduLandingUiState.collectAsStateWithLifecycle()

    val scope = rememberCoroutineScope()
    val keyboardController = LocalSoftwareKeyboardController.current

    val searchInputState = rememberEditableSearchInputState(
        hint = stringResource(id = R.string.realha_todu_list_common_search_bar_hint_search),
        keyword = ""
    )

    val statusButtonState = rememberStatusButtonState(
        buttonStatusState = uiState.landingUiState.success?.filtering?.first()
            ?: StatusState(status = FilterType.ALL, selected = true)
    )

    var showBottomSheet by rememberSaveable { mutableStateOf(false) }

    val bottomSheetState = rememberModalBottomSheetState()

    var toduUiClicked by remember {
        mutableStateOf(ToduLandingUiState.ToduLandingUi.ToduListUi.ToduUi())
    }

    Surface(modifier = modifier.fillMaxSize()) {

        Column(
            modifier = modifier
                .background(Color.White)
        ) {
            //Search Bar
            SearchBar(
                state = searchInputState,
                onSearchAction = { newText ->
                    scope.launch {
                        viewModel.search(newText, statusButtonState.statusType)
                        keyboardController?.hide()
                    }
                },
                onValueChange = { newText ->
                    scope.launch {
                        viewModel.search(newText, statusButtonState.statusType)
                    }
                },
                onClickedClearText = {
                    scope.launch {
                        viewModel.search(searchInputState.text, statusButtonState.statusType)
                        keyboardController?.hide()
                    }
                },
                modifier = Modifier
                    .padding(horizontal = dimen.dimen_16)
                    .padding(top = dimen.dimen_32, bottom = dimen.dimen_16)
            )

            Divider(modifier = Modifier.fillMaxWidth())

            //Todu List Content
            WrapToduLandingContent(
                toduLandingUiState = uiState,
                modifier = Modifier.padding(horizontal = dimen.dimen_16),
                onClickedStatus = {
                    scope.launch {
                        statusButtonState.updateStatusType(it.statusType)
                        statusButtonState.updateSelected(it.isSelected)

                        viewModel.search(searchInputState.text, it.status)
                    }
                },
                onToduItemClicked = {
                    toduUiClicked = it
                    showBottomSheet = true
                }
            )
        }

        //Detail BottomSheet
        if (showBottomSheet) {
            ToduDetailBottomSheetScreen(
                detailUi = viewModel.mapLandingUiToDetailUi(toduUiClicked),
                bottomSheetState = bottomSheetState,
                onDismissRequest = { dismiss ->
                    scope.launch {
                        bottomSheetState.hide()
                        delay(200)
                    }.invokeOnCompletion {
                        if (bottomSheetState.isVisible.not()) {
                            showBottomSheet = dismiss
                        }
                    }
                },
                onClickedSave = {
                    scope.launch {
                        viewModel.insertDetail(
                            detailUi = it,
                            keyword = searchInputState.text,
                            status = statusButtonState.statusType
                        )
                    }
                },
                onDoneAction = {}
            )
        }
    }
}

@Composable
fun WrapToduLandingContent(
    modifier: Modifier = Modifier,
    toduLandingUiState: ToduLandingUiState,
    onClickedStatus: (StatusState) -> Unit = {},
    onToduItemClicked: (ToduLandingUiState.ToduLandingUi.ToduListUi.ToduUi) -> Unit = {}
) {
    val state = toduLandingUiState.landingUiState

    when {
        state.loading != false -> BaseLoading(modifier = modifier)

        state.error != null -> BaseErrorScreen(
            title = stringResource(id = R.string.realha_todu_list_common_error_title),
            message = state.error.message.orEmpty()
        )

        state.success != null -> ToduLandingContent(
            toduListUi = state.success.toduListUi,
            filtering = state.success.filtering,
            onClickedStatus = onClickedStatus,
            onToduItemClicked = onToduItemClicked,
            modifier = modifier
        )
    }
}

@Composable
fun ToduLandingContent(
    modifier: Modifier = Modifier,
    toduListUi: List<ToduLandingUiState.ToduLandingUi.ToduListUi>,
    filtering: List<StatusState>,
    onClickedStatus: (StatusState) -> Unit = {},
    onToduItemClicked: (ToduLandingUiState.ToduLandingUi.ToduListUi.ToduUi) -> Unit = {}
) {
    //Filtering by status
    FilterList(
        filtering = filtering,
        onClickedButton = onClickedStatus,
        modifier = modifier.padding(top = dimen.dimen_16)
    )

    //Todu List
    ToduList(
        toduListUi = toduListUi,
        onToduItemClicked = onToduItemClicked,
        modifier = modifier
    )
}

@Preview
@Composable
private fun ToduLandingScreenPreview() {
    ToduLandingScreen()
}