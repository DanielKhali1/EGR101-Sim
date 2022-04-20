using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class CreateWire : MonoBehaviour
{
    public GameObject linePrefab;
    private LineRenderer line;
    private List<List<GameObject>> connectionsList;
    private int wiringCount = 0;
    public Camera wiringCam;
    bool wiringMode = false;
    private List<GameObject> wire = new List<GameObject>();
    public Slider redSlider;
    public Slider greenSlider;
    public Slider blueSlider;
    public GameObject preview;
    private void Start()
    {
        connectionsList = GameObject.FindGameObjectWithTag("Player").GetComponent<placementmesh>().wires;
        redSlider.onValueChanged.AddListener(delegate {RedChangeCheck();});
        greenSlider.onValueChanged.AddListener(delegate {GreenChangeCheck();});
        blueSlider.onValueChanged.AddListener(delegate {BlueChangeCheck();});
    }

    void Update()
    {
        if(wiringCam.GetComponent<Camera>().enabled && Input.GetMouseButtonDown(0))
        {
            Ray ray = Camera.main.ScreenPointToRay (Input.mousePosition);
            RaycastHit hit;
            if(Physics.Raycast(ray, out hit))
            {
                if(hit.collider.gameObject.transform.parent.tag == "Pin")
                {
                    if(!wiringMode)
                    {
                        wiringCount = 0;
                        GameObject lineStuff = Instantiate(linePrefab);
                        lineStuff.tag = "Wire";
                        line = lineStuff.GetComponent<LineRenderer>();
                        line.SetPosition(0, hit.collider.gameObject.transform.position);
                        wiringCount++;
                        line.positionCount = 50;
                    }
                    else{
                        line.material.color = new Color(RedChangeCheck(),GreenChangeCheck(),BlueChangeCheck(),1);
                        line.SetPosition(wiringCount, hit.collider.gameObject.transform.position);
                    }
                    
                    hit.collider.gameObject.transform.name = hit.collider.gameObject.transform.parent.name;
                    wire.Add(hit.collider.gameObject);
                    wiringMode = !wiringMode;
                    clearList();
                }
                if(hit.collider.gameObject.transform.parent.tag != "Pin" && wiringMode)
                {
                    GameObject randomPoint = new GameObject();
                    randomPoint.gameObject.transform.name = "randomPoint";
                    Plane plane = new Plane(new Vector3(0,1,0), -5.6f);
                    float distance;
                    Vector3 screenPos;
                    
                    if (plane.Raycast(ray, out distance))
                    {
                        screenPos = ray.GetPoint(distance);
                        for(int i = wiringCount; i < 50; i++)
                        {   
                            line.SetPosition(i, screenPos);
                        }
                    }
                    wiringCount++;
                    wire.Add(randomPoint);
                }
            }
        }
        activeWire(wiringMode);
        changePreview();
    }

    void activeWire(bool wiringMode)
    {
        if(wiringMode)
        {
            Ray ray = Camera.main.ScreenPointToRay(Input.mousePosition);

            Plane plane = new Plane(new Vector3(0,1,0), -5.6f);
            float distance;
            Vector3 worldPosition;
            
            if (plane.Raycast(ray, out distance))
            {
                worldPosition = ray.GetPoint(distance);
                for(int i = wiringCount; i < 50; i++)
                {   
                    line.SetPosition(i, worldPosition);
                }
            }
        }
    }

    public void changePreview()
    {
        preview.GetComponent<Image>().color = new Color(RedChangeCheck(),GreenChangeCheck(),BlueChangeCheck(),1);
    }

    public float RedChangeCheck()
    {
        Debug.Log(redSlider.value);
        return redSlider.value;
    }

    public float GreenChangeCheck()
    {
        return greenSlider.value;
    }

    public float BlueChangeCheck()
    {
        return blueSlider.value;
    }
    void clearList()
    {
        if(!wiringMode)
        {
            connectionsList.Add(new List<GameObject>(wire));
            wire.Clear();
        }
    }   
}