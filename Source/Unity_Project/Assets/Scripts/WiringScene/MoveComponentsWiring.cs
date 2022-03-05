using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.EventSystems;

public class MoveComponentsWiring : MonoBehaviour,  IPointerClickHandler
{

    GameObject bot;
    Vector3 origin;
    public Vector2 dimensions;

    void Start()
    {
        origin = new Vector3(0, -3, 0);
    }
    public async void OnPointerClick(PointerEventData eventData)
    {
        bot = GameObject.FindGameObjectWithTag("Player");
        List<GameObject> sensor = bot.GetComponent<placementmesh>().sensors;
        
        for(int i = 0; i < sensor.Count; i++)
        {
            Debug.Log(sensor[i].transform.localPosition.x);
            //Right Face
            if(sensor[i].transform.localPosition.x == -4)
            {
                MoveRightFace();
            }
            //Left Face
            if(sensor[i].transform.localPosition.x >= 3.9f && sensor[i].transform.localPosition.x <= 4.1f)
            {
                Debug.Log("Hello There");
                MoveLeftFace(sensor[i]);
                sensor[i].transform.Rotate(new Vector3(-90,0,0));
            }
            //Front Face
            if(sensor[i].transform.localPosition.z == -5)
            {
                MoveFrontFace();
            }
            //Back Face
            if(sensor[i].transform.localPosition.x == 6)
            {
                MoveBackFace();
            }
        }
    }

    private void MoveLeftFace(GameObject sensor)
    {
        if(sensor.transform.localPosition.y <= -0.9f && sensor.transform.localPosition.y >= -1.1f)
            sensor.transform.localPosition = new Vector3(4, 4, sensor.transform.localPosition.z);
        if(sensor.transform.localPosition.y >= 0.49f && sensor.transform.localPosition.y <= 0.51f)
            sensor.transform.localPosition = new Vector3(6, 4, sensor.transform.localPosition.z);
        if(sensor.transform.localPosition.y >= 1.99f && sensor.transform.localPosition.y <= 2.1f)
            sensor.transform.localPosition = new Vector3(8, 4, sensor.transform.localPosition.z);
        if(sensor.transform.localPosition.y >= 3.49f && sensor.transform.localPosition.y <= 3.51f)
            sensor.transform.localPosition = new Vector3(10, 4, sensor.transform.localPosition.z);    
    }

    private void MoveRightFace()
    {

    }

    private void MoveFrontFace()
    {

    }

    private void MoveBackFace()
    {

    }
}
