using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class SwitchScene : MonoBehaviour
{
  	public Button yourButton;

	void Start () 
    {
		Button btn = yourButton.GetComponent<Button>();
		btn.onClick.AddListener(OpenNewScene);
	}

	void OpenNewScene()
    {
		
	}
}
