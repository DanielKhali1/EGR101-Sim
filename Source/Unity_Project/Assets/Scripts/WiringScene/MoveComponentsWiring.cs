using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.EventSystems;

public class MoveComponentsWiring : MonoBehaviour,  IPointerClickHandler
{

    GameObject bot;
    public Camera wiringCam;
    public Vector2 dimensions;
    List<Vector3> originalSensors;

    public async void OnPointerClick(PointerEventData eventData)
    {
        bot = GameObject.FindGameObjectWithTag("Player");
        List<GameObject> sensor = bot.GetComponent<placementmesh>().sensors;
        originalSensors = bot.GetComponent<placementmesh>().origSensorsPos;
        //sets the originalSensorPos to the positions of the sn
        for(int i = 0; i < sensor.Count; i++)
        {
            bot.GetComponent<placementmesh>().origSensorsPos.Add(sensor[i].transform.localPosition);
        }

        if(wiringCam.GetComponent<Camera>().enabled)
        {
            bot.GetComponent<Transform>().rotation = Quaternion.Euler(0,0,0);
            bot.GetComponent<Rigidbody>().freezeRotation = true;
            for(int i = 0; i < sensor.Count; i++)
            {
                //Right Face
                if(sensor[i].transform.localPosition.x >= -4.01f && sensor[i].transform.localPosition.x <= -3.99f)
                {
                    MoveRightFace(sensor[i]);
                    sensor[i].transform.Rotate(new Vector3(-90,0,0));
                }
                //Left Face
                if(sensor[i].transform.localPosition.x >= 3.9f && sensor[i].transform.localPosition.x <= 4.1f)
                {
                    MoveLeftFace(sensor[i]);
                    sensor[i].transform.Rotate(new Vector3(-90,0,0));
                }
                //Front Face
                if(sensor[i].transform.localPosition.z >= -5.01f && sensor[i].transform.localPosition.z <= -4.99f)
                {
                    MoveFrontFace(sensor[i]);
                    sensor[i].transform.Rotate(new Vector3(-90,0,0));
                }
                //Back Face
                if(sensor[i].transform.localPosition.z <= 6.01f && sensor[i].transform.localPosition.z >= 5.99f)
                {
                    MoveBackFace(sensor[i]);
                    sensor[i].transform.Rotate(new Vector3(-90,0,0));
                }
                //IR Sensors
                if(sensor[i].transform.localPosition.y >= -2.4f && sensor[i].transform.localPosition.y <=-2.3f)
                {
                    MoveIRSensors(sensor[i]);
                }
            }
        }
        if(!wiringCam.GetComponent<Camera>().enabled)
        {
            bot.GetComponent<Rigidbody>().freezeRotation = false;
            for(int i = 0; i < sensor.Count; i++)
            {
                sensor[i].transform.localPosition = originalSensors[i];
                if(sensor[i].transform.localPosition.y > -2.3f)
                    sensor[i].transform.Rotate(90,0,0);
                //bot.GetComponent<placementmesh>().origSensorsPos.Remove(sensor[i].transform.localPosition);
            }
            bot.GetComponent<placementmesh>().origSensorsPos.RemoveAll(item => item.x < 100);
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

    private void MoveRightFace(GameObject sensor)
    {
        if(sensor.transform.localPosition.y <= -0.9f && sensor.transform.localPosition.y >= -1.1f)
            sensor.transform.localPosition = new Vector3(-4, 4, sensor.transform.localPosition.z);
        if(sensor.transform.localPosition.y >= 0.49f && sensor.transform.localPosition.y <= 0.51f)
            sensor.transform.localPosition = new Vector3(-6, 4, sensor.transform.localPosition.z);
        if(sensor.transform.localPosition.y >= 1.99f && sensor.transform.localPosition.y <= 2.1f)
            sensor.transform.localPosition = new Vector3(-8, 4, sensor.transform.localPosition.z);
        if(sensor.transform.localPosition.y >= 3.49f && sensor.transform.localPosition.y <= 3.51f)
            sensor.transform.localPosition = new Vector3(-10, 4, sensor.transform.localPosition.z); 
    }

    private void MoveFrontFace(GameObject sensor)
    {
        if(sensor.transform.localPosition.y <= -0.9f && sensor.transform.localPosition.y >= -1.1f)
            sensor.transform.localPosition = new Vector3(sensor.transform.localPosition.x, 4, -5);
        if(sensor.transform.localPosition.y >= 0.49f && sensor.transform.localPosition.y <= 0.51f)
            sensor.transform.localPosition = new Vector3(sensor.transform.localPosition.x, 4, -7);
        if(sensor.transform.localPosition.y >= 1.99f && sensor.transform.localPosition.y <= 2.1f)
            sensor.transform.localPosition = new Vector3(sensor.transform.localPosition.x, 4, -9);
        if(sensor.transform.localPosition.y >= 3.49f && sensor.transform.localPosition.y <= 3.51f)
            sensor.transform.localPosition = new Vector3(sensor.transform.localPosition.x, 4, -11); 
    }

    private void MoveBackFace(GameObject sensor)
    {
        if(sensor.transform.localPosition.y <= -0.9f && sensor.transform.localPosition.y >= -1.1f)
            sensor.transform.localPosition = new Vector3(sensor.transform.localPosition.x, 4, 5);
        if(sensor.transform.localPosition.y >= 0.49f && sensor.transform.localPosition.y <= 0.51f)
            sensor.transform.localPosition = new Vector3(sensor.transform.localPosition.x, 4, 7);
        if(sensor.transform.localPosition.y >= 1.99f && sensor.transform.localPosition.y <= 2.1f)
            sensor.transform.localPosition = new Vector3(sensor.transform.localPosition.x, 4, 9);
        if(sensor.transform.localPosition.y >= 3.49f && sensor.transform.localPosition.y <= 3.51f)
            sensor.transform.localPosition = new Vector3(sensor.transform.localPosition.x, 4, 11); 
    }

    private void MoveIRSensors(GameObject sensor)
    {
        sensor.transform.localPosition = new Vector3(sensor.transform.localPosition.x + 8, 
                                                    4,
                                                    sensor.transform.localPosition.z - 5);
    }
}
