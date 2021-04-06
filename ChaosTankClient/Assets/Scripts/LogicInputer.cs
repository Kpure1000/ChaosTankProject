using System;
using UnityEngine;

public struct LogicData
{
    public int xInput;
    public int yInput;
}

public class LogicInputer
{
    public LogicInputer()
    {
        ClientNetwork.instance.AddListeningCallBack((LogicData dataIn) =>
        {
            m_DataCache = dataIn;
            return false;
        });

        ClientNetwork.instance.BeginListening();
    }

    public LogicData dataCache
    {
        get { return m_DataCache; }
    }

    private LogicData m_DataCache = new LogicData();

}
