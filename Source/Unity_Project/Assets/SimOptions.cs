using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class SimOptions : MonoBehaviour
{
    public GameObject boe;
    private void Start()
    {
    }
    // Update is called once per frame
    void Update()
    {
        if (Input.GetKey(KeyCode.R))
        {
            boe.transform.position = new Vector3(-7.5f, 0.58f, 105.2f);
            boe.transform.rotation = Quaternion.Euler(new Vector3(0, 2.23f, 0));
        }        
    }
}
