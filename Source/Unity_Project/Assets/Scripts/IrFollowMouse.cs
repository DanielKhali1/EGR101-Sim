using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class IrFollowMouse : MonoBehaviour
{
    bool Follow;
    Vector3 bestPosition;
    GameObject bot;
    public float pickuptimer = 0;
    Vector3 m_EulerAngleVelocity = new Vector3(0, 100, 0);
    int currentAngle = 0;
    bool selected;
    public bool isSim = false;

    List<GameObject> nodes;

    // Start is called before the first frame update
    void Start()
    {
        foreach (Transform child in transform)
        {
            child.name = gameObject.name + "-" + child.name;
        }

        Follow = true;
        selected = true;
        if(!isSim)
        {
            GetComponent<Outline>().enabled = true;
            bot = GameObject.FindGameObjectWithTag("Player");
            bot.GetComponent<ComponentStructure>().getIRSensorList().Add(gameObject);
            nodes = bot.GetComponent<presetSwitch>().activePositions;
            bot.GetComponent<placementmesh>().sensors.Add(gameObject);
            gameObject.transform.rotation = bot.transform.rotation;
            gameObject.transform.Rotate(new Vector3(0, -90, 0), Space.Self);
        
            Physics.IgnoreLayerCollision(1, 7, true);
        }

    }
    public void mouse(GameObject ob)
    {
        if (ob == gameObject && !Follow)
        {
            if (!selected)
            {
                //refresh nodes
                nodes = bot.GetComponent<presetSwitch>().activePositions;
                Debug.Log("selected component: " + ob);
                //GameObject.FindGameObjectsWithTag("UI")[0].GetComponent<UnityEngine.UI.Text>().text = gameObject.name;
                GetComponent<Outline>().enabled = true;
                selected = true;
            }
            else if (selected)
            {
                Follow = true;
                Debug.Log("moving component: " + ob);
                pickuptimer = 0.4f;

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
        if(!isSim)
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

            if (Input.GetKey(KeyCode.Escape) && selected)
            {
                selected = false;

                Destroy(gameObject);
            }

            if (Follow)
            {
                Vector3 WorldPosition = Camera.main.ScreenToWorldPoint(new Vector3(Input.mousePosition.x, Input.mousePosition.y, 10));

                float closest = float.MaxValue;
                //int bI = 0;

                for (int i = 0; i < nodes.Count; i++)
                {
                    float nodeDistance = Vector3.Distance(WorldPosition, nodes[i].transform.position);
                    if (nodeDistance < closest)
                    {
                        closest = nodeDistance;
                        bestPosition = nodes[i].transform.position;
                    }
                }

                bestPosition -= new Vector3(0, 0.12f, 0);
                gameObject.transform.position = bestPosition;

           

                if (Input.GetMouseButtonDown(0) && pickuptimer <= 0)
                {
                    Debug.Log("placed object");
                    //bot.GetComponent<RotateBot>().setDrag(false);

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
}
