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

    bool drag;


    public void setDrag(bool d)
    {
        drag = d;
    }

    void Start()
    {
        rb = gameObject.GetComponent<Rigidbody>();
        position = rb.position + new Vector3(0, 0.5f, 0);
        drag = true;
    }

    public void OnMouseDown()
    {
        if(drag)
        {
            initialPosition = new Vector2(Input.mousePosition.x, Input.mousePosition.y);
            rb.position = position;
            pickup = true;
        }
    }

    public void OnMouseDrag()
    {
        if(drag)
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
    }

    void OnMouseUp()
    {
        if(drag)
        {
            rb.angularVelocity = new Vector3(0, 0, 0);
            rb.position = position;
            pickup = false;
        }
    }

}
