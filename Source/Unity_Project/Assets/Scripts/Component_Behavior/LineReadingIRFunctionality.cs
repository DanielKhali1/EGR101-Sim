using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class LineReadingIRFunctionality : MonoBehaviour
{
    int whiteness = 0;

    private void Update()
    {
        GameObject.FindGameObjectWithTag("ServerController").GetComponent<ServerController>().sendBuffer += gameObject.name + "," + whiteness + "\n";
    }

    private void OnTriggerEnter(Collider other)
    {

        Debug.Log(other.gameObject.name);

        if (other.gameObject.tag.Equals("whiteline")){
            //whiteness = 100;
            whiteness = 100;//GameObject.FindGameObjectWithTag("ServerController").GetComponent<ServerController>().sendBuffer += gameObject.name + "," + 100 + "\n";
        }
    }

    private void OnTriggerStay(Collider other)
    {

        Debug.Log(other.gameObject.name);

        if (other.gameObject.tag.Equals("whiteline"))
        {
            //whiteness = 100;
            whiteness = 100;//GameObject.FindGameObjectWithTag("ServerController").GetComponent<ServerController>().sendBuffer += gameObject.name + "," + 100 + "\n";
        }
/*        else
        {

            whiteness = 0;//GameObject.FindGameObjectWithTag("ServerController").GetComponent<ServerController>().sendBuffer += gameObject.name + "," + 0 + "\n";
        }*/
    }


    private void OnTriggerExit(Collider other)
    {
        Debug.Log(other.gameObject.name);

        if (other.gameObject.tag.Equals("whiteline"))
        {
                whiteness = 0;//GameObject.FindGameObjectWithTag("ServerController").GetComponent<ServerController>().sendBuffer += gameObject.name + "," + 0 + "\n";

        }
    }
}
