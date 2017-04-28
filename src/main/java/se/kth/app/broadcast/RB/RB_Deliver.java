package se.kth.app.broadcast.RB;

import se.sics.kompics.KompicsEvent;
import se.sics.ktoolbox.util.network.KAddress;

/**
 * Created by victoraxelsson on 2017-04-28.
 */
public class RB_Deliver implements KompicsEvent {

    public final KAddress src;
    public final KompicsEvent payload;

    public RB_Deliver(KAddress src, KompicsEvent payload) {
        this.src = src;
        this.payload = payload;
    }
}
