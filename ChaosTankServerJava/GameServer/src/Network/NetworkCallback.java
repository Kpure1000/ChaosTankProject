package Network;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public interface NetworkCallback {
    void OnBeginListening(DatagramSocket datagramSocket);

    boolean OnReceivePackage(DatagramPacket sourcePacket);

    void OnCloseNetwork(DatagramSocket datagramSocket);
}
