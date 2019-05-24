/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package mozilla.components.service.glean

import android.content.Context
import kotlinx.coroutines.Job
import mozilla.components.service.glean.config.Configuration
import mozilla.telemetry.glean.Glean

/**
 * In contrast with other glean-ac classes (i.e. Configuration), we can't
 * use typealias to export mozilla.telemetry.glean.Glean, as we need migration
 * code to be called whenever `initialize` is called. Moreover, we can't simply
 * delegate other methods or inherit, since that doesn't work for `object` in
 * Kotlin.
 */
object Glean {
    /**
     * Initialize Glean.
     *
     * This should only be initialized once by the application, and not by
     * libraries using Glean. A message is logged to error and no changes are made
     * to the state if initialize is called a more than once.
     *
     * A LifecycleObserver will be added to send pings when the application goes
     * into the background.
     *
     * @param applicationContext [Context] to access application features, such
     * as shared preferences
     * @param configuration A Glean [Configuration] object with global settings.
     */
    fun initialize(
        applicationContext: Context,
        configuration: Configuration = Configuration()
    ) {
        assert(false) {"Not implemented"}
    }

    /**
     * Returns true if the Glean library has been initialized.
     */
    fun isInitialized(): Boolean {
        assert(false) {"Not implemented"}
        return false
    }

    /**
     * Register the pings generated from `pings.yaml` with Glean.
     *
     * @param pings The `Pings` object generated for your library or application
     * by Glean.
     */
    fun registerPings(pings: Any) {
        assert(false) {"Not implemented"}
    }

    /**
     * Enable or disable Glean collection and upload.
     *
     * Metric collection is enabled by default.
     *
     * When disabled, metrics aren't recorded at all and no data
     * is uploaded.
     *
     * @param enabled When true, enable metric collection.
     */
    fun setUploadEnabled(enabled: Boolean) {
        assert(false) {"Not implemented"}
    }

    /**
     * Get whether or not Glean is allowed to record and upload data.
     */
    fun getUploadEnabled(): Boolean {
        assert(false) {"Not implemented"}
        return false
    }

    /**
     * Indicate that an experiment is running. Glean will then add an
     * experiment annotation to the environment which is sent with pings. This
     * information is not persisted between runs.
     *
     * @param experimentId The id of the active experiment (maximum
     *     30 bytes)
     * @param branch The experiment branch (maximum 30 bytes)
     * @param extra Optional metadata to output with the ping
     */
    fun setExperimentActive(
        experimentId: String,
        branch: String,
        extra: Map<String, String>? = null
    ) {
        assert(false) {"Not implemented"}
    }

    /**
     * Indicate that an experiment is no longer running.
     *
     * @param experimentId The id of the experiment to deactivate.
     */
    fun setExperimentInactive(experimentId: String) {
        assert(false) {"Not implemented"}
    }

    /**
     * Handle the background event and send the appropriate pings.
     */
    fun handleBackgroundEvent() {
        assert(false) {"Not implemented"}
    }

    /**
     * Send a list of pings by name.
     *
     * Each ping will be looked up in the known instances of [PingType]. If the
     * ping isn't known, an error is logged and the ping isn't queued for uploading.
     *
     * The ping content is assembled as soon as possible, but upload is not
     * guaranteed to happen immediately, as that depends on the upload
     * policies.
     *
     * If the ping currently contains no content, it will not be sent.
     *
     * @param pingNames List of ping names to send.
     * @return The async Job performing the work of assembling the ping
     */
    internal fun sendPingsByName(pingNames: List<String>): Job? {
        assert(false) {"Not implemented"}
        return null
    }
}
