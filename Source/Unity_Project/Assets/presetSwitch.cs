using System.Collections;
using System.Collections.Generic;
using UnityEngine;


using System.IO;
using System.Runtime.Serialization;
using System.Runtime.Serialization.Formatters.Binary;
public class presetSwitch : MonoBehaviour
{
    public GameObject leftWheel;
    public GameObject rightWheel;

    public GameObject mount;
    public Mesh defaultMount;
    public GameObject defaultWheels;

    public GameObject defaultMountPos;
    public GameObject inwardMountPos;
    public GameObject outwardMountPos;
    public GameObject lolMountPos;

    public string mountName;
    public string wheelName;




    public List<GameObject> activePositions = new List<GameObject>();
    public void Start()
    {
        ToggleMount(defaultMount, 2);
        ToggleWheel(defaultWheels);
        wheelName = "default";
        mountName = "default";
        System.IO.File.WriteAllText("..\\..\\Data\\Wheel_Data.dat", defaultWheels.name);
        System.IO.File.WriteAllText("..\\..\\Data\\Mount_Data.dat", mountName);
    }

    public void ToggleWheel(GameObject mesh)
    {
        leftWheel.GetComponent<MeshFilter>().mesh = mesh.GetComponentInChildren<MeshFilter>().sharedMesh;
        rightWheel.GetComponent<MeshFilter>().mesh = mesh.GetComponentInChildren<MeshFilter>().sharedMesh;

        

        System.IO.File.WriteAllText("..\\..\\Data\\Wheel_Data.dat", mesh.name);

    }

    public void ToggleMount(Mesh mesh, int num)
    {
        mount.GetComponent<MeshFilter>().mesh = mesh;

        switch (num)
        {
            case 1:
                showMountPositions(outwardMountPos);
                mountName = "outward";
                break;
            case 2:
                showMountPositions(defaultMountPos);
                mountName = "default";
                break;
            case 3:
                showMountPositions(lolMountPos);
                mountName = "lol";
                break;
            case 4:
                showMountPositions(inwardMountPos);
                mountName = "inward";
                break;
        }

        System.IO.File.WriteAllText("..\\..\\Data\\Mount_Data.dat", mountName);


        checkdiff();
    }

    public void checkdiff()
    {
        List<GameObject> irsensors = GameObject.FindGameObjectWithTag("Player").GetComponent<ComponentStructure>().getIRSensorList();


        for(int i = 0; i < irsensors.Count; i++)
        {
            bool incorrectpostion = true;
            for (int j = 0; j < activePositions.Count; j++) {
                //Debug.Log(irsensors[i].transform.position + " " + activePositions[j].transform.position);
                if(Mathf.Abs(irsensors[i].transform.position.x - activePositions[j].transform.position.x) < 0.1 &&
                    Mathf.Abs(irsensors[i].transform.position.z - activePositions[j].transform.position.z) < 0.1)
                {
                    incorrectpostion = false;
                }     
            }

            if(incorrectpostion)
            {
                GameObject x = irsensors[i];
                irsensors.RemoveAt(i);
                Destroy(x);
                i--;
                
            }
        }

    }

    public void showMountPositions(GameObject b)
    {
        Transform[] allChildren = b.GetComponentsInChildren<Transform>();
        List<GameObject> gameobs = new List<GameObject>();

        defaultMountPos.SetActive(false);
        inwardMountPos.SetActive(false);
        outwardMountPos.SetActive(false);
        lolMountPos.SetActive(false);
        b.SetActive(true);

        foreach (Transform child in allChildren)
        {
            gameobs.Add(child.gameObject);
        }

        activePositions = gameobs;
    }
}
