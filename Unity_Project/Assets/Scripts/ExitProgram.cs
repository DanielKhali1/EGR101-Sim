using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
public class ExitProgram : MonoBehaviour
{
   public Button yourButton;

   void Start()
   {
       Button btn = yourButton.GetComponent<Button>();
       btn.onClick.AddListener(doExitGame);
   }

    public void doExitGame() 
    {
        Debug.Log("Quit Program");
        Application.Quit();
    }
}
