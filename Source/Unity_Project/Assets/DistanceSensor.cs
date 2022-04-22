using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class DistanceSensor : MonoBehaviour
{
    // Start is called before the first frame update
    float distance = 20;

    private void Update()
    {
        try
        {
            GameObject.FindGameObjectWithTag("ServerController").GetComponent<ServerController>().sendBuffer += gameObject.name + "," + distance + "\n";
        }
        catch
        { }
    }

    private void OnTriggerEnter(Collider other)
    {
        if (other.gameObject.tag.Equals("Wall"))
        {
            distance = Vector3.Distance(other.gameObject.transform.position, gameObject.transform.position)/2;
            Debug.Log(distance);
        }
    }

    private void OnTriggerStay(Collider other)
    {
        if (other.gameObject.tag.Equals("Wall"))
        {
            distance = Vector3.Distance(other.gameObject.transform.position, gameObject.transform.position) / 2;
            //Debug.Log(distance);
        }

    }


    private void OnTriggerExit(Collider other)
    {
        if (other.gameObject.tag.Equals("Wall"))
        {
            distance = 20;
            //Debug.Log(distance);
        }
    }
}
