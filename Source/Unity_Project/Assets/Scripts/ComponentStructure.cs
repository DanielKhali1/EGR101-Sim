using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ComponentStructure : MonoBehaviour
{
    List<GameObject> componentList;
    List<GameObject> irsensorList;

    void Start()
    {
        componentList = new List<GameObject>();
        irsensorList = new List<GameObject>();
    }

    public List<GameObject> getIRSensorList()
    {
        return irsensorList;
    }

}
