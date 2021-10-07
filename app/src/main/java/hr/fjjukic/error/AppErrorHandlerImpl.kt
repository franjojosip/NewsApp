package hr.fjjukic.error

import com.google.gson.Gson
import com.google.gson.JsonParseException
import hr.fjjukic.common.error.ErrorJsonApiModel

class AppErrorHandlerImpl(val gson: Gson) {
    fun handleAPIError(errorBody: String?, httpCode: Int) {
        return try {
            val errorObject = gson.fromJson(errorBody, ErrorJsonApiModel::class.java)
            //Handle API error
        } catch (parseException: JsonParseException) {
            //Handle Parse Exception
        }
    }
}