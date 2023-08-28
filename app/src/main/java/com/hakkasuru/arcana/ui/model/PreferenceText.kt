package com.hakkasuru.arcana.ui.model

data class PreferenceText(
    override val type: PreferenceItemType = PreferenceItemType.TEXT,
    val text: String
) : PreferenceItem
