using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.EventSystems;
using UnityEngine.SceneManagement;

public class RemovePanels : MonoBehaviour, IPointerClickHandler
{
    public Camera wiringCam;
    GameObject canvas;
    GameObject[] wires;
    GameObject color;

    void Start()
    {
        canvas = GameObject.FindGameObjectWithTag("UI");
        canvas.transform.GetChild(0).gameObject.SetActive(false);

        for(int i = 0; i < canvas.transform.childCount; i++)
        {
            
        }
    }

   public async void OnPointerClick(PointerEventData eventData)
    {
        canvas = GameObject.FindGameObjectWithTag("UI");
        wires = GameObject.FindGameObjectsWithTag("Wire");
        if(wiringCam.GetComponent<Camera>().enabled)
        {
            for(int i = 0; i < wires.Length; i++)
            {
                wires[i].GetComponent<LineRenderer>().enabled = true;
            }
            for(int i = 0; i < canvas.transform.childCount; i++)
            {
                if(canvas.transform.GetChild(i).gameObject.name != "swap_camera_wire")
                {
                    canvas.transform.GetChild(i).gameObject.SetActive(false);
                } 
                if(canvas.transform.GetChild(i).gameObject.name == "WiringComponent")
                {
                    canvas.transform.GetChild(0).gameObject.SetActive(true);
                    canvas.transform.GetChild(i).gameObject.SetActive(true);
                }
            }
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
                if(canvas.transform.GetChild(i).gameObject.name == "WiringComponent")
                {
                    canvas.transform.GetChild(0).gameObject.SetActive(false);
                    canvas.transform.GetChild(i).gameObject.SetActive(false);
                }
            }
        }
    }
}
