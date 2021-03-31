using System;
using System.Net.Sockets;
using System.Text;
using System.Threading;

using Newtonsoft.Json;

namespace ChaosTankServer.ServerNetwork
{

    struct DataMsg
    {
        public int id;
        public string str;
    }

    public class TestClient
    {
        public TestClient()
        {
            UdpClient udpClient = new UdpClient("127.0.0.1", 12345);

            DataMsg data = new DataMsg();
            data.str = "Hello.";
            byte[] sendBytes = new byte[1024];
            byte[] dataBf;
            string jsonStr = "";
            while (true)
            {
                data.id++;
                jsonStr = JsonConvert.SerializeObject(data);
                dataBf = Encoding.UTF8.GetBytes(jsonStr);
                for (int i = 0; i < dataBf.Length; i++)
                {
                    sendBytes[i] = dataBf[i];
                }
                try
                {
                    udpClient.Send(sendBytes, sendBytes.Length);
                    
                }
                catch (SocketException ex)
                {
                    Console.WriteLine("Sand Failed");
                }
                Thread.Sleep(32);
            }
        }
    }
}
