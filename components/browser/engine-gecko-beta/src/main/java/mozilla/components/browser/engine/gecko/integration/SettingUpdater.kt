package mozilla.components.browser.engine.gecko.integration

abstract class SettingUpdater<T> {
    /**
     * Toggle the automatic tracking of a setting derived from the device state.
     */
    var enabled: Boolean = false
        set(value) {
            if (value) {
                updateValue()
                registerForUpdates()
            } else {
                unregisterForUpdates()
            }
            field = value
        }

    /**
     * The setter for this property should change the GeckoView setting.
     */
    abstract var value: T

    internal fun updateValue() {
        value = findValue()
    }

    /**
     * Register for updates from the device state. This is setting specific.
     */
    abstract fun registerForUpdates()

    /**
     * Unregister for updates from the device state.
     */
    abstract fun unregisterForUpdates()

    /**
     * Find the value of the setting from the device state. This is setting specific.
     */
    abstract fun findValue(): T
}
