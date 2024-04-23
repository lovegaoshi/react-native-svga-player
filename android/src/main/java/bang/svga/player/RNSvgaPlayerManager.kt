package bang.svga.player

import com.facebook.react.uimanager.SimpleViewManager
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.annotations.ReactProp
import com.opensource.svgaplayer.SVGAParser
import com.opensource.svgaplayer.SVGAParser.ParseCompletion
import com.opensource.svgaplayer.SVGAVideoEntity
import java.net.URL

class RNSvgaPlayerManager : SimpleViewManager<RCTSVGAImageView>() {
    override fun getName(): String {
        return REACT_CLASS
    }

    public override fun createViewInstance(c: ThemedReactContext): RCTSVGAImageView {
        return RCTSVGAImageView(c, null, 0)
    }

    @ReactProp(name = "source")
    fun setSource(view: RCTSVGAImageView, source: String) {
        val context = view.context
        if (source.startsWith("http") || source.startsWith("https")) {
            try {
                SVGAParser(context).parse(URL(source), object : ParseCompletion {
                    override fun onError() {}
                    override fun onComplete(videoItem: SVGAVideoEntity) {
                        view.setVideoItem(videoItem)
                        view.startAnimation()
                    }
                })
            } catch (e: Exception) {
            }
        } else {
            try {
                SVGAParser(context).parse(source, object : ParseCompletion {
                    override fun onError() {}
                    override fun onComplete(videoItem: SVGAVideoEntity) {
                        view.setVideoItem(videoItem)
                        view.startAnimation()
                    }
                })
            } catch (e: Exception) {
            }
        }
    }

    @ReactProp(name = "loops", defaultInt = 0)
    fun setLoops(view: RCTSVGAImageView, loops: Int) {
        view.loops = loops
    }

    @ReactProp(name = "clearsAfterStop", defaultBoolean = true)
    fun setClearsAfterStop(view: RCTSVGAImageView, clearsAfterStop: Boolean?) {
        view.clearsAfterStop = clearsAfterStop!!
    }

    @ReactProp(name = "currentState")
    fun setCurrentState(view: RCTSVGAImageView, currentState: String?) {
        view.currentState = currentState
        when (currentState) {
            "start" -> view.startAnimation()
            "pause" -> view.pauseAnimation()
            "stop" -> view.stopAnimation()
            "clear" -> view.stopAnimation(true)
            else -> {}
        }
    }

    @ReactProp(name = "toFrame", defaultInt = -1)
    fun setToFrame(view: RCTSVGAImageView, toFrame: Int) {
        if (toFrame < 0) {
            return
        }
        view.stepToFrame(toFrame, if (view.currentState == "play") true else false)
    }

    @ReactProp(name = "toPercentage", defaultFloat = -1.0f)
    fun setToPercentage(view: RCTSVGAImageView, toPercentage: Float) {
        if (toPercentage < 0) {
            return
        }
        view.stepToPercentage(
            toPercentage.toDouble(),
            if (view.currentState == "play") true else false
        )
    }

    companion object {
        const val REACT_CLASS = "RNSvgaPlayer"
    }
}
