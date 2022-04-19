using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class updateToolBar : MonoBehaviour
{
    // Start is called before the first frame update

    public GameObject toolslot1;
    public GameObject toolslot2;
    public GameObject toolslot3;
    public GameObject toolslot4;

    public Sprite wheelType1;
    public Sprite wheelType2;
    public Sprite wheelType3;
    public Sprite wheelType4;


    public Sprite mountType1;
    public Sprite mountType2;
    public Sprite mountType3;
    public Sprite mountType4;

    public Sprite LED;

    public Sprite lineReadingSensor;
    public Sprite ultraSonicSensor;
    public Sprite distanceMeasuringIRSensor;

    private void Start()
    {
        Screen.SetResolution(1280, 720, false);
    }
    public void fitImagesInToolbar(int t)
    {

        switch (t)
        {
            case 1:
                Debug.Log("IN general mode");
                toolslot1.GetComponent<Image>().sprite = null;
                toolslot2.GetComponent<Image>().sprite = null;
                toolslot3.GetComponent<Image>().sprite = null;
                toolslot4.GetComponent<Image>().sprite = null;

                toolslot1.GetComponent<NodeFunctionality>().mode = 1;
                toolslot2.GetComponent<NodeFunctionality>().mode = 1;
                toolslot3.GetComponent<NodeFunctionality>().mode = 1;
                toolslot4.GetComponent<NodeFunctionality>().mode = 1;

                toolslot1.GetComponent<NodeFunctionality>().preset = false;
                toolslot2.GetComponent<NodeFunctionality>().preset = false;
                toolslot3.GetComponent<NodeFunctionality>().preset = false;
                toolslot4.GetComponent<NodeFunctionality>().preset = false;
                break;

            case 2:
                Debug.Log("IN wheel mode");
                toolslot1.GetComponent<Image>().sprite = (wheelType1);
                toolslot2.GetComponent<Image>().sprite = (wheelType2);
                toolslot3.GetComponent<Image>().sprite = (wheelType3);
                toolslot4.GetComponent<Image>().sprite = (wheelType4);

                toolslot1.GetComponent<NodeFunctionality>().mode = 2;
                toolslot2.GetComponent<NodeFunctionality>().mode = 2;
                toolslot3.GetComponent<NodeFunctionality>().mode = 2;
                toolslot4.GetComponent<NodeFunctionality>().mode = 2;

                toolslot1.GetComponent<NodeFunctionality>().preset = true;
                toolslot2.GetComponent<NodeFunctionality>().preset = true;
                toolslot3.GetComponent<NodeFunctionality>().preset = true;
                toolslot4.GetComponent<NodeFunctionality>().preset = true;
                break;

            case 3:
                Debug.Log("IN mount mode");
                toolslot1.GetComponent<Image>().sprite = (mountType1);
                toolslot2.GetComponent<Image>().sprite = (mountType2);
                toolslot3.GetComponent<Image>().sprite = (mountType3);
                toolslot4.GetComponent<Image>().sprite = (mountType4);

                toolslot1.GetComponent<NodeFunctionality>().mode = 3;
                toolslot2.GetComponent<NodeFunctionality>().mode = 3;
                toolslot3.GetComponent<NodeFunctionality>().mode = 3;
                toolslot4.GetComponent<NodeFunctionality>().mode = 3;

                toolslot1.GetComponent<NodeFunctionality>().preset = true;
                toolslot2.GetComponent<NodeFunctionality>().preset = true;
                toolslot3.GetComponent<NodeFunctionality>().preset = true;
                toolslot4.GetComponent<NodeFunctionality>().preset = true;
                break;

            case 4:
                Debug.Log("IN sensor mode");
                toolslot1.GetComponent<Image>().sprite = (lineReadingSensor);
                toolslot2.GetComponent<Image>().sprite = (ultraSonicSensor);
                toolslot3.GetComponent<Image>().sprite = (distanceMeasuringIRSensor);
                toolslot4.GetComponent<Image>().sprite = (LED);

                toolslot1.GetComponent<NodeFunctionality>().mode = 4;
                toolslot2.GetComponent<NodeFunctionality>().mode = 4;
                toolslot3.GetComponent<NodeFunctionality>().mode = 4;
                toolslot4.GetComponent<NodeFunctionality>().mode = 4;

                toolslot1.GetComponent<NodeFunctionality>().preset = false;
                toolslot2.GetComponent<NodeFunctionality>().preset = false;
                toolslot3.GetComponent<NodeFunctionality>().preset = false;
                toolslot4.GetComponent<NodeFunctionality>().preset = false;
                break;
        }
    }
    public void clearToolBar()
    {

    }

    public void updateEnumType(int t)
    {
        fitImagesInToolbar(t);
    }
}
