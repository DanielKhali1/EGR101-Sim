using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.EventSystems;

public class RotateBot : MonoBehaviour
{
    private Vector3 screenPoint;
    private Vector3 initialPosition;
    private Vector3 offset;
    public float speed = 20.0f;

    Rigidbody rb;

    Vector3 position;

    bool pickup = false;


    private void Update()
    {
        //if(!pickup) rb.velocity += new Vector3(0, -50.0f, 0);
    }

    void Start()
    {
        rb = gameObject.GetComponent<Rigidbody>();
        position = rb.position + new Vector3(0, 0.5f, 0);
    }

    public void OnMouseDown()
    {
        initialPosition = new Vector2(Input.mousePosition.x, Input.mousePosition.y);
        rb.position = position;
        pickup = true;
    }

    public void OnMouseDrag()
    {
        Vector3 cursorPoint = new Vector2(Input.mousePosition.x, Input.mousePosition.y);
        Vector3 heading = cursorPoint - initialPosition;
        Vector3 direction = new Vector3(0, -(cursorPoint.x - initialPosition.x)/20, 0);

        direction.x = 0;
        direction.z = 0;

        rb.angularVelocity = direction;
        rb.position = position;
        pickup = true;

    }

    void OnMouseUp()
    {
        rb.angularVelocity = new Vector3(0, 0, 0);
        rb.position = position;
        pickup = false;

    }


}
