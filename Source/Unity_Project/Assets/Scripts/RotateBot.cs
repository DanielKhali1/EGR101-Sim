using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class RotateBot : MonoBehaviour
{
    public GameObject rotate;
  

    // Update is called once per frame
    void Update()
    {
        rotate.transform.Rotate(0f,0.05f,0f,Space.Self);
    }


}
