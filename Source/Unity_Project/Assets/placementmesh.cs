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
        createLeftFace();
        createRightFace();
        createBackwardFace();
        createForwardFace();


        for (int i = 0; i < meshNodes.Count; i++)
        {
            meshNodes[i].GetComponent<MeshRenderer>().enabled = false;
        }

    }

    void createLeftFace() 
    {
        for (int i = 0; i < 6; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                GameObject sphere = makeSphere();
                sphere.transform.parent = gameObject.transform;
                sphere.transform.position = new Vector3(origin.x - dimensions.x * .8f, origin.y + dimensions.y * 1.7f - (j * 1.5f), origin.z + 5 - (i * 1.5f));
            }
        }
    }

    void createRightFace()
    {
        for (int i = 0; i < 6; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                GameObject sphere = makeSphere();
                sphere.transform.parent = gameObject.transform;
                sphere.transform.position = new Vector3(origin.x + dimensions.x * .8f, origin.y + dimensions.y * 1.7f - (j * 1.5f), origin.z + 5 - (i * 1.5f));
            }
        }
    }

    void createBackwardFace()
    {
        for (int i = 0; i < 6; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                GameObject sphere = makeSphere();
                sphere.transform.parent = gameObject.transform;
                sphere.transform.position = new Vector3(origin.x + dimensions.x * .8f - (i * 1.5f), origin.y + dimensions.y * 1.7f - (j * 1.5f), origin.z + 6 );
            }
        }
    }

    void createForwardFace()
    {
        for (int i = 0; i < 6; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                GameObject sphere = makeSphere();
                sphere.transform.parent = gameObject.transform;
                sphere.transform.position = new Vector3(origin.x + dimensions.x * .8f - (i * 1.5f), origin.y + dimensions.y * 1.7f - (j * 1.5f), origin.z - 4);
            }
        }
    }

    GameObject makeSphere()
    {
        GameObject sphere = GameObject.CreatePrimitive(PrimitiveType.Sphere);
        sphere.transform.localScale = new Vector3(0.5f, 0.5f, 0.5f);
        sphere.GetComponent<Renderer>().material.SetColor("_Color", new Color(0, 1, 0, 0.2f));
        meshNodes.Add(sphere);
        return sphere;
    }
}
