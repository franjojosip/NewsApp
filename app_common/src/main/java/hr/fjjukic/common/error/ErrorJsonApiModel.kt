package hr.fjjukic.common.error

data class ErrorJsonApiModel(val status:String, val message:String, val data: Map<String, Any>? = null)
