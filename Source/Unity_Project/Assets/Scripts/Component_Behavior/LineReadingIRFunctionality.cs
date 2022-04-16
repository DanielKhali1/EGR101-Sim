using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class LineReadingIRFunctionality : MonoBehaviour
{
    int whiteness = 0;

    private void OnTriggerEnter(Collider other)
    {

        Debug.Log(other.gameObject.name);

        if (other.gameObject.tag.Equals("whiteline")){
            //whiteness = 100;
            Debug.Log(whiteness);
            if(GameObject.FindGameObjectWithTag("ServerController").GetComponent<ServerController>().sendBuffer.Length < 100)
                GameObject.FindGameObjectWithTag("ServerController").GetComponent<ServerController>().sendBuffer += gameObject.name + "," + 100 + "\n";
        }
    }

/*  private void OnTriggerStay(Collider other)
    {

        Debug.Log(other.gameObject.name);

        if (other.gameObject.tag.Equals("whiteline"))
        {
            //whiteness = 100;
            Debug.Log(whiteness);
            if (GameObject.FindGameObjectWithTag("ServerController").GetComponent<ServerController>().sendBuffer.Length < 100)
                GameObject.FindGameObjectWithTag("ServerController").GetComponent<ServerController>().sendBuffer += gameObject.name + "," + 100 + "\n";
        }
    }
*/

    private void OnTriggerExit(Collider other)
    {
        Debug.Log(other.gameObject.name);

        if (other.gameObject.tag.Equals("whiteline"))
        {
            whiteness = 0;
            Debug.Log(whiteness);
            if (GameObject.FindGameObjectWithTag("ServerController").GetComponent<ServerController>().sendBuffer.Length < 100)
                GameObject.FindGameObjectWithTag("ServerController").GetComponent<ServerController>().sendBuffer += gameObject.name + "," + 0 + "\n";

        }
    }
}
