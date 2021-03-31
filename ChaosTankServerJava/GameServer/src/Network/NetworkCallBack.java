package Network;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public interface NetworkCallBack {
    void OnBeginListening(DatagramSocket datagramSocket);

    boolean OnReceivePackage(DatagramPacket datagramPacket);

    void OnCloseNetwork(DatagramSocket datagramSocket);
}
