using System;
using System.Net.Sockets;
using System.Net;
using System.Threading;
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
          });

        try
        {
            udpClient = new UdpClient(12345);
            // TODO ?
        }
        catch (SocketException ex)
        {
            // TODO exception
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

    public void BeginListening()
    {
        IPEndPoint remoteIPEP = new IPEndPoint(IPAddress.Any, 0);

        new Thread(() =>
       {
           while (true)
           {
               // Block to receive
               //byte[] receiveBytes = udpClient.Receive(ref remoteIPEP);

               LogicData dataIn = new LogicData();
               dataIn.xInput = 1;

               if (onReceiveInput(dataIn))
               {
                   onListeningStop(remoteIPEP);
                   break;
               }

               Thread.Sleep(32);
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

}
