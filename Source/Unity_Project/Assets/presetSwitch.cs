using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class presetSwitch : MonoBehaviour
{
    public GameObject leftWheel;
    public GameObject rightWheel;

    public GameObject mount;


    public void ToggleWheel(Mesh mesh)
    {
        leftWheel.GetComponent<MeshFilter>().mesh = mesh;
        rightWheel.GetComponent<MeshFilter>().mesh = mesh;

    }

    public void ToggleMount(Mesh mesh)
    {
        mount.GetComponent<MeshFilter>().mesh = mesh;
    }
}
