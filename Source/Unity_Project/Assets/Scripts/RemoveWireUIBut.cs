using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.EventSystems;

public class RemoveWireUIBut : MonoBehaviour, IPointerClickHandler
{
    public Camera forkCam;
    GameObject canvas;
    public GameObject ped;

   public async void OnPointerClick(PointerEventData eventData)
    {
        ped.SetActive(false);
        canvas = GameObject.FindGameObjectWithTag("UI");
        if(forkCam.GetComponent<Camera>().enabled)
        {
            for(int i = 0; i < canvas.transform.childCount; i++)
            {
                if(canvas.transform.GetChild(i).gameObject.tag == "backButton")
                {
                    canvas.transform.GetChild(i).gameObject.SetActive(true);
                }
                if(canvas.transform.GetChild(i).gameObject.name == "swap_camera_wire" ||
                    canvas.transform.GetChild(i).gameObject.name == "swap_camera_but" ||
                    canvas.transform.GetChild(i).gameObject.name == "Image1" ||
                    canvas.transform.GetChild(i).gameObject.name == "Image2")

                {
                    canvas.transform.GetChild(i).gameObject.SetActive(false);
                } 
            }
        }
        if(!forkCam.GetComponent<Camera>().enabled)
        {
            for (int i = 0; i < canvas.transform.childCount; i++)
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
