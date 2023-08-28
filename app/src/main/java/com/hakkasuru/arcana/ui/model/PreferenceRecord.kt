package com.hakkasuru.arcana.ui.model

data class PreferenceRecord(
    override val type: PreferenceItemType = PreferenceItemType.RECORD,
    val title: String,
    val description: String,
    val key: String,
    val defaultValue: Boolean
) : PreferenceItem