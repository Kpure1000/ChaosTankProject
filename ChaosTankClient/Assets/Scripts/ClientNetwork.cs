using System;
using System.Net.Sockets;
using System.Net;
using System.Threading;
using UnityEngine;
using System.Text;

public class ClientNetwork
{

    private ClientNetwork()
    {
        onReceiveInput = new OnReceiveInput((LogicData dataIn) =>
        {
            return false;
        });
        onListeningStop = new OnListeningStop((IPEndPoint IPEP) =>
          {
              Debug.LogWarning(string.Format(
                  "Client network STOP lisetning, info: {0}:{1}.", IPEP.Address, IPEP.Port
                  ));
          });
    }

    public void Init(ServerInfo serverInfoIn)
    {
        serverInfo = serverInfoIn;
        Debug.Log(string.Format("Server Info: {0}:{1}", serverInfo.address, serverInfo.port));

        try
        {
            udpClient = new UdpClient("127.0.0.1", 12345);
            Debug.Log(string.Format("Open Udp Client."));
        }
        catch (SocketException ex)
        {
            // TODO exception
            Debug.LogException(ex);
        }
    }

    public void AddListeningCallBack(OnReceiveInput receiveInput)
    {
        lock (onReceiveInput)
        {
            onReceiveInput += receiveInput;
        }
    }

    public void AddListenedCallBack(OnListeningStop listeningStop)
    {
        lock (onListeningStop)
        {
            onListeningStop += listeningStop;
        }
    }

    public void SendLogic(LogicData dataOut)
    {
        try
        {
            var sendBt = Util.JsonTool.ObjectToJsonByte(dataOut);
            udpClient.Send(sendBt, sendBt.Length);
            //Debug.Log("发送成功");
        }
        catch (SocketException ex)
        {
            Debug.LogException(ex);
        }
    }

    public void BeginListening()
    {
        IPEndPoint remoteIPEP = new IPEndPoint(IPAddress.Any, 0);

        new Thread(() =>
        {
            Debug.Log(string.Format("BeginListening"));
            while (true)
            {
                // Block to receive
                byte[] receiveBytes = udpClient.Receive(ref remoteIPEP);

                var jsonStr = Encoding.UTF8.GetString(receiveBytes);
                Debug.Log("receive>>   " + jsonStr);

                LogicData dataIn = Util.JsonTool.JsonStrToObject<LogicData>(jsonStr);

                if (onReceiveInput(dataIn))
                {
                    onListeningStop(remoteIPEP);
                    break;
                }
            }
        }).Start();

    }

    //////////////////////////////////////////
    /// Singleton

    public static ClientNetwork instance
    {
        get
        {
            return m_LazyInstance.Value;
        }
    }

    private static readonly Lazy<ClientNetwork> m_LazyInstance = new Lazy<ClientNetwork>(() => new ClientNetwork());

    //////////////////////////////////////////

    public delegate bool OnReceiveInput(LogicData dataIn);
    private OnReceiveInput onReceiveInput;

    public delegate void OnListeningStop(IPEndPoint IPEP);
    private OnListeningStop onListeningStop;

    private UdpClient udpClient;
    private ServerInfo serverInfo;

}
