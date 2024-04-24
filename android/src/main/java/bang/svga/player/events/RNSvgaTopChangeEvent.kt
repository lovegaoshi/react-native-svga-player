package bang.svga.player.events

import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.WritableMap
import com.facebook.react.uimanager.events.Event

class RNSvgaOnFrameEvent (
    surfaceId: Int,
    viewTag: Int,
    private val value: Int
) : Event<RNSvgaOnFrameEvent>(surfaceId, viewTag) {

    override fun getEventName(): String = "topChange"
    override fun getEventData(): WritableMap = Arguments.createMap().apply {
        putString("action", "onFrame")
        putInt("value", value)
    }
}

class RNSvgaOnPercentageEvent (
    surfaceId: Int,
    viewTag: Int,
    private val value: Double
) : Event<RNSvgaOnPercentageEvent>(surfaceId, viewTag) {

    override fun getEventName(): String = "topChange"
    override fun getEventData(): WritableMap = Arguments.createMap().apply {
        putString("action", "onPercentage")
        putDouble("value", value)
    }
}

class RNSvgaOnFinishedEvent (
    surfaceId: Int,
    viewTag: Int,
) : Event<RNSvgaOnFinishedEvent>(surfaceId, viewTag) {

    override fun getEventName(): String = "topChange"
    override fun getEventData(): WritableMap = Arguments.createMap().apply {
        putString("action", "onFinished")
    }
}