using System;

namespace ChaosTankServer
{
    class ServerMain
    {

        static void Main(string[] args)
        { 
            Console.WriteLine("Server On");

            ServerNetwork.ServerNetwork network = new ChaosTankServer.ServerNetwork.ServerNetwork();
            network.BeginListen();
        }

    }
}