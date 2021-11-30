using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.EventSystems;

public class NodeFunctionality : MonoBehaviour, IPointerClickHandler
{
    public GameObject object1, object2, object3, object4;
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

        if (mode == 1)
        {
            GameObject temp = Instantiate(object2);
            temp.transform.position = new Vector3(0, 5, -5);
            Debug.Log("Hello");
        }
        if (mode == 2)
        {
            GameObject temp = Instantiate(object4);
            temp.transform.position = new Vector3(0, 5, -5);
            Debug.Log("Hello");
        }
        if (mode == 3)
        {
            GameObject temp = Instantiate(object3);
            temp.transform.position = new Vector3(0, 5, -5);
            Debug.Log("Hello");
        }
        if (mode == 4)
        {
            GameObject temp = Instantiate(object1);
            temp.transform.position = new Vector3(0, 5, -5);
            Debug.Log("Hello");
        }
       

    }

   
}
