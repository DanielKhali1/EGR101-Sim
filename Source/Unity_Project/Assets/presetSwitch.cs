using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class presetSwitch : MonoBehaviour
{
    public GameObject mount1;
    public GameObject mount2;
    public GameObject mount3;
    public GameObject mount4;

    public GameObject wheel1;
    public GameObject wheel2;
    public GameObject wheel3;
    public GameObject wheel4;

    public void ToggleWheel(GameObject wheel)
    {
        wheel1.SetActive(false);
        wheel2.SetActive(false);
        wheel3.SetActive(false);
        wheel4.SetActive(false);

        wheel.SetActive(true);
    }

    public void ToggleMount(GameObject mount)
    {
        mount1.SetActive(false);
        mount2.SetActive(false);
       // mount3.SetActive(false);
        //mount4.SetActive(false);

        mount.SetActive(true);
    }
}
