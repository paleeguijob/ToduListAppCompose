package realaof.realhon.realha.todulistapp.domain.mapper.todu

import realaof.realhon.realha.todulistapp.data.model.todu.ToduItem
import realaof.realhon.realha.todulistapp.ui.screen.detail.uimodel.DetailUi
import realaof.realhon.realha.todulistapp.ui.screen.todus.uimodel.todulanding.ToduLandingUiState

interface ToduMapper {

    fun mapTo(todus: List<ToduItem>): ToduLandingUiState.ToduLandingUi

    fun mapTo(toduUi: ToduLandingUiState.ToduLandingUi.ToduListUi.ToduUi): DetailUi

    fun mapTo(detailUi: DetailUi): ToduItem
}