package com.hakkasuru.arcana.core.arcana

import com.hakkasuru.arcana.core.arcana.annotation.Document
import com.hakkasuru.arcana.core.arcana.annotation.Record
import com.hakkasuru.arcana.core.arcana.document.Login
import kotlinx.coroutines.flow.Flow

@Document("arcana_book", "Arcana")
interface Arcana {

    fun showLogin(): Login

    @Record(
        key = "enable_greeting",
        title = "Enable Greeting",
        description = "Show greeting to user on home screen",
        defaultValue = false
    )
    fun isGreetingEnabled(): Flow<Boolean>
}