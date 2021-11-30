using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class Selected : MonoBehaviour
{
    Color32 c;

    public bool work;

    private void Start()
    {
        c = gameObject.GetComponent<MeshRenderer>().material.color;
    }
    public void OnMouseEnter()
    {
        if(work)
        {
            gameObject.GetComponent<MeshRenderer>().material.color = new Color(255, 0, 255);
        }
        
    }

    public void OnMouseExit()
    {
        gameObject.GetComponent<MeshRenderer>().material.color = c;
    }

}
