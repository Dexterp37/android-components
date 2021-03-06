/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package mozilla.components.feature.media.service

import android.content.Intent
import androidx.test.ext.junit.runners.AndroidJUnit4
import mozilla.components.browser.session.Session
import mozilla.components.browser.session.SessionManager
import mozilla.components.concept.engine.media.Media
import mozilla.components.feature.media.notification.MediaNotificationFeature
import mozilla.components.feature.media.state.MediaStateMachine
import mozilla.components.feature.media.state.MockMedia
import mozilla.components.support.test.any
import mozilla.components.support.test.mock
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.never
import org.mockito.Mockito.spy
import org.mockito.Mockito.verify
import org.robolectric.Robolectric

@RunWith(AndroidJUnit4::class)
class MediaServiceTest {
    @Test
    fun `Playing state starts service in foreground`() {
        val media = MockMedia(Media.PlaybackState.UNKNOWN)

        val sessionManager = SessionManager(engine = mock()).apply {
            add(Session("https://www.mozilla.org").also { it.media = listOf(media) })
        }

        val stateMachine = MediaStateMachine(sessionManager)
        stateMachine.start()

        val feature = MediaNotificationFeature(mock(), stateMachine)
        feature.enable()

        media.playbackState = Media.PlaybackState.PLAYING

        val service = spy(Robolectric.buildService(MediaService::class.java)
            .create()
            .get())

        service.onStartCommand(Intent(), 0, 0)

        verify(service).startForeground(ArgumentMatchers.anyInt(), any())
    }

    @Test
    fun `Switching from playing to none stops service`() {
        val media = MockMedia(Media.PlaybackState.UNKNOWN)

        val sessionManager = SessionManager(engine = mock()).apply {
            add(Session("https://www.mozilla.org").also { it.media = listOf(media) })
        }

        val stateMachine = MediaStateMachine(sessionManager)
        stateMachine.start()

        val feature = MediaNotificationFeature(mock(), stateMachine)
        feature.enable()

        media.playbackState = Media.PlaybackState.PLAYING

        val service = spy(Robolectric.buildService(MediaService::class.java)
            .create()
            .get())

        service.onStartCommand(Intent(), 0, 0)

        verify(service, never()).stopSelf()

        media.playbackState = Media.PlaybackState.ENDED

        service.onStartCommand(Intent(), 0, 0)

        verify(service).stopSelf()
    }
}
