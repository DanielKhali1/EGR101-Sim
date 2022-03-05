using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class PrintWiringNode : MonoBehaviour
{
    Text text;

    void Start()
    {
        text = GetComponent<Text>();
    }
    void RayCast()
    {
        Ray ray = Camera.main.ScreenPointToRay( Input.mousePosition );
        RaycastHit hit;
        try{ 
            if( Physics.Raycast( ray, out hit, 100 ) && hit.transform.parent.name != "Walls")
            {
                text.text = hit.transform.parent.name;
            }
        } catch
        {
            
        }
    }

    // Update is called once per frame
    void Update()
    {
        RayCast();
    }
}
