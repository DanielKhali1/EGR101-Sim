using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CreateWire : MonoBehaviour
{
    public GameObject linePrefab;
    LineRenderer oldLineRender;
    public Camera wiringCam;
    private GameObject bot;
    bool wiringMode = false;

    void Start()
    {
        bot = GameObject.FindGameObjectWithTag("Player");
    }

    /*
        1. Create initial position
        2. Add positions 
        3. When another pin is clicked end the list.
    */

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
                    //Only pins here
                }
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

