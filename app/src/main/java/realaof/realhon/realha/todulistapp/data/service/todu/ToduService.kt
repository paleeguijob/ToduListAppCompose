package realaof.realhon.realha.todulistapp.data.service.todu

import realaof.realhon.realha.todulistapp.base.model.BaseCommonError
import realaof.realhon.realha.todulistapp.base.model.NetworkResponse
import realaof.realhon.realha.todulistapp.data.model.todu.ToduItem
import retrofit2.http.GET

interface ToduService {

    @GET("/todos")
    suspend fun getTuduList(): NetworkResponse<List<ToduItem>, BaseCommonError>
}