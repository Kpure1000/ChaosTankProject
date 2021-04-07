using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class SystemInputer : MonoBehaviour
{

    public float InputInternalTime = 0.032f;

    void Start()
    {
        logicInputSending = new LogicData();
    }

    void Update()
    {
        currentTime += Time.deltaTime;

        if (currentTime >= InputInternalTime)
        {
            currentTime = 0.0f;

            // get input
            logicInputSending.xInput = (int)(1000.0f * Input.GetAxisRaw("Horizontal"));
            logicInputSending.yInput = (int)(1000.0f * Input.GetAxisRaw("Vertical"));

            // TODO send input to server
            ClientNetwork.instance.SendLogic(logicInputSending);
        }
    }

    private float currentTime = 0.0f;

    private LogicData logicInputSending;
}
