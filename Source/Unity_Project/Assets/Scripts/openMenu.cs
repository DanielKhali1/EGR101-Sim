using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;

public class openMenu : MonoBehaviour
{
	public GameObject menu1;
	public GameObject menu2;
	public Button btn2;
	Button btn;
	public int toolType;
	public Button firstbt;
	public GameObject ToolBarController;

	void Start()
	{
		btn = gameObject.GetComponent<Button>();
		btn.onClick.AddListener(ToggleMenus);
		btn.Select();
		//menu1.SetActive(true);
		//menu2.SetActive(false);
		if(gameObject.name == "Presets")
        {
			ToggleMenus();
        }
	}

	void ToggleMenus()
	{
		menu1.SetActive(true);
		menu2.SetActive(false);
		btn.interactable = false;
		btn2.interactable = true;
		firstbt.Select();
		ToolBarController.GetComponent<updateToolBar>().updateEnumType(toolType);

	}
}
