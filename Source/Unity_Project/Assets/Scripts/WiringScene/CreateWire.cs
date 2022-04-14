using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CreateWire : MonoBehaviour
{
    public GameObject linePrefab;
    private List<List<GameObject>> connectionsList;
    LineRenderer oldLineRender;
    public Camera wiringCam;
    private GameObject bot;
    bool wiringMode = false;
    private List<GameObject> wire = new List<GameObject>();

    void Start()
    {
        bot = GameObject.FindGameObjectWithTag("Player");
    }

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
                }
            }
        }
        if(wiringCam.GetComponent<Camera>().enabled && Input.GetMouseButtonDown(1))
        {
            Ray ray = Camera.main.ScreenPointToRay(Input.mousePosition);
            RaycastHit hit;

            if(Physics.Raycast(ray, out hit))
            {

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