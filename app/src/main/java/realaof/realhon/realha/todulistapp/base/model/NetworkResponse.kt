package realaof.realhon.realha.todulistapp.base.model

import java.io.IOException

sealed class NetworkResponse<out T, out U> {

    data class Success<T>(val body: T) : NetworkResponse<T, Nothing>()
    data class ApiError<U>(val body: U, val code: Int) : NetworkResponse<Nothing, U>()
    data class NetworkError(val error: IOException) : NetworkResponse<Nothing, Nothing>()
    data class OtherError(val error: Throwable) : NetworkResponse<Nothing, Nothing>()
}

fun <T : Any, U : Any> NetworkResponse<T, U>.toDataOrThrow(): T = when (this) {
    is NetworkResponse.Success -> body
    is NetworkResponse.ApiError -> throw getApiError(body, code)
    is NetworkResponse.NetworkError -> throw BaseCommonError.NetworkError(error)
    is NetworkResponse.OtherError -> throw BaseCommonError.OtherError(error)
}

private fun getApiError(error: Any, httpCode: Int) = BaseCommonError.ApiError(
    httpCode = httpCode,
    code = httpCode,
    title = null,
    message = BaseCommonError.DEFAULT_API_ERROR_MESSAGE,
    payload = error
)