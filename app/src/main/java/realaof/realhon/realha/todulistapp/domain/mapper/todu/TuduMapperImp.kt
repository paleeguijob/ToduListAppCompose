package realaof.realhon.realha.todulistapp.domain.mapper.todu

import androidx.compose.ui.graphics.Color
import realaof.realhon.realha.todulistapp.base.model.enum.todu.FilterType
import realaof.realhon.realha.todulistapp.data.model.todu.ToduItem
import realaof.realhon.realha.todulistapp.ui.screen.detail.uimodel.DetailUi
import realaof.realhon.realha.todulistapp.ui.screen.todus.uimodel.button.StatusState
import realaof.realhon.realha.todulistapp.ui.screen.todus.uimodel.todulanding.ToduLandingUiState
import realaof.realhon.realha.todulistapp.ui.theme.Malachite
import realaof.realhon.realha.todulistapp.ui.theme.TreePoppy
import javax.inject.Inject

class TuduMapperImp @Inject constructor() : ToduMapper {

    override fun mapTo(todus: List<ToduItem>): ToduLandingUiState.ToduLandingUi =
        ToduLandingUiState.ToduLandingUi(
            toduListUi = mapToduList(todus),
            filtering = statusList()
        )

    override fun mapTo(toduUi: ToduLandingUiState.ToduLandingUi.ToduListUi.ToduUi): DetailUi =
        DetailUi(
            id = toduUi.id,
            userId = toduUi.userId,
            user = toduUi.user,
            title = toduUi.title,
            completed = mapType(toduUi.status.status)
        )

    override fun mapTo(detailUi: DetailUi): ToduItem =
        ToduItem(
            id = detailUi.id,
            userId = detailUi.userId,
            title = detailUi.title,
            completed = detailUi.completed
        )

    private fun mapToduList(todus: List<ToduItem>): List<ToduLandingUiState.ToduLandingUi.ToduListUi> =
        todus.groupBy { it.userId }
            .map { item ->
                ToduLandingUiState.ToduLandingUi.ToduListUi(
                    user = "User ${item.key}",
                    todus = item.value.map { todu ->
                        ToduLandingUiState.ToduLandingUi.ToduListUi.ToduUi(
                            id = todu.id,
                            userId = todu.userId,
                            user = "User ${item.key}",
                            title = todu.title,
                            status = ToduLandingUiState.ToduLandingUi.ToduListUi.ToduUi.ToduStatus(
                                status = mapStatus(todu.completed),
                                color = mapColorStatus(mapStatus(todu.completed))
                            )
                        )
                    }
                )
            }

    private fun mapStatus(isComplete: Boolean?): FilterType =
        when (isComplete) {
            true -> FilterType.DONE
            false -> FilterType.ACTIVE
            else -> FilterType.ALL
        }

    private fun mapType(filterType: FilterType): Boolean =
        when (filterType) {
            FilterType.ALL -> true
            FilterType.ACTIVE -> false
            FilterType.DONE -> true
        }

    private fun mapColorStatus(status: FilterType): Color =
        when (status) {
            FilterType.DONE -> Malachite
            FilterType.ACTIVE -> TreePoppy
            else -> Malachite
        }

    private fun statusList() = listOf(
        StatusState(
            status = FilterType.ALL,
            selected = true
        ),
        StatusState(
            status = FilterType.ACTIVE,
            selected = false
        ),
        StatusState(
            status = FilterType.DONE,
            selected = false
        )
    )
}