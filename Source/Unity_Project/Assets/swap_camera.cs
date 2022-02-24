using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.EventSystems;

public class swap_camera : MonoBehaviour, IPointerClickHandler
{
    public GameObject cam1;
    public GameObject cam2;

    public void OnPointerClick(PointerEventData eventData)
    {
        cam1.GetComponent<Camera>().enabled = !cam1.GetComponent<Camera>().enabled;
        cam2.GetComponent<Camera>().enabled = !cam2.GetComponent<Camera>().enabled;
    }
}
