package se.kth.app.broadcast.CB;

import se.kth.app.broadcast.RB.Data;
import se.kth.app.broadcast.RB.RB_Deliver;
import se.kth.app.broadcast.RB.ReliableBroadcast;
import se.sics.kompics.*;
import se.sics.ktoolbox.util.network.KAddress;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by victoraxelsson on 2017-04-28.
 */
public class NoWaitingCausalBroadcast extends ComponentDefinition {
    Negative<CausalOrderReliableBroadcast> cb = provides(CausalOrderReliableBroadcast.class);
    Positive<ReliableBroadcast> rb = requires(ReliableBroadcast.class);

    private KAddress selfAdr;
    Set<Data> delivered;
    List<Data> past;

    public NoWaitingCausalBroadcast(Init init){
        this.selfAdr = init.selfAdr;

        delivered = new HashSet<>();
        past = new ArrayList<>();

        subscribe(cb_broadcastHandler, cb);
        subscribe(dataRB_deliverClassMatchedHandler, rb);
    }

    protected final Handler<CB_Broadcast> cb_broadcastHandler = new Handler<CB_Broadcast>() {
        @Override
        public void handle(CB_Broadcast cb_broadcast) {
            System.out.println("CB broadcasting");
        }
    };

    protected final ClassMatchedHandler<Data, RB_Deliver> dataRB_deliverClassMatchedHandler = new ClassMatchedHandler<Data, RB_Deliver>() {
        @Override
        public void handle(Data data, RB_Deliver rb_deliver) {
            System.out.println("CB delivering");
        }
    };


    public static class Init extends se.sics.kompics.Init<NoWaitingCausalBroadcast>{

        public final KAddress selfAdr;

        public Init(KAddress selfAdr) {
            this.selfAdr = selfAdr;
        }
    }
}
