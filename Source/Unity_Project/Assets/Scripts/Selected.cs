using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Selected : MonoBehaviour
{
    Color32 c;

    private void Start()
    {
        c = gameObject.GetComponent<MeshRenderer>().material.color;
    }

    public void OnMouseEnter()
    {
        gameObject.GetComponent<MeshRenderer>().material.color = new Color(255, 0, 255);
        Debug.Log("HEOOW");
    }

    public void OnMouseExit()
    {
        gameObject.GetComponent<MeshRenderer>().material.color = c;
    }

}
