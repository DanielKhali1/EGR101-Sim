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
    {
        //save component data to a file
        string text = "";

        for(int i = 0; i < components.Count; i++)
        {
            GameObject comp = components[i];
            text += comp.name+"\n";
            text += comp.transform.localPosition + "\n";
            text += comp.transform.localRotation + "\n";

        }

        File.WriteAllText("WriteText.txt", text);
    }
    void OnApplicationQuit()
    {
        saveData();
    }


}
