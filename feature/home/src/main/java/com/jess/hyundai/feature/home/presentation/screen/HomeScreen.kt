package com.jess.hyundai.feature.home.presentation.screen

import android.content.Intent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jess.hyundai.feature.home.R
import com.jess.hyundai.navigator.Direction
import com.jess.hyundai.ui.component.JessAppBar
import com.jess.hyundai.ui.component.JessTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun HomeScreen(
    getIntent: (Direction) -> Intent,
    onBackPress: () -> Unit,
    modifier: Modifier = Modifier,
) {

    val context = LocalContext.current
    var query by remember { mutableStateOf("motors") }

    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        topBar = {
            JessAppBar(
                title = {
                    Text(stringResource(id = R.string.home_title))
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBackPress,
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(id = R.string.home_back)
                        )
                    }
                }
            )
        }
    ) { innerPadding ->

        val keyboardController = LocalSoftwareKeyboardController.current

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .imePadding()

        ) {
            Box(
                modifier = Modifier
                    .padding(24.dp)
                    .wrapContentSize(Alignment.Center)
                    .weight(1f),
            ) {
                JessTextField(
                    value = query,
                    onValueChange = {
                        query = it
                    },
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = stringResource(id = R.string.home_search)
                        )
                    },
                    singleLine = true,
                )
            }

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                onClick = {
                    keyboardController?.hide()

                    if (query.isNotEmpty()) {
                        context.startActivity(
                            getIntent(Direction.SearchResult(query))
                        )
                    }
                },
            ) {
                Text(text = stringResource(id = R.string.home_search))
            }
        }
    }
}