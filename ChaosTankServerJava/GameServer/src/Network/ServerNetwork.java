package Network;

import Utility.Debug;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ServerNetwork {

    private ServerNetwork() {
        isOn = true;
        networkCallBacks = new CopyOnWriteArrayList<>();
    }

    public void AddCallBack(NetworkCallBack networkCallBack) {
        networkCallBacks.add(networkCallBack);
    }

    public void Start(int port) {
        m_Port = port;
        try {
            m_DatagramSocket = new DatagramSocket(m_Port);
        } catch (SocketException e) {
            Debug.LogError(e.getMessage());
//            e.printStackTrace();
        }
        for (var item :
                networkCallBacks) {
            item.OnBeginListening(m_DatagramSocket);
        }

        byte[] byteBuffer = new byte[1024];
        DatagramPacket receiveP = new DatagramPacket(byteBuffer, byteBuffer.length);

        while (isOn) {
            try {
                m_DatagramSocket.receive(receiveP);

                for (var item :
                        networkCallBacks) {
                    if (!item.OnReceivePackage(receiveP)) {
                        isOn = false;
                        break;
                    }
                }

            } catch (IOException e) {
                Debug.LogError(e.getMessage());
//                e.printStackTrace();
            }
        }

        for (var item :
                networkCallBacks) {
            item.OnCloseNetwork(m_DatagramSocket);
        }

    }

    public void Close() {
        m_DatagramSocket.close();
        for (var item :
                networkCallBacks) {
            item.OnCloseNetwork(m_DatagramSocket);
        }
    }

    //////////////////////////

    private int m_Port;

    private DatagramSocket m_DatagramSocket;

    private boolean isOn;

    private CopyOnWriteArrayList<NetworkCallBack> networkCallBacks;

    private static ServerNetwork instance;

    public static ServerNetwork getInstance() {
        if (instance == null) {
            synchronized (ServerNetwork.class) {
                if (instance == null) {
                    instance = new ServerNetwork();
                }
            }
        }
        return instance;
    }

}
