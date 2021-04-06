using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using UnityEngine;

[Serializable]
public struct ServerInfo
{
    public string address;
    public int port;
}

[CreateAssetMenu(menuName = "Proj-Config/Create ClientNetConfig ")]
public class ClientNetConfig : ScriptableObject
{
    public List<ServerInfo> ServerList;
}

