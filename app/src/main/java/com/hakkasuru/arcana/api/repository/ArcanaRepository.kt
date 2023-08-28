package com.hakkasuru.arcana.api.repository

import com.hakkasuru.arcana.core.arcana.Arcana
import com.hakkasuru.arcana.core.arcana.annotation.Document
import com.hakkasuru.arcana.core.arcana.annotation.Record
import com.hakkasuru.arcana.ui.model.PreferenceDocument
import com.hakkasuru.arcana.ui.model.PreferenceItem
import com.hakkasuru.arcana.ui.model.PreferenceRecord
import com.hakkasuru.arcana.ui.model.PreferenceText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ArcanaRepository {

    fun fetchArcana(cls: Class<*>): Flow<List<PreferenceItem>> = flow {
        val items: MutableList<PreferenceItem> = mutableListOf()

        val parentDocument = cls.getAnnotation(Document::class.java)
            ?: throw Throwable("class $cls is not an interface or is missing @Document annotation")

        items.add(PreferenceText(text = parentDocument.title))

        cls.methods.sortedBy { it.annotations.isNotEmpty() }.forEach { method ->
            method.returnType.getAnnotation(Document::class.java)?.let { document ->
                items.add(PreferenceDocument(
                    title = document.title,
                    description = document.description,
                    cls = method.returnType
                ))
            }

            method.getAnnotation(Record::class.java)?.let { record ->
                items.add(PreferenceRecord(
                    title = record.title,
                    description = record.description,
                    key = "${parentDocument.key}_${record.key}",
                    defaultValue = record.defaultValue
                ))
            }
        }

        emit(items)
    }
}