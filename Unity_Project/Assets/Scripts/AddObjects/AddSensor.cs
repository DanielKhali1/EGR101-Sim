using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class AddSensor : MonoBehaviour
{
    public Object sensor;
    public Button yourButton;
    void Start()
    {
        Button btn = yourButton.GetComponent<Button>();
		btn.onClick.AddListener(createSensor);
    }

    // Update is called once per frame
    void createSensor()
    {
        Debug.Log("Create object");
        Instantiate(sensor, new Vector3(281.5f, 158.5f, 0f), Quaternion.identity);
    }
}
