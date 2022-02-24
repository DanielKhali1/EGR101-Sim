using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CreateWire : MonoBehaviour
{
    public GameObject linePrefab;
    LineRenderer oldLineRender;
    int count = 0;

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

                    Debug.Log(count + " " + hit.transform.parent.name);
                    if(count % 2 == 0)
                    {
                        
                        lineRenderer.SetPosition(0, hit.transform.parent.position);
                    }
                    else
                    {
                        oldLineRender.SetPosition(1, hit.transform.parent.position);
                    }
                    oldLineRender = lineRenderer;
                    count++;
                } 
            }catch {

            }
        }
    }
}
