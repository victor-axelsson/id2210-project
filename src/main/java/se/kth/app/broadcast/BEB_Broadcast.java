package se.kth.app.broadcast;

import se.sics.kompics.KompicsEvent;
import se.sics.ktoolbox.util.network.KAddress;

import java.util.Set;

/**
 * Created by victoraxelsson on 2017-04-26.
 */
public class BEB_Broadcast implements KompicsEvent{

    public final KompicsEvent payload;
    public final Set<KAddress> procs;

    public BEB_Broadcast(KompicsEvent payload, Set<KAddress> procs) {
        this.payload = payload;
        this.procs = procs;
    }


}
