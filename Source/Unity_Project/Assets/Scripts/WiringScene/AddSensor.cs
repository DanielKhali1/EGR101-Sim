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
        Instantiate(sensor, new Vector3(292.5f, 42.7f, 50f), Quaternion.identity);
    }
}
