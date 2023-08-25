package com.hakkasuru.arcana.core.arcana.annotation

/**
 * Define an Arcana Record
 * @param key Define a key to map record in data store, key cannot have spaces
 * @param title Define a title for this record, this will be shown in arcana content
 * @param description Define a description for this record, this will be show in arcana content
 * */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class Record(
    val key: String,
    val title: String = "",
    val description: String = "",
    val defaultValue: Boolean = false
)
