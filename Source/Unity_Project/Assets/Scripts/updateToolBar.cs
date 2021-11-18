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

    public Sprite mountType1;
    public Sprite mountType2;
    public Sprite mountType3;

    public Sprite LED;

    public Sprite lineReadingSensor;
    public Sprite ultraSonicSensor;
    public Sprite distanceMeasuringIRSensor;

    public void fitImagesInToolbar(int t)
    {

        switch (t)
        {
            case 1:
                toolslot1.GetComponent<Image>().sprite = (LED);
                toolslot2.GetComponent<Image>().sprite = null;
                toolslot3.GetComponent<Image>().sprite = null;
                toolslot4.GetComponent<Image>().sprite = null;
                break;
            case 2:
                toolslot1.GetComponent<Image>().sprite = (wheelType1);
                toolslot2.GetComponent<Image>().sprite = (wheelType2);
                toolslot3.GetComponent<Image>().sprite = (wheelType2);
                toolslot4.GetComponent<Image>().sprite = null;
                break;
            case 3:

                toolslot1.GetComponent<Image>().sprite = (mountType1);
                toolslot2.GetComponent<Image>().sprite = (mountType2);
                toolslot3.GetComponent<Image>().sprite = (mountType3);
                toolslot4.GetComponent<Image>().sprite = null;
                break;

            case 4:
                toolslot1.GetComponent<Image>().sprite = (lineReadingSensor);
                toolslot2.GetComponent<Image>().sprite = (ultraSonicSensor);
                toolslot3.GetComponent<Image>().sprite = (distanceMeasuringIRSensor);
                toolslot4.GetComponent<Image>().sprite = null;
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
