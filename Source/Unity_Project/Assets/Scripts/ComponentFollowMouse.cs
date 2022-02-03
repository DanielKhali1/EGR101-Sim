using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ComponentFollowMouse : MonoBehaviour
{
    bool Follow;
    Vector3 bestPosition;

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
            Vector3 WorldPosition = Camera.main.ScreenToWorldPoint(new Vector3( Input.mousePosition.x,  Input.mousePosition.y, 7));

            List<GameObject> nodes = GameObject.FindGameObjectWithTag("Player").GetComponent<placementmesh>().meshNodes;

            float closest = float.MaxValue;
            
            for (int i = 0; i < nodes.Count; i++)
            {
                float nodeDistance = Vector3.Distance(WorldPosition, nodes[i].transform.position);
                if (nodeDistance < closest)
                {
                    closest = nodeDistance;
                    bestPosition = nodes[i].transform.position;
                }
            }

            // gameObject.transform.position = new Vector3(WorldPosition.x - 10, WorldPosition.y - 10, gameObject.transform.position.z);
            gameObject.transform.position = bestPosition;
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
