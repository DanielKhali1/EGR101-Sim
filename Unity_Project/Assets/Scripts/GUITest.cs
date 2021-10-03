using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class GUITest : MonoBehaviour
{
    private float hSliderValue1 = 0.0f;
    private float hSliderValue2 = 0.0f;
    //public IMColorPicker colorPicker;

    void OnGUI()
    {
     
        //Make a background box
        GUI.Box(new Rect(10, 10, 1000, 600), "GUI Test");

        //Ultrasonic Sensor selection menu box
        GUI.Box(new Rect(20, 30, 200, 300), "Ultrasonic Sensor");

        //Labels for sensor selection box
        GUI.Label(new Rect(30, 60, 100, 50), "Sensor Height:");
        GUI.Label(new Rect(30, 150, 100, 50), "Sensor Distance:");
        GUI.Label(new Rect(30, 240, 100, 50), "Color:");

        //Sliders for sensor selection box
        hSliderValue1 = GUI.HorizontalSlider(new Rect(30, 85, 100, 50), hSliderValue1, 0.0f, 10.0f);
        hSliderValue2 = GUI.HorizontalSlider(new Rect(30, 175, 100, 50), hSliderValue2, 0.0f, 10.0f);

        if(GUI.Button(new Rect(725, 30, 75, 40), "Wiring"))
        {
            //Create a connection to Java
        }
    }
}
