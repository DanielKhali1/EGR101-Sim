using System.Collections;
using System.Collections.Generic;
using UnityEngine;


using System.IO;
using System.Threading.Tasks;

public class CompTracker : MonoBehaviour
{
    List<GameObject> components;
    // Start is called before the first frame update
    void Start()
    {
        components = new List<GameObject>();
    }

    public void AddComponent(GameObject component)
    {
        components.Add(component);
    }
    public void saveData()
    public bool isSim;
    public List<GameObject> components;
    // Start is called before the first frame update
    void Start()
    {
        if(!isSim)
        {
            components = gameObject.GetComponent<placementmesh>().sensors;
        }
    }


    void OnApplicationQuit()
    {
        if(!isSim)
        {
            Debug.Log("SAVING Component Data");
            saveCompData();
            Debug.Log("SAVING Wiring Data");
            saveWireData();
        }


    }
    public void saveCompData()
    {
        //save component data to a file
        string text = "";

        for (int i = 0; i < components.Count; i++)
        {
            GameObject comp = components[i];
            text += comp.name + "\n";
            text += comp.transform.localPosition + "\n";
            text += comp.transform.localRotation + "\n";

        }

        File.WriteAllText("WriteText.txt", text);
    }

    private void saveWireData()
    {
        saveData();
        string ultrastring = "";
        List<List<GameObject>> connectionsList = GameObject.FindGameObjectWithTag("Player").GetComponent<placementmesh>().wires; ;
        for (int i = 0; i < connectionsList.Count; i++)
        {
            string line = "";
            foreach (GameObject pin in connectionsList[i])
            {
                line += pin.name + "-";
            }
            ultrastring += line + "\n";
        }
        System.IO.File.WriteAllText("..\\..\\Data\\Wiring_Data.dat", ultrastring);
    }


}
