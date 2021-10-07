package hr.fjjukic.common.repository

import android.content.res.Resources
import androidx.annotation.StringRes

interface ResourceRepository {

    fun getString(@StringRes stringResId: Int): String
    fun getFormattedString(@StringRes stringResId: Int, vararg params: Any): String
    fun getResources(): Resources
}

open class ResourcesRepositoryImpl(private val resources: Resources) :
    ResourceRepository {

    override fun getString(@StringRes stringResId: Int): String = resources.getString(stringResId)

    override fun getFormattedString(@StringRes stringResId: Int, vararg params: Any) =
        resources.getString(stringResId, params)

    override fun getResources(): Resources = resources
}