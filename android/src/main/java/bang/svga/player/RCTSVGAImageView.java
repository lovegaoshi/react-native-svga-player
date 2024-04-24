package bang.svga.player;

import android.content.Context;
import android.util.AttributeSet;

import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.uimanager.UIManagerHelper;
import com.opensource.svgaplayer.SVGACallback;
import com.opensource.svgaplayer.SVGAImageView;

import bang.svga.player.events.RNSvgaOnFinishedEvent;
import bang.svga.player.events.RNSvgaOnFrameEvent;
import bang.svga.player.events.RNSvgaOnPercentageEvent;

public class RCTSVGAImageView extends SVGAImageView {
    protected String currentState;
    public RCTSVGAImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.setCallback(new SVGACallback() {

            @Override
            public void onStep(int frame, double percentage) {
                ReactContext reactContext = (ReactContext) getContext();
                if (!reactContext.hasActiveReactInstance()) {
                    return;
                }
                int id = getId();
                EventDispatcher dispatcher =
                        UIManagerHelper.getEventDispatcherForReactTag(reactContext, id);
                int surfaceId = UIManagerHelper.getSurfaceId(reactContext);
                if (dispatcher != null) {
                    dispatcher.dispatchEvent(
                            new RNSvgaOnFrameEvent(surfaceId, id,  frame)
                    );
                    dispatcher.dispatchEvent(
                            new RNSvgaOnPercentageEvent(surfaceId, id,  percentage)
                    );
                }
            }

            @Override
            public void onRepeat() {

            }

            @Override
            public void onPause() {

            }

            @Override
            public void onFinished() {
                ReactContext reactContext = (ReactContext) getContext();
                if (!reactContext.hasActiveReactInstance()) {
                    return;
                }
                int id = getId();
                EventDispatcher dispatcher =
                        UIManagerHelper.getEventDispatcherForReactTag(reactContext, id);
                int surfaceId = UIManagerHelper.getSurfaceId(reactContext);
                if (dispatcher != null) {
                    dispatcher.dispatchEvent(
                            new RNSvgaOnFinishedEvent(surfaceId, id)
                    );
                }
            }
        });
    }

}
