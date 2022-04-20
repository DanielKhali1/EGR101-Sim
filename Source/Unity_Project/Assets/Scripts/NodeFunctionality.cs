using System.Collections;
using System.Collections.Generic;
using System.Text;
using UnityEngine;
using UnityEngine.EventSystems;
using System;

public class NodeFunctionality : MonoBehaviour, IPointerClickHandler
{
    public GameObject object1, object4, mesh2;
    public GameObject mesh1;
    public GameObject bot;
    public Camera wiringCam;
    public int mode = 0;
    public bool preset = false;
    public int num = 0;
    public void OnPointerClick(PointerEventData eventData)
    {
        GameObject temp = null;

        switch(mode)
        {
            case 1: temp = Instantiate(object1);
                temp.transform.parent = bot.gameObject.transform;
                temp.transform.position = new Vector3(0, 5, -3); break;
            case 2: //wheel
                bot.GetComponent<presetSwitch>().ToggleWheel(mesh1);
                break;

            case 3: //mount
                bot.GetComponent<presetSwitch>().ToggleMount(mesh2.GetComponentInChildren<MeshFilter>().sharedMesh, num);
                break;

            case 4: temp = Instantiate(object4);
                temp.transform.parent = bot.gameObject.transform;
                temp.transform.position = new Vector3(0, 5, -3);
                temp.name = object4.name.Replace("Prefab", "") + (RandomString(6));
                Debug.Log(temp.name);
                break;
        }
        

    }
    private readonly System.Random random = new System.Random();
    public string RandomString(int size, bool lowerCase = false)
    {
        var builder = new StringBuilder(size);

        // Unicode/ASCII Letters are divided into two blocks
        // (Letters 65�90 / 97�122):   
        // The first group containing the uppercase letters and
        // the second group containing the lowercase.  

        // char is a single Unicode character  
        char offset = lowerCase ? 'a' : 'A';
        const int lettersOffset = 26; // A...Z or a..z: length = 26  

        for (var i = 0; i < size; i++)
        {
            var @char = (char)random.Next(offset, offset + lettersOffset);
            builder.Append(@char);
        }

        return lowerCase ? builder.ToString().ToLower() : builder.ToString();
    }
}
