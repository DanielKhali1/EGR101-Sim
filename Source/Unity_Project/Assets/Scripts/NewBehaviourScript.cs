using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class NewBehaviourScript : MonoBehaviour
{
    float rotationSpeed = 0.2f;
    public GameObject rotate;
 
	void OnMouseDrag()
	{
		float XaxisRotation = Input.GetAxis("Mouse X")*rotationSpeed;
		float YaxisRotation = Input.GetAxis("Mouse Y")*rotationSpeed;
		// select the axis by which you want to rotate the GameObject
		rotate.transform.Rotate(Vector3.down, Space.Self);
		rotate.transform.Rotate(Vector3.right, Space.Self);
	}
}
