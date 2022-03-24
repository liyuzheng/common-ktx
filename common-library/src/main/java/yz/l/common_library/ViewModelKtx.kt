package yz.l.common_library

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

inline fun <reified T : Any> Flow<T>.completedIn(
    scope: CoroutineScope,
    crossinline block: (d: T) -> Unit
) {
    scope.launch {
        this@completedIn.collectLatest {
            block(it)
        }
    }
}
