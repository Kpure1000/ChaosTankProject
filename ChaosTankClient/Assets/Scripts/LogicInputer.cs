using System;
using UnityEngine;

public struct LogicData
{
    public int id;
    public int xInput;
    public int yInput;
}

public class LogicInputer
{
    public static LogicInputer instance
    {
        get
        {
            return m_LazyInstance.Value;
        }
    }

    private LogicInputer()
    {
    }

    public void Init()
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

    private static LogicData m_DataCache = new LogicData();

    private static readonly Lazy<LogicInputer> m_LazyInstance = new Lazy<LogicInputer>(() => new LogicInputer());

}
