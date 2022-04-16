using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CreateWire : MonoBehaviour
{
    public GameObject linePrefab;
    private LineRenderer line;
    private List<List<GameObject>> connectionsList = new List<List<GameObject>>();
    LineRenderer oldLineRender;
    private int wiringCount = 0;
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

        if(wiringCam.GetComponent<Camera>().enabled && Input.GetMouseButtonDown(1) && wiringMode)
        {
            Ray ray = Camera.main.ScreenPointToRay(Input.mousePosition);
            GameObject randomPoint = new GameObject();
            randomPoint.gameObject.transform.position = new Vector3(Input.mousePosition.x, 3.58f, Input.mousePosition.z);
            randomPoint.gameObject.transform.name = "randomPoint";
            wire.Add(randomPoint);
        }

        if(visualWire)
        {
            try{
                GameObject oldLine = GameObject.FindGameObjectWithTag("Wire");
                Destroy(oldLine);
            }catch{}

            GameObject lineStuff = Instantiate(linePrefab);
            lineStuff.tag = "Wire";
            line = lineStuff.GetComponent<LineRenderer>(); 

            Vector3 wireLocation = new Vector3();
            wireLocation = wire[0].transform.position;
            wireLocation.y = 5.6f;

            Ray ray = Camera.main.ScreenPointToRay(Input.mousePosition);

            Plane plane = new Plane(new Vector3(0,1,0), -5.6f);
            float distance;
            Vector3 worldPosition;
            
            line.SetPosition(0, wireLocation);
            
            if(wiringMode && Input.GetMouseButtonDown(1))
            {
                wiringCount++;
            }

            if (plane.Raycast(ray, out distance))
            {
                worldPosition = ray.GetPoint(distance);
                line.SetPosition(wiringCount, worldPosition);
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
    }
}