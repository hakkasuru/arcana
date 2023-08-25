package com.hakkasuru.arcana.core.arcana

import com.hakkasuru.arcana.core.arcana.annotation.Document
import com.hakkasuru.arcana.core.arcana.annotation.Record
import kotlinx.coroutines.flow.Flow

@Document("arcana_book", "Arcana")
interface Arcana {

    @Record(
        key = "show_greeting",
        title = "Enable Greeting",
        description = "Show greeting to user on home screen",
        defaultValue = false
    )
    fun isGreetingEnabled(): Flow<Boolean>
}