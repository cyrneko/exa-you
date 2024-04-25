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

package io.element.android.features.messages.impl.timeline.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import io.element.android.features.messages.impl.timeline.TimelineEvents
import io.element.android.features.messages.impl.timeline.TimelineRoomInfo
import io.element.android.features.messages.impl.timeline.components.virtual.TimelineEncryptedHistoryBannerView
import io.element.android.features.messages.impl.timeline.components.virtual.TimelineItemDaySeparatorView
import io.element.android.features.messages.impl.timeline.components.virtual.TimelineItemReadMarkerView
import io.element.android.features.messages.impl.timeline.components.virtual.TimelineItemRoomBeginningView
import io.element.android.features.messages.impl.timeline.components.virtual.TimelineLoadingMoreIndicator
import io.element.android.features.messages.impl.timeline.model.TimelineItem
import io.element.android.features.messages.impl.timeline.model.virtual.TimelineItemDaySeparatorModel
import io.element.android.features.messages.impl.timeline.model.virtual.TimelineItemEncryptedHistoryBannerVirtualModel
import io.element.android.features.messages.impl.timeline.model.virtual.TimelineItemLastForwardIndicatorModel
import io.element.android.features.messages.impl.timeline.model.virtual.TimelineItemLoadingIndicatorModel
import io.element.android.features.messages.impl.timeline.model.virtual.TimelineItemReadMarkerModel
import io.element.android.features.messages.impl.timeline.model.virtual.TimelineItemRoomBeginningModel

@Composable
fun TimelineItemVirtualRow(
    virtual: TimelineItem.Virtual,
    timelineRoomInfo: TimelineRoomInfo,
    eventSink: (TimelineEvents.EventFromTimelineItem) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        when (virtual.model) {
            is TimelineItemDaySeparatorModel -> TimelineItemDaySeparatorView(virtual.model)
            TimelineItemReadMarkerModel -> TimelineItemReadMarkerView()
            is TimelineItemEncryptedHistoryBannerVirtualModel -> TimelineEncryptedHistoryBannerView()
            TimelineItemRoomBeginningModel -> TimelineItemRoomBeginningView(roomName = timelineRoomInfo.name)
            is TimelineItemLoadingIndicatorModel -> {
                TimelineLoadingMoreIndicator(virtual.model.direction)
                LaunchedEffect(virtual.model.timestamp) {
                    eventSink(TimelineEvents.LoadMore(virtual.model.direction))
                }
            }
            is TimelineItemLastForwardIndicatorModel -> {
                Spacer(modifier = Modifier)
            }
        }
    }
}
