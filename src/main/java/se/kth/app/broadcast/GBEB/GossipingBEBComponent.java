package se.kth.app.broadcast.GBEB;

import se.kth.app.link.PL_Deliver;
import se.kth.app.link.PerfectLink;
import se.kth.croupier.util.CroupierHelper;
import se.sics.kompics.*;
import se.sics.ktoolbox.croupier.CroupierPort;
import se.sics.ktoolbox.croupier.event.CroupierSample;
import se.sics.ktoolbox.util.network.KAddress;

import java.util.List;

/**
 * Created by victoraxelsson on 2017-04-28.
 */
public class GossipingBEBComponent extends ComponentDefinition {
    protected final Positive<PerfectLink> pLink = requires(PerfectLink.class);
    protected final Negative<GossipingBestEffortBroadcast> gbeb = provides(GossipingBestEffortBroadcast.class);
    protected final Positive<CroupierPort> bs = requires(CroupierPort.class);

    //Fields
    private KAddress selfAdr;

    public GossipingBEBComponent(Init init){
        selfAdr = init.selfAdr;

        subscribe(croupierSampleHandler, bs);
        subscribe(gbeb_broadcastHandler, gbeb);
        subscribe(pl_deliverGBEB_historyRequestClassMatchedHandler, pLink);
        subscribe(gbeb_historyResponsePL_deliverClassMatchedHandler, pLink);
    }


    protected final Handler<CroupierSample> croupierSampleHandler = new Handler<CroupierSample>() {
        @Override
        public void handle(CroupierSample croupierSample) {
            if (croupierSample.publicSample.isEmpty()) {
                return;
            }
            List<KAddress> sample = CroupierHelper.getSample(croupierSample);

            System.out.println("Got a sample");
        }
    };

    protected final Handler<GBEB_Broadcast> gbeb_broadcastHandler = new Handler<GBEB_Broadcast>() {
        @Override
        public void handle(GBEB_Broadcast gbeb_broadcast) {
            System.out.println("gbeb_broadcastHandler");
        }
    };


    protected final ClassMatchedHandler<GBEB_HistoryRequest, PL_Deliver> pl_deliverGBEB_historyRequestClassMatchedHandler = new ClassMatchedHandler<GBEB_HistoryRequest, PL_Deliver>() {
        @Override
        public void handle(GBEB_HistoryRequest gbeb_historyRequest, PL_Deliver pl_deliver) {
            System.out.println("pl_deliverGBEB_historyRequestClassMatchedHandler");
        }
    };


    protected final ClassMatchedHandler<GBEB_HistoryResponse, PL_Deliver>  gbeb_historyResponsePL_deliverClassMatchedHandler = new ClassMatchedHandler<GBEB_HistoryResponse, PL_Deliver>() {
        @Override
        public void handle(GBEB_HistoryResponse gbeb_historyResponse, PL_Deliver pl_deliver) {
            System.out.println("gbeb_historyResponsePL_deliverClassMatchedHandler");
        }
    };

    public static class Init extends se.sics.kompics.Init<GossipingBEBComponent>{
        public final KAddress selfAdr;

        public Init(KAddress selfAdr) {
            this.selfAdr = selfAdr;
        }
    }
}
