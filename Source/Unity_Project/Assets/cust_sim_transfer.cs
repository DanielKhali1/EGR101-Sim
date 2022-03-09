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
        //GameObject bot = GameObject.FindGameObjectWithTag("Player");

        List<string> list = new List<string>();
        foreach (string line in System.IO.File.ReadLines("..\\..\\Data\\Component_Data.dat")) { list.Add(line); }

        for(int i = 0; i < list.Count; i+=4)
        {
            GameObject temp = null;
            if(list[i].Equals("UltraSonicPrefab(Clone)"))
            {
                temp = Instantiate(ultransonic);
                temp.GetComponent<Outline>().enabled = false;
            }
            else if (list[i].Equals("lineReadingIR(Clone)"))
            {
                temp = Instantiate(linereadingir);
                temp.GetComponent<Outline>().enabled = false;
            }
            else if (list[i].Equals("distancemeasuringirsensor(Clone)"))
            {
                temp = Instantiate(distanceir);
                temp.GetComponent<Outline>().enabled = false;
            }

            temp.transform.parent = bot.transform;
            temp.transform.localPosition = strToVector(list[i + 1]);
            temp.transform.localRotation = strToQuat(list[i + 2]);
            temp.transform.localScale = strToVector(list[i + 3]);
            Debug.Log(i);

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
