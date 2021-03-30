using System;
using System.Net.Sockets;
using System.Net;
using System.Linq;
using System.IO;
using System.Runtime.Serialization.Formatters.Binary;

namespace ChaosTankServer.ServerNetwork
{
    [Serializable]
    struct DataObj
    {
        public int id;
        public string str;
    }

    public class ServerNetwork
    {
        public ServerNetwork()
        {
            serverIP = new IPEndPoint(IPAddress.Any, 12345);
            socket = new Socket(AddressFamily.InterNetwork, SocketType.Dgram, ProtocolType.Udp);
            socket.Bind(serverIP);
        }

        public void BeginListen()
        {
            Console.WriteLine("Begin Listening");
            try
            {
                byte[] reader = new byte[1024];
                IPEndPoint sender = new IPEndPoint(IPAddress.Any, 0);
                EndPoint remoteEndPoint = (EndPoint)sender;
                while (true)
                {
                    socket.ReceiveFrom(reader, ref remoteEndPoint);
                    DataObj dataObj = ReadObjectFromBytes<DataObj>(reader);
                    Console.WriteLine("ID: {0}, Content: {1}\n", dataObj.id, dataObj.str);
                }
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex);
            }
        }

        public void Shutdown()
        {
            socket.Close();
        }

        /// <summary>
        /// ReadObjectFromBytes (Serialize)
        /// </summary>
        /// <typeparam name="T">Object Type</typeparam>
        /// <param name="reader">byte array</param>
        /// <returns>Object with Target Type</returns>
        private T ReadObjectFromBytes<T>(byte[] reader)
        {
            T reObj;
            using (MemoryStream ms = new MemoryStream())
            {
                ms.Write(reader, 0, reader.Length);
                ms.Flush();
                ms.Position = 0;

                BinaryFormatter bf = new BinaryFormatter();
                reObj = (T)bf.Deserialize(ms);
            }
            return reObj;
        }

        /////////////
        /// Data
        /////////////

        private IPEndPoint serverIP;
        private Socket socket;
    }
}
