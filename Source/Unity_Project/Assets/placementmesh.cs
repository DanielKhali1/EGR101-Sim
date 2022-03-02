using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class placementmesh : MonoBehaviour
{

    public Vector2 dimensions;
    Vector3 origin;

    public List<GameObject> meshNodes = new List<GameObject>();

    void Start()
    {
        origin = new Vector3(0, -3, 0);
        createForwardFace();
        createLeftFace();
        createBackwardFace();
        createRightFace();

        for (int i = 0; i < meshNodes.Count; i++)
        {
            meshNodes[i].GetComponent<MeshRenderer>().enabled = false;
        }

    }

    void createLeftFace() 
    {
        int count = 0;
        for (int i = 0; i < 5; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                GameObject sphere = makeSphere("LSphere" + count);
                sphere.transform.parent = gameObject.transform;
                sphere.transform.position = new Vector3(origin.x - dimensions.x * .8f, origin.y + dimensions.y * 1.7f - (j * 1.5f), origin.z + 5.7f - (i * 2.1f));
                count++;
            }
        }
    }

    void createRightFace()
    {
        int count = 0;
        for (int i = 0; i < 5; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                GameObject sphere = makeSphere("RSphere" + count);
                sphere.transform.parent = gameObject.transform;
                sphere.transform.position = new Vector3(origin.x + dimensions.x * .8f, origin.y + dimensions.y * 1.7f - (j * 1.5f), origin.z + 5.7f - (i * 2.1f));
                count++;
            }
        }
    }

    void createBackwardFace()
    {
        int count = 0;
        for (int i = 0; i < 5; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                GameObject sphere = makeSphere("BSphere" + count);
                sphere.transform.parent = gameObject.transform;
                sphere.transform.position = new Vector3(origin.x + dimensions.x * .8f - (i * 1.5f), origin.y + dimensions.y * 1.7f - (j * 1.5f), origin.z + 7 );
                count++;
            }
        }
    }

    void createForwardFace()
    {
        int count = 0;
        for (int i = 0; i < 5; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                GameObject sphere = makeSphere("FSphere" + count);
                sphere.transform.parent = gameObject.transform;
                sphere.transform.position = new Vector3(origin.x + dimensions.x * .6f - (i * 1.5f), origin.y + dimensions.y * 1.7f - (j * 1.5f), origin.z - 4);
                count++;
            }
        }
    }

    GameObject makeSphere(string name)
    {
        GameObject sphere = GameObject.CreatePrimitive(PrimitiveType.Sphere);
        sphere.name = name;
        sphere.transform.localScale = new Vector3(0.5f, 0.5f, 0.5f);
        sphere.GetComponent<Renderer>().material.SetColor("_Color", new Color(0, 1, 0, 0.2f));
        meshNodes.Add(sphere);
        return sphere;
    }
}
