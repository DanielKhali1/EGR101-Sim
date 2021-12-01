using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ComponentFollowMouse : MonoBehaviour
{
    bool Follow;

    // Start is called before the first frame update
    void Start()
    {
        Follow = true;
        
    }

    // Update is called once per frame
    void Update()
    {
        if (Follow)
        {
            Vector3 WorldPosition = Camera.main.ScreenToWorldPoint(new Vector3(12 * Input.mousePosition.x, 12 * Input.mousePosition.y, 1.0f));
            gameObject.transform.position = new Vector3(WorldPosition.x - 10, WorldPosition.y - 10, gameObject.transform.position.z);
            Debug.Log(WorldPosition);

            if (Input.GetMouseButtonDown(0))
            {
                Follow = false;
            }
        }

        
    }

    void OnMouseDown()
    {
        Follow = false; 
    }
}
