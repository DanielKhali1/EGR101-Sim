using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CreateWire : MonoBehaviour
{
    public GameObject linePrefab;
    private LineRenderer line;
    private List<List<GameObject>> connectionsList = new List<List<GameObject>>();
    private int wiringCount = 0;
    Vector3 lastWirePos;
    public Camera wiringCam;
    bool wiringMode = false;
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
                    if(!wiringMode)
                    {
                        wiringCount = 0;
                        GameObject lineStuff = Instantiate(linePrefab);
                        lineStuff.tag = "Wire";
                        line = lineStuff.GetComponent<LineRenderer>();
                        line.SetPosition(0, hit.collider.gameObject.transform.position);
                        wiringCount++;
                        line.positionCount = 50;
                    }
                    else{
                        line.SetPosition(wiringCount, hit.collider.gameObject.transform.position);
                        line.material.color = Color.green;
                    }
                    
                    hit.collider.gameObject.transform.name = hit.collider.gameObject.transform.parent.name;
                    wire.Add(hit.collider.gameObject);
                    wiringMode = !wiringMode;
                    clearList();
                }
                if(hit.collider.gameObject.transform.parent.tag != "Pin" && wiringMode)
                {
                    GameObject randomPoint = new GameObject();
                    randomPoint.gameObject.transform.name = "randomPoint";
                    Plane plane = new Plane(new Vector3(0,1,0), -5.6f);
                    float distance;
                    Vector3 screenPos;
                    
                    if (plane.Raycast(ray, out distance))
                    {
                        screenPos = ray.GetPoint(distance);
                        for(int i = wiringCount; i < 50; i++)
                        {   
                            line.SetPosition(i, screenPos);
                        }
                    }
                    wiringCount++;
                    wire.Add(randomPoint);
                }
            }
        }
        activeWire(wiringMode);
    }

    void activeWire(bool wiringMode)
    {
        if(wiringMode)
        {
            Ray ray = Camera.main.ScreenPointToRay(Input.mousePosition);

            Plane plane = new Plane(new Vector3(0,1,0), -5.6f);
            float distance;
            Vector3 worldPosition;
            
            if (plane.Raycast(ray, out distance))
            {
                worldPosition = ray.GetPoint(distance);
                for(int i = wiringCount; i < 50; i++)
                {   
                    line.SetPosition(i, worldPosition);
                }
            }
        }
    }
    void clearList()
    {
        if(!wiringMode)
        {
            connectionsList.Add(new List<GameObject>(wire));
            updateConnectionList(connectionsList);
            wire.Clear();
        }
    }

    private void updateConnectionList(List<List<GameObject>> connectionslist)
    {
        GameObject bot = GameObject.FindGameObjectWithTag("Player");
        bot.GetComponent<placementmesh>().wires = connectionsList;

        string ultrastring = "";

        foreach(List<GameObject> wire in bot.GetComponent<placementmesh>().wires)
        {
            string f = "";
            foreach(GameObject w in wire)
            {
                Debug.Log(w.transform.name);
                f += w.name + "-";
            }
            ultrastring += "\n";
        }

        //saving wiring data to a file
        System.IO.File.WriteAllText("..\\..\\Data\\Wiring_Data.dat", ultrastring);
    }
}