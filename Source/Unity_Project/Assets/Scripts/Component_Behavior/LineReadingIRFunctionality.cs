using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class LineReadingIRFunctionality : MonoBehaviour
{
    int whiteness = 0;
    // Start is called before the first frame update

    private void OnTriggerEnter(Collider other)
    {
        if(other.gameObject.tag.Equals("whiteline")){
            whiteness = 100;
            GameObject.FindGameObjectWithTag("ServerController").GetComponent<ServerController>().sendBuffer += gameObject.name + "," + whiteness;
        }
    }

    private void OnTriggerExit(Collider other)
    {
        if (other.gameObject.tag.Equals("whiteline"))
        {
            whiteness = 0;
            GameObject.FindGameObjectWithTag("ServerController").GetComponent<ServerController>().sendBuffer += gameObject.name + "," + whiteness;
        }
    }
}
