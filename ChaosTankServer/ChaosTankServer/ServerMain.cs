using System;

namespace ChaosTankServer
{
    using Network;

    class ServerMain
    {

        static void Main(string[] args)
        { 
            Console.WriteLine("Server On");

            Network.Network network = new ChaosTankServer.Network.Network();
            network.BeginListen();
        }

    }
}