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
    int currentAngle = 0;
    bool selected;

    // Start is called before the first frame update
    void Start()
    {
        Follow = true;
        selected = true;
        GetComponent<Outline>().enabled = true;
        bot = GameObject.FindGameObjectWithTag("Player");
        List<GameObject> nodes = bot.GetComponent<placementmesh>().meshNodes;
        gameObject.transform.rotation = bot.transform.rotation;

        for(int i = 0;i < nodes.Count; i++)
        {
            nodes[i].GetComponent<MeshRenderer>().enabled = true;
        }
        Physics.IgnoreLayerCollision(1,7,true);  

    }
    public void mouse(GameObject ob)
    {
        if(ob == gameObject && !Follow)
        {
            if (!selected)
            {

                Debug.Log("selected component: " + ob);
                GameObject.FindGameObjectWithTag("UI").GetComponent<UnityEngine.UI.Text>().text = gameObject.name;
                GetComponent<Outline>().enabled = true;
                selected = true;
            }
            else if(selected)
            {
                Follow = true;
                Debug.Log("moving component: " + ob);
                pickuptimer = 0.4f;

                List<GameObject> nodes = bot.GetComponent<placementmesh>().meshNodes;

                for (int i = 0; i < nodes.Count; i++)
                {
                    nodes[i].GetComponent<MeshRenderer>().enabled = true;
                }
            }
        }
        else
        {
            selected = false;
            GetComponent<Outline>().enabled = false;
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
                Debug.Log("You clicked on the " + hit.transform.name); 
            }
        }

        if (Input.GetKey(KeyCode.Escape))
        {
            selected = false;
            List<GameObject> nodes = bot.GetComponent<placementmesh>().meshNodes;
            for (int i = 0; i < nodes.Count; i++)
            {
                nodes[i].GetComponent<MeshRenderer>().enabled = false;
            }
            Destroy(gameObject);
        }

        if (Follow)
        {
            Vector3 WorldPosition = Camera.main.ScreenToWorldPoint(new Vector3( Input.mousePosition.x,  Input.mousePosition.y, 10));

            List<GameObject> nodes = bot.GetComponent<placementmesh>().meshNodes;


            float closest = float.MaxValue;
            int bI = 0;
            
            for (int i = 0; i < nodes.Count; i++)
            {
                float nodeDistance = Vector3.Distance(WorldPosition, nodes[i].transform.position);
                if (nodeDistance < closest)
                {
                    closest = nodeDistance;
                    bestPosition = nodes[i].transform.position;
                    bI = i;
                }
            }

            gameObject.transform.position = bestPosition;
            //Debug.Log(bI + " " + (bI / 20) * 90 + " " + gameObject.transform.rotation.eulerAngles.y);
            if((bI / 20) * 90 != currentAngle)
            {
                int oldRotation = currentAngle;
                // algorithm to get to correct angle
                currentAngle = (bI / 20) * 90;
                Debug.Log(currentAngle + " - " + oldRotation);

                // need to rotate to turn to the correct angle 
                // final - initial
                gameObject.transform.Rotate(new Vector3(0, currentAngle-oldRotation, 0), Space.Self);
            }

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
                selected = true;
                GetComponent<Outline>().enabled = true;

            }
            if (pickuptimer > 0)
            {
                pickuptimer -= Time.deltaTime;
            }

        }
    }
}
