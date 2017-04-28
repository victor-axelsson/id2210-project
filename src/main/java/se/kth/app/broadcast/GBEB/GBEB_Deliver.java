package se.kth.app.broadcast.GBEB;

import se.sics.kompics.KompicsEvent;
import se.sics.ktoolbox.util.network.KAddress;

/**
 * Created by victoraxelsson on 2017-04-28.
 */
public class GBEB_Deliver implements KompicsEvent {

    public final KAddress pp;
    public final KompicsEvent m;


    public GBEB_Deliver(KAddress pp, KompicsEvent m) {
        this.pp = pp;
        this.m = m;
    }


}
