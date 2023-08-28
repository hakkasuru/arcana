package com.hakkasuru.arcana.debugprefs

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.hakkasuru.arcana.core.arcana.Arcana
import com.hakkasuru.arcana.core.arcana.ArcanaStore
import com.hakkasuru.arcana.ui.model.PreferenceDocument
import com.hakkasuru.arcana.ui.model.PreferenceItem
import com.hakkasuru.arcana.ui.model.PreferenceItemType
import com.hakkasuru.arcana.ui.model.PreferenceRecord
import com.hakkasuru.arcana.ui.model.PreferenceText
import com.hakkasuru.arcana.ui.theme.Typography
import kotlinx.coroutines.launch

@Composable
fun PreferenceScreen(
    navController: NavController,
    cls: Class<*> = Arcana::class.java,
    vm: PreferenceViewModel = hiltViewModel()
) {
    val state = vm.state.collectAsStateWithLifecycle().value

    Box(modifier = Modifier
        .fillMaxSize(1f)
        .padding(8.dp)) {
        Crossfade(targetState = state, label = "Crossfade Preference Screen") {
            when (it) {
                is PreferenceViewModel.State.OnIdle -> Text(text = "idle")
                PreferenceViewModel.State.OnError -> Text(text = "error")
                PreferenceViewModel.State.OnLoading -> Text(text = "loading")
                is PreferenceViewModel.State.ShowArcana -> ContentView(
                    preferences = it.preferences,
                    ArcanaStore(LocalContext.current),
                    navController
                )
            }
        }
    }

    LaunchedEffect(true) {
        vm.submitAction(PreferenceViewModel.Action.FetchArcana(cls))
    }
}

@Composable
private fun ContentView(
    preferences: List<PreferenceItem>,
    store: ArcanaStore,
    navController: NavController
) {
    val scope = rememberCoroutineScope()

    LazyColumn {
        items(preferences) { preference ->
            when (preference.type) {
                PreferenceItemType.TEXT -> {
                    val item = preference as PreferenceText
                    Text(modifier = Modifier.padding(8.dp), text = item.text, style = Typography.titleLarge)
                }
                PreferenceItemType.DOCUMENT -> {
                    val item = preference as PreferenceDocument
                    DocumentComposable(title = item.title, description = item.description) {
                        navController.navigate("preferences/login")
                    }
                }
                PreferenceItemType.RECORD -> {
                    val item = preference as PreferenceRecord

                    val checked = store.getBoolean(
                        booleanPreferencesKey(item.key),
                        item.defaultValue
                    ).collectAsStateWithLifecycle(initialValue = item.defaultValue).value

                    RecordComposable(
                        title = item.title,
                        description = item.description,
                        checked = checked,
                        onToggle = {
                            scope.launch {
                                store.putBoolean(booleanPreferencesKey(item.key), it)
                            }
                        }
                    )
                }
            }
        }
    }
}