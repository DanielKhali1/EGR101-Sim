using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.EventSystems;

public class BackButton : MonoBehaviour, IPointerClickHandler
{
    // Start is called before the first frame update
    public Camera wiringCam;
    public Camera mainCam;
    public Camera bottomCam;
    GameObject[] wires;
    GameObject canvas;
    public GameObject ped;

    void Start()
    {
        canvas = GameObject.FindGameObjectWithTag("UI");
        for(int i = 0; i < canvas.transform.childCount; i++)
        {
            if(canvas.transform.GetChild(i).gameObject.tag == "backButton")
            {
                canvas.transform.GetChild(i).gameObject.SetActive(false);
            }
        }
    }

    public async void OnPointerClick(PointerEventData eventData)
    {
        ped.SetActive(true);

        wires = GameObject.FindGameObjectsWithTag("Wire");
        if(wiringCam.GetComponent<Camera>().enabled)
        {
            wiringCam.GetComponent<Camera>().enabled = !wiringCam.GetComponent<Camera>().enabled;
            mainCam.GetComponent<Camera>().enabled = !mainCam.GetComponent<Camera>().enabled;
        }
        if(!wiringCam.GetComponent<Camera>().enabled)
        {
            for(int i = 0; i < wires.Length; i++)
            {
                wires[i].GetComponent<LineRenderer>().enabled = false;
            }
            for(int i = 0; i < canvas.transform.childCount; i++)
            {
                if(canvas.transform.GetChild(i).gameObject.name != "swap_camera_wire")
                {
                    canvas.transform.GetChild(i).gameObject.SetActive(true);
                } 
                if(canvas.transform.GetChild(i).gameObject.name == "WiringComponent" ||
                    canvas.transform.GetChild(i).gameObject.name == "LeftPanel" || 
                    canvas.transform.GetChild(i).gameObject.tag == "backButton")
                {
                    canvas.transform.GetChild(i).gameObject.SetActive(false);
                }
            }
        }
        if(bottomCam.GetComponent<Camera>().enabled)
        {
            bottomCam.GetComponent<Camera>().enabled = !bottomCam.GetComponent<Camera>().enabled;
            mainCam.GetComponent<Camera>().enabled = !mainCam.GetComponent<Camera>().enabled;
        }
        if(!bottomCam.GetComponent<Camera>().enabled)
        {
            for(int i = 0; i < canvas.transform.childCount; i++)
            {
                if(canvas.transform.GetChild(i).gameObject.name == "swap_camera_wire")
                {
                    canvas.transform.GetChild(i).gameObject.SetActive(true);
                }
                if(canvas.transform.GetChild(i).gameObject.tag == "backButton")
                {
                    canvas.transform.GetChild(i).gameObject.SetActive(false);
                } 
            }
        }

    }

}
