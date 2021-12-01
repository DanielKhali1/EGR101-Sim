using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.EventSystems;

public class NodeFunctionality : MonoBehaviour, IPointerClickHandler
{
    public GameObject object1, object2, object3, object4;
    public GameObject bot;
    public int mode = 0;

    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    public void OnPointerClick(PointerEventData eventData)
    {
        GameObject temp = null;

        if (mode == 1)
        {
            temp = Instantiate(object1);
        }
        if (mode == 2)
        {
            temp = Instantiate(object2);
        }
        if (mode == 3)
        {
            temp = Instantiate(object3);
        }
        if (mode == 4)
        {
            temp = Instantiate(object4);
        }
        temp.transform.parent = bot.gameObject.transform;
        temp.transform.position = new Vector3(0, 5, -3);
    }


}
