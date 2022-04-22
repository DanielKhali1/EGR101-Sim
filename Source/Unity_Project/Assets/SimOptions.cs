using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class SimOptions : MonoBehaviour
{
    public GameObject boe;
    public GameObject ServerController;
    public GameObject title;

    public GameObject course1;
    public GameObject course2;
    public GameObject course3;

    public Vector3 resetPosition;
    public Quaternion resetRotation;

    public Mesh inward;
    public Mesh outward;
    public Mesh default_;
    public Mesh lol;

    public GameObject Mount;


    public GameObject defaultwheel;
    public GameObject lightweightwheel;
    public GameObject skinnyridgewheel;
    public GameObject ridgewheel;




    public GameObject wheelone;
    public GameObject wheeeltwo;

    private void Start()
    {
        updateWheelView();
        updateMountView();
        course1.SetActive(false);
        course2.SetActive(false);
        course3.SetActive(false);
        List<string> list = new List<string>();
        foreach (string line in System.IO.File.ReadLines("..\\..\\Data\\Current_Course.dat")) { list.Add(line); }

        if (list[0].Equals("Stage 1: Line Reading") )
        {
            course1.SetActive(true);
            course2.SetActive(false);
            course3.SetActive(false);
            resetPosition = new Vector3(-7.5f, 0.58f, 105.2f);
            resetRotation = Quaternion.Euler(new Vector3(0, 2.23f, 0));
            title.GetComponent<Text>().text = "Stage 1: Line Reading";
}
        else if (list[0].Equals("Stage 2: Object Avoidance"))
        {
            course1.SetActive(false);
            course2.SetActive(true);
            course3.SetActive(false);
            resetPosition = new Vector3(-3.8f, 0.58f, 81f);
            resetRotation = Quaternion.Euler(new Vector3(0, 2.23f, 0));
            title.GetComponent<Text>().text = "Stage 2: Object Avoidance";


        }

        else if (list[0].Equals("Stage 3: Line Reading with Noise"))
        {
            course1.SetActive(false);
            course2.SetActive(false);
            course3.SetActive(true);
            resetPosition = new Vector3(-69.93f, 0.58f, -3.029999f);
            resetRotation = Quaternion.Euler(new Vector3(0, 212.761f, 0));
            title.GetComponent<Text>().text = "Stage 3: Line Reading with Noise";


        }
        res();
    }
    public void res()
    {
        boe.transform.position = resetPosition;
        boe.transform.rotation = resetRotation;
    }

    public void play()
    {
        ServerController.GetComponent<ServerController>().isPlaying = true;
    }

    public void pause()
    {
        ServerController.GetComponent<ServerController>().isPlaying = false;
    }

    private void updateWheelView()
    {
        List<string> list = new List<string>();
        foreach (string line in System.IO.File.ReadLines("..\\..\\Data\\Wheel_Data.dat")) { list.Add(line); }

        Debug.Log(list[0]);

        if (list[0].Equals("better_wheel"))
        {
            Debug.Log(list[0]);

            wheelone.GetComponent<MeshFilter>().mesh = defaultwheel.GetComponentInChildren<MeshFilter>().sharedMesh;
            wheeeltwo.GetComponent<MeshFilter>().mesh = defaultwheel.GetComponentInChildren<MeshFilter>().sharedMesh;

        }
        else if (list[0].Equals("lightweightwheel"))

        {
            Debug.Log(list[0]);

            wheelone.GetComponent<MeshFilter>().mesh = lightweightwheel.GetComponentInChildren<MeshFilter>().sharedMesh;
            wheeeltwo.GetComponent<MeshFilter>().mesh = lightweightwheel.GetComponentInChildren<MeshFilter>().sharedMesh;
        }
        else if (list[0].Equals("skinnyridgewheel"))
        {
            Debug.Log(list[0]);

            wheelone.GetComponent<MeshFilter>().mesh = skinnyridgewheel.GetComponentInChildren<MeshFilter>().sharedMesh;
            wheeeltwo.GetComponent<MeshFilter>().mesh = skinnyridgewheel.GetComponentInChildren<MeshFilter>().sharedMesh;
        }
        else if (list[0].Equals("ridgewheel"))
        {
            Debug.Log(list[0]);

            wheelone.GetComponent<MeshFilter>().mesh = ridgewheel.GetComponentInChildren<MeshFilter>().sharedMesh;
            wheeeltwo.GetComponent<MeshFilter>().mesh = ridgewheel.GetComponentInChildren<MeshFilter>().sharedMesh;
        }
    }

    private void updateMountView()
    {
        List<string> list = new List<string>();
        foreach (string line in System.IO.File.ReadLines("..\\..\\Data\\Mount_Data.dat")) { list.Add(line); }

        Debug.Log(list[0]);

        if (list[0].Equals("inward"))
        {
            Debug.Log(list[0]);

            Mount.GetComponent<MeshFilter>().mesh = inward;
        }
        else if(list[0].Equals("outward"))

        {
            Debug.Log(list[0]);

            Mount.GetComponent<MeshFilter>().mesh = outward;
        }
        else if(list[0].Equals("default"))
        {
            Debug.Log(list[0]);

            Mount.GetComponent<MeshFilter>().mesh = default_;
        } 
        else if(list[0].Equals("lol"))
        {
            Debug.Log(list[0]);

            Mount.GetComponent<MeshFilter>().mesh = lol;
        }
    }
}
