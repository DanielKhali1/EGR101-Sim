using System.Collections;
using System.Collections.Generic;
using UnityEngine;

using System.Globalization;

public class cust_sim_transfer : MonoBehaviour
{
    // Start is called before the first frame update
    public GameObject ultransonic;
    public GameObject linereadingir;
    public GameObject distanceir;
    public GameObject bot;

    void Start()
    {
        GameObject bot = GameObject.FindGameObjectWithTag("Player");

        List<string> list = new List<string>();
        foreach (string line in System.IO.File.ReadLines("..\\..\\Data\\Component_Data.dat")) { list.Add(line); }

        for(int i = 0; i < list.Count; i+=4)
        {
            GameObject temp = null;
            if(list[i].Contains("UltraSonic"))
            {
                temp = Instantiate(ultransonic);

                temp.GetComponent<Outline>().enabled = false;
                temp.GetComponent<ComponentFollowMouse>().isSim = true;

            }
            else if (list[i].Contains("lineReadingIR"))
            {
                temp = Instantiate(linereadingir);
                temp.GetComponent<Outline>().enabled = false;
                temp.GetComponent<IrFollowMouse>().isSim = true;
            }
            else if (list[i].Contains("distancemeasuringirsensor"))
            {
                temp = Instantiate(distanceir);
                temp.GetComponent<Outline>().enabled = false;
                temp.GetComponent<ComponentFollowMouse>().isSim = true;

            }

            temp.transform.parent = bot.transform;
            temp.name = list[i];
            temp.transform.localPosition = strToVector(list[i + 1]);
            temp.transform.localRotation = strToQuat(list[i + 2]);
            temp.transform.localScale = strToVector(list[i + 3]);
            bot.GetComponent<CompTracker>().components.Add(temp);

        }
    }

    Vector3 strToVector(string x)
    {
        string newstr = x.Replace("(", "").Replace(")", "").Replace(",","");
        string[] splitnewstr = newstr.Split(' ');



        return new Vector3(float.Parse(splitnewstr[0], CultureInfo.InvariantCulture.NumberFormat)
                        , float.Parse(splitnewstr[1], CultureInfo.InvariantCulture.NumberFormat)
                        , float.Parse(splitnewstr[2], CultureInfo.InvariantCulture.NumberFormat));
    }

    Quaternion strToQuat(string x)
    {
        string newstr = x.Replace("(", "").Replace(")", "").Replace(",", "");
        string[] splitnewstr = newstr.Split(' ');


        return new Quaternion(float.Parse(splitnewstr[0], CultureInfo.InvariantCulture.NumberFormat)
                        , float.Parse(splitnewstr[1], CultureInfo.InvariantCulture.NumberFormat)
                        , float.Parse(splitnewstr[2], CultureInfo.InvariantCulture.NumberFormat)
                        , float.Parse(splitnewstr[3], CultureInfo.InvariantCulture.NumberFormat));
    }
}
