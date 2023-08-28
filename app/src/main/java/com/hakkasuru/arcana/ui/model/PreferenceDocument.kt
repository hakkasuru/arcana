package com.hakkasuru.arcana.ui.model

data class PreferenceDocument(
    override val type: PreferenceItemType = PreferenceItemType.DOCUMENT,
    val title: String,
    val description: String,
    val cls: Class<*>
) : PreferenceItem
