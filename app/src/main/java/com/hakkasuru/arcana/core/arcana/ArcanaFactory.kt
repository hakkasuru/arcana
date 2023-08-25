package com.hakkasuru.arcana.core.arcana

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import com.hakkasuru.arcana.core.arcana.annotation.Document
import com.hakkasuru.arcana.core.arcana.annotation.Record
import kotlinx.coroutines.flow.collect
import java.lang.reflect.Proxy

class ArcanaFactory {
    companion object {
        /**
         * Creates an instance of document interface, interface must be annotated with [Document]
         * */
        inline fun <reified T : Any> create(context: Context): T {
            val cls = T::class.java
            val document = cls.getAnnotation(Document::class.java)
            require(cls.isInterface && document != null) {
                "class $cls is not an interface or is missing @Document annotation"
            }

            val dataStore = ArcanaStore(context)

            return Proxy.newProxyInstance(
                context.classLoader,
                arrayOf(cls)
            ) { _, method, _ ->
                val flag = method.getAnnotation(Record::class.java) ?: return@newProxyInstance false
                val key = booleanPreferencesKey(flag.key)
                return@newProxyInstance dataStore.getBoolean(key, flag.defaultValue)
            } as T
        }
    }
}