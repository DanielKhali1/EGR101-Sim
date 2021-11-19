using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;

public class HomeScreen : MonoBehaviour
{
  	public Button yourButton;

	void Start () 
    {
		Button btn = yourButton.GetComponent<Button>();
		btn.onClick.AddListener(OpenNewScene);
	}

	void OpenNewScene()
    {
		SceneManager.LoadScene("MainMenu");
	}
}
