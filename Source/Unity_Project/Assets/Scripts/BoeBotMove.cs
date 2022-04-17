using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class BoeBotMove : MonoBehaviour
{
	Quaternion rot = Quaternion.Euler(new Vector3(0, 0, 0));
	public void GetInput(float leftWheelDrive_, float rightWheelDrive_)
	{
		//leftWheelDrive = Input.GetKey(KeyCode.A) || Input.GetKey(KeyCode.Z);
		//rightWheelDrive = Input.GetKey(KeyCode.X) || Input.GetKey(KeyCode.S);

		leftWheelForce = leftWheelDrive_;
		rightWheelForce = -rightWheelDrive_;

		//Debug.Log(leftWheelForce + " " + rightWheelForce);

		leftWheelDrive = true;
		rightWheelDrive = true;
	}

	private void Drive()
	{
		bool forward = true;
		//leftWheelForce = (leftWheelDrive) ? setleftWheelForce : 0;
		//rightWheelForce = (rightWheelDrive) ? setrightWheelForce : 0;

		//leftWheelForce *= (Input.GetKey(KeyCode.Z)) ? -1 : 1;
		//rightWheelForce *= (Input.GetKey(KeyCode.S)) ? -1 : 1;

		float difference = leftWheelForce - rightWheelForce;

		if(forward)
        {
			if (difference > 0)
			{
				gameObject.transform.rotation = Quaternion.Euler(new Vector3(0, rot.eulerAngles.y + difference * 300 * Time.deltaTime, 0));

			}
			else if (difference < 0)
			{
				gameObject.transform.rotation = Quaternion.Euler(new Vector3(0, rot.eulerAngles.y + difference * 300 * Time.deltaTime, 0));

			}



			gameObject.transform.Translate(new Vector3(0, 0, -Mathf.Sqrt(leftWheelForce * leftWheelForce + rightWheelForce * rightWheelForce)* ((leftWheelForce + rightWheelForce < 0)? -1: 1)));

		}
		rot = gameObject.transform.rotation;
	}

	private void UpdateWheelPoses()
	{
		frontPassengerT.rotation = Quaternion.Euler(new Vector3(frontPassengerT.rotation.eulerAngles.x , frontPassengerT.rotation.eulerAngles.y, (frontPassengerT.rotation.eulerAngles.z - rightWheelForce * 300 * Time.deltaTime)));
		frontDriverT.rotation = Quaternion.Euler(new Vector3(frontDriverT.rotation.eulerAngles.x, frontDriverT.rotation.eulerAngles.y, (frontDriverT.rotation.eulerAngles.z - leftWheelForce * 300 * Time.deltaTime)));

	}

	private void FixedUpdate()
	{
		Drive();
		UpdateWheelPoses();
	}
	private void Start()
	{
		leftWheelForce = 0;
		rightWheelForce = 0;
	}

	private bool leftWheelDrive;
	private bool rightWheelDrive;


	public Transform frontDriverT, frontPassengerT;
	private float leftWheelForce;
	private float rightWheelForce;
	public float setleftWheelForce = 0.5f;
	public float setrightWheelForce = 0.5f;
}
