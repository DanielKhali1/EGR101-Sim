using System.Collections;
using System.Collections.Generic;
using UnityEngine;


using System.IO;
using System.Threading.Tasks;

public class CompTracker : MonoBehaviour
{
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
            text += comp.transform.localScale + "\n";
        }

        File.WriteAllText("..\\..\\Data\\Component_Data.dat", text);
    }
    void OnApplicationQuit()
    {
        if(!isSim)
        {
            Debug.Log("SAVING");
            saveData();
        }
    }


}
