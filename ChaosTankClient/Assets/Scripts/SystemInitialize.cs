using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class SystemInitialize : MonoBehaviour
{
    public ClientNetConfig ClientNetworkConfig;

    private void Awake()
    {
        if (ClientNetworkConfig.ServerList.Count > 0)
        {
            var serverInfo = ClientNetworkConfig.ServerList[0];
            ClientNetwork.instance.Init(serverInfo);
        }
        LogicInputer.instance.Init();
    }
}
