package Network;

import Utility.Debug;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.concurrent.CopyOnWriteArrayList;

public class ServerNetwork {

    private ServerNetwork() {
        isOn = true;
        networkCallbacks = new CopyOnWriteArrayList<>();
    }

    public void AddCallback(NetworkCallback networkCallBack) {
        networkCallbacks.add(networkCallBack);
    }

    public <T> void Send(DatagramPacket datagramPacket, byte[] byteOut) {
        int maxLength = (byteOut.length / 1024 + 1) * 1024;
        byte[] byteFormat = new byte[maxLength];

        for (int i = 0, len = byteOut.length; i < len; i++) byteFormat[i] = byteOut[i];
        DatagramPacket packetOut = new DatagramPacket(byteFormat, 0, byteFormat.length);
        packetOut.setAddress(datagramPacket.getAddress());
        packetOut.setPort(datagramPacket.getPort());
        try {
            m_DatagramSocket.send(packetOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
                networkCallbacks) {
            item.OnBeginListening(m_DatagramSocket);
        }

        byte[] byteBuffer = new byte[1024];
        DatagramPacket receiveP = new DatagramPacket(byteBuffer, byteBuffer.length);

        while (isOn) {
            try {
                m_DatagramSocket.receive(receiveP);

                for (var item :
                        networkCallbacks) {
                    if (item.OnReceivePackage(receiveP)) {
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
                networkCallbacks) {
            item.OnCloseNetwork(m_DatagramSocket);
        }

    }

    public void Close() {
        m_DatagramSocket.close();
        for (var item :
                networkCallbacks) {
            item.OnCloseNetwork(m_DatagramSocket);
        }
    }

    //////////////////////////

    private int m_Port;

    private DatagramSocket m_DatagramSocket;

    private boolean isOn;

    private CopyOnWriteArrayList<NetworkCallback> networkCallbacks;

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
