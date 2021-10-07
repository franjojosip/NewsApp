package hr.fjjukic.common.model

import hr.fjjukic.common.enums.FragmentType

data class MenuUI(
    val title: String,
    val fragmentType: FragmentType,
    val categoryId: Int
)