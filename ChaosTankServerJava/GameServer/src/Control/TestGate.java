package Control;

import Network.NetworkCallback;
import Network.ServerNetwork;
import Utility.Debug;
import Utility.DataTranser;
import Module.PlayerOperation;

import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class TestGate {

    public TestGate() {
        ServerNetwork.getInstance().AddCallback(new NetworkCallback() {
            @Override
            public void OnBeginListening(DatagramSocket datagramSocket) {
                Debug.Log("*** Gateway Server On ***");
            }

            @Override
            public boolean OnReceivePackage(DatagramPacket sourcePacket) {
                try {
                    //  Get object of input data
                    PlayerOperation dataIn = DataTranser.ByteToObject(sourcePacket.getData(), PlayerOperation.class);

                    //  TODO

                    //  Send to every client
                    ServerNetwork.getInstance().Send(sourcePacket, DataTranser.ObjectToJsonByte(dataIn));

                } catch (UnsupportedEncodingException e) {
                    Debug.LogWarning("Encode Unsupported, info: " + e.getMessage());
//                    e.printStackTrace();
                }
                return false;
            }

            @Override
            public void OnCloseNetwork(DatagramSocket datagramSocket) {
                Debug.Log("*** Gateway Server Off ***");
            }
        });

        ServerNetwork.getInstance().Start(12345);

        ServerNetwork.getInstance().Close();

    }

}
