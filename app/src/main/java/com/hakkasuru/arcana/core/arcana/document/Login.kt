package com.hakkasuru.arcana.core.arcana.document

import com.hakkasuru.arcana.core.arcana.annotation.Document
import com.hakkasuru.arcana.core.arcana.annotation.Record
import kotlinx.coroutines.flow.Flow

@Document("arcana_book", "Login Arcana")
interface Login {

    @Record(
        key = "enable_login",
        title = "Show Login Button",
        description = "Show login button on home screen",
        defaultValue = false
    )
    fun isLoginEnabled(): Flow<Boolean>
}