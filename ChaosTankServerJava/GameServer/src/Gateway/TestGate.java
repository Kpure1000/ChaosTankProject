package Gateway;

import Network.NetworkCallBack;
import Network.ServerNetwork;
import Utility.Debug;
import Utility.JsonTool;
import Module.TestData;

import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class TestGate {

    public TestGate() {
        ServerNetwork.getInstance().AddCallBack(new NetworkCallBack() {
            @Override
            public void OnBeginListening(DatagramSocket datagramSocket) {
                Debug.Log("*** Gateway Server On ***");
            }

            @Override
            public boolean OnReceivePackage(DatagramPacket datagramPacket) {
                try {
//                    TestData data = JsonTool.Parse(datagramPacket.getData(), TestData.class);
//                    Debug.Log(">>Client " + datagramPacket.getAddress() + ":" +
//                            datagramPacket.getPort() + " :" + data);
                    var jsonStr = JsonTool.ByteToString(datagramPacket.getData());
                    Debug.Log(">>Client: " + jsonStr);
                    ServerNetwork.getInstance().Send(datagramPacket, JsonTool.Parse(jsonStr.getBytes(), TestData.class));
                } catch (UnsupportedEncodingException e) {
                    Debug.LogWarning("Encode Unsupported, info: " + e.getMessage());
//                    e.printStackTrace();
                }
                return true;
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
