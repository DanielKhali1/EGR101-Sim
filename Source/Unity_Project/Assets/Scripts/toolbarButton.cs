using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class toolbarButton : MonoBehaviour
{

    public GameObject ToolBarController;

	public int toolType;

	Button btn;

	void Start()
	{
		btn = gameObject.GetComponent<Button>();
		btn.onClick.AddListener(buttonPress);
		btn.Select();

	}

	void buttonPress()
	{
		ToolBarController.GetComponent<updateToolBar>().updateEnumType(toolType);
	}
}
