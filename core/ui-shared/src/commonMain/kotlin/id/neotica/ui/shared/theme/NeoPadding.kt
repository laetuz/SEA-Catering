package id.neotica.ui.shared.theme

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.ime
//import androidx.compose.foundation.layout.isImeVisible
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

class NeoPadding {
    companion object {

        val statusBarPadding : Dp
            @Composable
            get() = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()

        val systemBarPadding : Dp
            @Composable
            get() = WindowInsets.systemBars.asPaddingValues().calculateBottomPadding()

        @OptIn(ExperimentalMaterial3Api::class)
        val topBarPadding : Dp
            @Composable
            get() = TopAppBarDefaults.TopAppBarExpandedHeight + statusBarPadding

        @OptIn(ExperimentalMaterial3Api::class)
        val topBarOnlyPadding : Dp
            @Composable
            get() = TopAppBarDefaults.TopAppBarExpandedHeight

        private val imePaddingValue
            @Composable
            get() = WindowInsets.ime.asPaddingValues()

//        @OptIn(ExperimentalLayoutApi::class)
//        val topBarImePadding : Dp
////            @Composable
////            get() = if (WindowInsets.isImeVisible && imePaddingValue.calculateBottomPadding() > statusBarPadding) imePaddingValue.calculateBottomPadding() - statusBarPadding + 24.dp
////                else 0.dp
    }

    @Composable
    fun statusBars(): PaddingValues {
        return WindowInsets.statusBars.asPaddingValues()
    }
}