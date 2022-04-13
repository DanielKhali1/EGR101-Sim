using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CreateWire : MonoBehaviour
{
    public GameObject linePrefab;
    LineRenderer oldLineRender;
    bool wiringMode = false;

     void Update()
    {
        if( Input.GetMouseButtonDown(0) )
        {
            try{
                Ray ray = Camera.main.ScreenPointToRay( Input.mousePosition );
                RaycastHit hit;
            
                if( Physics.Raycast( ray, out hit, 100 ) && hit.transform.parent.name != "Walls")
                {
                    
                    GameObject line = Instantiate(linePrefab, new Vector3(0,0,0), Quaternion.identity);
                    LineRenderer lineRenderer = line.GetComponent<LineRenderer>();
                    Color notReadyColor = Color.red;
                    lineRenderer.sharedMaterial.SetColor("_Color", notReadyColor);


                    //Debug.Log(count + " " + hit.transform.parent.name);
                    if (!wiringMode)
                    {
                        
                        lineRenderer.SetPosition(0, hit.transform.parent.position);
                        wiringMode = true;
                        Debug.Log("turning on wiring mode");
                    }
                    else
                    {
                        oldLineRender.SetPosition(1, hit.transform.parent.position);
                        wiringMode = false;
                        Debug.Log("turning off wiring mode");
                    }
                    oldLineRender = lineRenderer;
                } 
            }catch {

            }
        }
    }

    //update every
    // - new connection formed
    // - connection deleted
    private void updateConnectionList(List<List<GameObject>> connectionslist)
    {
        // hi luke 
    }
}

