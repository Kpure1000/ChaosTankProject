package Control;

import Module.PlayerOperation;
import Module.FrameMatchPacket;
import Network.ServerNetwork;
import Utility.DataTranser;

import java.net.DatagramPacket;

public class GatewayOut {
    public GatewayOut() {
        //
    }

    public void OperationIn(DatagramPacket sourceP, PlayerOperation operationIn) {
        FrameMatchPacket frameOut = new FrameMatchPacket();
        ServerNetwork.getInstance().Send(sourceP, DataTranser.ObjectToJsonByte(frameOut));
    }


}
