using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CreateWire : MonoBehaviour
{
    public GameObject linePrefab;
    private LineRenderer line;
    private List<List<GameObject>> connectionsList = new List<List<GameObject>>();
    LineRenderer oldLineRender;
    public Camera wiringCam;
    bool wiringMode = false;
    bool visualWire = false;
    private List<GameObject> wire = new List<GameObject>();

    void Update()
    {
        if(wiringCam.GetComponent<Camera>().enabled && Input.GetMouseButtonDown(0))
        {
            Ray ray = Camera.main.ScreenPointToRay (Input.mousePosition);
            RaycastHit hit;
            if(Physics.Raycast(ray, out hit))
            {
                if(hit.collider.gameObject.transform.parent.tag == "Pin")
                {
                    hit.collider.gameObject.transform.name = hit.collider.gameObject.transform.parent.name;
                    wire.Add(hit.collider.gameObject);
                    wiringMode = !wiringMode;
                    visualWire = !visualWire;
                    clearList();
                }
            }
        }
        if(wiringCam.GetComponent<Camera>().enabled && Input.GetMouseButtonDown(1))
        {
            Ray ray = Camera.main.ScreenPointToRay(Input.mousePosition);
            GameObject randomPoint = new GameObject();
            randomPoint.gameObject.transform.position = new Vector3(Input.mousePosition.x, 3.58f, Input.mousePosition.z);
            randomPoint.gameObject.transform.name = "randomPoint";
            wire.Add(randomPoint);
        }

        if(visualWire)
        {
            Plane plane = new Plane(Vector3.up, 0);
            float distance;
            Vector3 worldPosition;
            try{
                GameObject oldLine = GameObject.FindGameObjectWithTag("Wire");
                Destroy(oldLine);
            }catch{}

            GameObject lineStuff = Instantiate(linePrefab);
            lineStuff.tag = "Wire";
            line = lineStuff.GetComponent<LineRenderer>(); 

            line.SetPosition(0, wire[0].gameObject.transform.position);
            Ray ray = Camera.main.ScreenPointToRay(Input.mousePosition);
            if (plane.Raycast(ray, out distance))
            {
                worldPosition = ray.GetPoint(distance);
                line.SetPosition(1, worldPosition);
            }
        }
    }

    void printList()
    {
        for(int i = 0; i < connectionsList.Count;i++)
        {
            for(int j = 0; j < connectionsList[i].Count;j++)
            {
                Debug.Log(connectionsList[i][j]);
            }
        }
    }

    void clearList()
    {
        if(!wiringMode)
        {
            connectionsList.Add(wire);
            printList();
            wire.Clear();
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