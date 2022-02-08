using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.EventSystems;

public class NodeFunctionality : MonoBehaviour, IPointerClickHandler
{
    public GameObject object1, object2, object3, object4;
    public GameObject bot;
    public int mode = 0;
    public bool preset = false;
    public void OnPointerClick(PointerEventData eventData)
    {
        GameObject temp = null;

        switch(mode)
        {
            case 1: temp = Instantiate(object1);
                temp.transform.parent = bot.gameObject.transform;
                temp.transform.position = new Vector3(0, 5, -3); break;
            case 2: //wheel
                bot.GetComponent<presetSwitch>().ToggleWheel(object2);
                break;

            case 3: //mount
                bot.GetComponent<presetSwitch>().ToggleMount(object3);
                break;

            case 4: temp = Instantiate(object4);
                temp.transform.parent = bot.gameObject.transform;
                temp.transform.position = new Vector3(0, 5, -3); break;
        }
        

    }
}
