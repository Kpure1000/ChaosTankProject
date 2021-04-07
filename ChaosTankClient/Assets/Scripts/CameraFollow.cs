using System.Collections;
using System.Collections.Generic;
using UnityEngine;
[RequireComponent(typeof(Rigidbody2D))]
public class CameraFollow : MonoBehaviour
{
    public PlayerController player;

    [Range(0f, .4f)]
    public float smoothTime = 0f;

    private void Start()
    {
        rb = GetComponent<Rigidbody2D>();
        followTarget = player.transform;
        rb.position = followTarget.position;
    }

    private Rigidbody2D rb;
    private Transform followTarget;
    private Vector2 velocity = Vector2.zero;

    private void FixedUpdate()
    {
        followTarget = player.transform;

        rb.position = new Vector2(
            Mathf.SmoothDamp(rb.position.x, followTarget.position.x, ref velocity.x, smoothTime),
            Mathf.SmoothDamp(rb.position.y, followTarget.position.y, ref velocity.y, smoothTime)
            );
    }
}
