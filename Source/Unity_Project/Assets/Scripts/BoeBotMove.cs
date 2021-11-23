using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class BoeBotMove : MonoBehaviour
{
	Quaternion rot = Quaternion.Euler(new Vector3(0, 0, 0));
	public void GetInput()
	{
		leftWheelDrive = Input.GetKey(KeyCode.A);
		rightWheelDrive = Input.GetKey(KeyCode.S);
		leftWheelReverse = Input.GetKey(KeyCode.Z);
		rightWheelReverse = Input.GetKey(KeyCode.X);
	}

	private void Drive()
	{
		leftWheelForce = (leftWheelDrive) ? setleftWheelForce : 0;
		rightWheelForce = (rightWheelDrive) ? setrightWheelForce : 0;

		float difference = leftWheelForce - rightWheelForce;
		Debug.Log(difference);
		if (difference > 0)
        {
			gameObject.transform.rotation = Quaternion.Euler(new Vector3(0, rot.eulerAngles.y+ difference*300 * Time.deltaTime, 0));
			//gameObject.transform.RotateAround(gameObject.transform.position, Vector2.up, -difference* 300 * Time.deltaTime);

		}
		else if (difference < 0)
		{
			//gameObject.transform.RotateAround(gameObject.transform.position, Vector2.down, difference * 300 * Time.deltaTime);
			gameObject.transform.rotation = Quaternion.Euler(new Vector3(0, rot.eulerAngles.y+difference*300 * Time.deltaTime, 0));
		}

		gameObject.transform.Translate(new Vector3(0, 0, -Mathf.Sqrt(leftWheelForce * leftWheelForce + rightWheelForce * rightWheelForce)));
		rot = gameObject.transform.rotation;
	}

	private void UpdateWheelPoses()
	{
		frontDriverT.transform.rotation = Quaternion.Euler(new Vector3(frontDriverT.transform.rotation.x, frontDriverT.transform.rotation.y, frontDriverT.transform.rotation.z+ leftWheelForce * 300 * Time.deltaTime));
		frontPassengerT.transform.rotation = Quaternion.Euler(new Vector3(frontPassengerT.transform.rotation.x, frontPassengerT.transform.rotation.y, frontPassengerT.transform.rotation.z + rightWheelForce * 300 * Time.deltaTime));

	}

	private void UpdateWheelPose(WheelCollider _collider, Transform _transform)
	{

	}

	private void FixedUpdate()
	{
		GetInput();
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
	private bool leftWheelReverse;
	private bool rightWheelReverse;

	public Transform frontDriverT, frontPassengerT;
	private float leftWheelForce;
	private float rightWheelForce;
	public float setleftWheelForce = 0.5f;
	public float setrightWheelForce = 0.5f;
}
