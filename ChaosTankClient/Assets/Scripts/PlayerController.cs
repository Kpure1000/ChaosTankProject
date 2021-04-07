using System.Collections;
using System.Collections.Generic;
using UnityEngine;

[RequireComponent(typeof(Rigidbody2D))]
public class PlayerController : MonoBehaviour
{

    public float MoveSpeed;
    public float RotateSpeed;

    void Start()
    {
        rb2d = GetComponent<Rigidbody2D>();
    }

    void FixedUpdate()
    {
        xInput = LogicInputer.instance.dataCache.xInput * 0.001f;
        yInput = LogicInputer.instance.dataCache.yInput * 0.001f;

        runAngle += -xInput * RotateSpeed;
        transform.Rotate(0, 0, -RotateSpeed * RadToAngle * xInput);

        runDirection.x = Mathf.Cos(runAngle);
        runDirection.y = Mathf.Sin(runAngle);
        runVelocity = yInput * MoveSpeed * runDirection;

        rb2d.velocity = runVelocity;
    }

    private float xInput;
    private float yInput;

    ////////////////////////////////////
    ///Tmp

    private Rigidbody2D rb2d;

    private float runAngle = Rad90;
    private Vector2 runDirection;
    private Vector2 runVelocity;

    private const float RadToAngle = 180 / Mathf.PI;
    private const float Rad90 = Mathf.PI / 2f; 

}
