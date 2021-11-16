using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;

public class openMenu : MonoBehaviour
{
	public GameObject menu1;
	public GameObject menu2;
	Button btn;

	void Start()
	{
		btn = gameObject.GetComponent<Button>();
		btn.onClick.AddListener(ToggleMenus);
	}

	void ToggleMenus()
	{
		menu1.SetActive(true);
		menu2.SetActive(false);
	}
}
