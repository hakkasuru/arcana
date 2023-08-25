package com.hakkasuru.arcana.core.arcana.annotation

/**
 * Define an Arcana Document
 * @param key Define a key to map records to this document, key cannot have spaces
 * @param title Define a title for this document, this will be shown in arcana content
 * @param description Define a description for this document, this will be show in arcana content
 * */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
annotation class Document(
    val key: String,
    val title: String,
    val description: String = "",
)
