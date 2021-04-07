package Control;

import Network.NetworkCallback;
import Network.ServerNetwork;
import Utility.Debug;
import Utility.DataTranser;
import Module.PlayerOperation;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * Receive all data here
 */
public class GatewayIn {

    public GatewayIn() {
        // Add network callback
        ServerNetwork.getInstance().AddCallback(new NetworkCallback() {
            @Override
            public void OnBeginListening(DatagramSocket datagramSocket) {
                Debug.Log("*** Gateway >>> Start listening ***");
            }

            @Override
            public boolean OnReceivePackage(DatagramPacket sourcePacket) {
                OnReceiveAnyInput(sourcePacket);
                return false;
            }

            @Override
            public void OnCloseNetwork(DatagramSocket datagramSocket) {
                Debug.Log("*** Gateway >>> Stop listening ***");
            }
        });
    }

    public void Open(GatewayOut gatewayOut) {
        this.gatewayOut = gatewayOut;
        ServerNetwork.getInstance().Start(serverPort);
    }

    public void OnReceiveAnyInput(DatagramPacket sourceP) {
        PlayerOperation operationIn = DataTranser.ByteToObject(sourceP.getData(), PlayerOperation.class);
        gatewayOut.OperationIn(sourceP, operationIn);
    }

    public void Close() {
        ServerNetwork.getInstance().Close();
    }

    final private int serverPort = 12345;

    private GatewayOut gatewayOut;

}
