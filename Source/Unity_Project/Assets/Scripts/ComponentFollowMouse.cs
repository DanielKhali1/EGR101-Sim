using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ComponentFollowMouse : MonoBehaviour
{
    bool Follow;
    Vector3 bestPosition;
    GameObject bot;
    public float pickuptimer = 0;

    // Start is called before the first frame update
    void Start()
    {
        Follow = true;
        bot = GameObject.FindGameObjectWithTag("Player");
        List<GameObject> nodes = bot.GetComponent<placementmesh>().meshNodes;

        for(int i = 0;i < nodes.Count; i++)
        {
            nodes[i].GetComponent<MeshRenderer>().enabled = true;
        }

    }
    public void OnMouseDown()
    {
        if(!Follow)
        {
            Follow = true;
            Debug.Log("selected component: " + Follow);
            pickuptimer = 0.4f;

            List<GameObject> nodes = bot.GetComponent<placementmesh>().meshNodes;

            for (int i = 0; i < nodes.Count; i++)
            {
                nodes[i].GetComponent<MeshRenderer>().enabled = true;
            }
        }
    }


    // Update is called once per frame
    void Update()
    {
        if (Follow)
        {
            Vector3 WorldPosition = Camera.main.ScreenToWorldPoint(new Vector3( Input.mousePosition.x,  Input.mousePosition.y, 7));

            List<GameObject> nodes = bot.GetComponent<placementmesh>().meshNodes;


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

            gameObject.transform.position = bestPosition;
            if (Input.GetMouseButtonDown(0) && pickuptimer <= 0)
            {
                Debug.Log("placed object");
                bot.GetComponent<RotateBot>().setDrag(false);

                Follow = false;

                for (int i = 0; i < nodes.Count; i++)
                {
                    nodes[i].GetComponent<MeshRenderer>().enabled = false;
                }

                bot.GetComponent<RotateBot>().setDrag(true);
            }
            if(pickuptimer > 0)
            {
                pickuptimer -= Time.deltaTime;
            }

            Vector3 v3LookPos = transform.parent.position;
            
            transform.LookAt(v3LookPos);
        }

        
    }
}
