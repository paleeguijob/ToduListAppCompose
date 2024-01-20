package realaof.realhon.realha.todulistapp.data.mapper.todu

import realaof.realhon.realha.todulistapp.data.model.todu.ToduItem

interface ToduLocalDataMapper {

    fun mapDataTo(list: List<ToduItem>): List<ToduItem>
}