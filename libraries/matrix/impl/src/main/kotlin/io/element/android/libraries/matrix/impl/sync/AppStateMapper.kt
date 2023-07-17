/*
 * Copyright (c) 2023 New Vector Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.element.android.libraries.matrix.impl.sync

import io.element.android.libraries.matrix.api.sync.SyncState
import org.matrix.rustcomponents.sdk.AppState
import org.matrix.rustcomponents.sdk.RoomListServiceState

internal fun RoomListServiceState.toSyncState(): SyncState {
    return when (this) {
        RoomListServiceState.INIT,
        RoomListServiceState.SETTING_UP -> SyncState.Idle
        RoomListServiceState.RUNNING -> SyncState.Syncing
        RoomListServiceState.ERROR -> SyncState.InError
        RoomListServiceState.TERMINATED -> SyncState.Terminated
    }
}

internal fun AppState.toSyncState(): SyncState {
    return when (this) {
        AppState.RUNNING -> SyncState.Syncing
        AppState.TERMINATED -> SyncState.Terminated
        AppState.ERROR -> SyncState.InError
    }
}