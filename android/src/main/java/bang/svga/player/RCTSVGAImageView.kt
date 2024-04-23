package bang.svga.player

import android.content.Context
import android.util.AttributeSet
import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.ReactContext
import com.facebook.react.uimanager.events.RCTEventEmitter
import com.opensource.svgaplayer.SVGACallback
import com.opensource.svgaplayer.SVGAImageView

class RCTSVGAImageView(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : SVGAImageView(
    context!!, attrs, defStyleAttr
) {
    @JvmField
    var currentState: String? = null

    init {
        callback = object : SVGACallback {
            override fun onStep(frame: Int, percentage: Double) {
                val changeMap = Arguments.createMap()
                changeMap.putString("action", "onFrame")
                changeMap.putInt("value", frame)
                val reactContext = getContext() as ReactContext
                reactContext.getJSModule(RCTEventEmitter::class.java).receiveEvent(
                    id, "topChange", changeMap
                )
                val map = Arguments.createMap()
                map.putString("action", "onPercentage")
                map.putDouble("value", percentage)
                reactContext.getJSModule(RCTEventEmitter::class.java).receiveEvent(
                    id, "topChange", map
                )
            }

            override fun onRepeat() {}
            override fun onPause() {}
            override fun onFinished() {
                val map = Arguments.createMap()
                map.putString("action", "onFinished")
                val reactContext = getContext() as ReactContext
                reactContext.getJSModule(RCTEventEmitter::class.java).receiveEvent(
                    id, "topChange", map
                )
            }
        }
    }
}
