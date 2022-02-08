using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ComponentFollowMouse : MonoBehaviour
{
    bool Follow;
    Vector3 bestPosition;
    GameObject bot;
    public float pickuptimer = 0;
    Vector3 m_EulerAngleVelocity = new Vector3(0, 100, 0);

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
        Physics.IgnoreLayerCollision(1,7,true);  

    }
    public void mouse(GameObject ob)
    {
        if(!Follow && ob == gameObject)
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
        if (Input.GetMouseButtonDown(0))
        {
            RaycastHit hit;
            Ray ray = Camera.main.ScreenPointToRay(Input.mousePosition);
            if (Physics.Raycast(ray, out hit, 100.0f))
            {
                Debug.Log(hit.transform.gameObject); 
                mouse(hit.transform.gameObject);
                Debug.Log("You selected the " + hit.transform.name); 
            }
        }

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

        }
    }
}
