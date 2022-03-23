using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.EventSystems;

public class RemovePanels : MonoBehaviour, IPointerClickHandler
{
    public Camera wiringCam;
    GameObject canvas;

   public async void OnPointerClick(PointerEventData eventData)
    {
        canvas = GameObject.FindGameObjectWithTag("UI");
        if(wiringCam.GetComponent<Camera>().enabled)
        {
            for(int i = 0; i < canvas.transform.childCount; i++)
            {
                if(canvas.transform.GetChild(i).gameObject.name != "swap_camera_wire" && 
                   canvas.transform.GetChild(i).gameObject.name != "swap_camera_but" &&
                   canvas.transform.GetChild(i).gameObject.name != "WiringComponent")
                {
                    canvas.transform.GetChild(i).gameObject.SetActive(false);
                } 
            }
        }
        if(!wiringCam.GetComponent<Camera>().enabled)
        {
            for(int i = 0; i < canvas.transform.childCount; i++)
            {
                if(canvas.transform.GetChild(i).gameObject.name != "swap_camera_wire" && 
                   canvas.transform.GetChild(i).gameObject.name != "swap_camera_but" &&
                   canvas.transform.GetChild(i).gameObject.name != "WiringComponent")
                {
                    canvas.transform.GetChild(i).gameObject.SetActive(true);
                } 
            }
        }
    }
}
