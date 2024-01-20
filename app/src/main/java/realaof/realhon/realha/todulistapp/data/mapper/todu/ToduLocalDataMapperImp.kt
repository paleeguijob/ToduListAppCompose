package realaof.realhon.realha.todulistapp.data.mapper.todu

import realaof.realhon.realha.todulistapp.data.model.todu.ToduItem
import javax.inject.Inject

class ToduLocalDataMapperImp @Inject constructor() : ToduLocalDataMapper {

    override fun mapDataTo(list: List<ToduItem>): List<ToduItem> =
        list.map {
            ToduItem(
                it.id,
                it.userId,
                "User${it.userId}",
                it.completed
            )
        }
}