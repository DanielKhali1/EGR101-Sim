using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CameraFollowController : MonoBehaviour
{


	public void LookAtTarget()
	{
		Vector3 _lookDirection = objectToFollow.position - transform.position;
		Quaternion _rot = Quaternion.LookRotation(_lookDirection, Vector3.up);
		transform.rotation = Quaternion.Lerp(transform.rotation, _rot, lookSpeed * Time.deltaTime);
	}

	public void MoveToTarget()
	{
		Vector3 _targetPos = objectToFollow.position +
							 objectToFollow.forward * offset.z +
							 objectToFollow.right * offset.x +
							 objectToFollow.up * offset.y;
		transform.position = Vector3.Lerp(transform.position, _targetPos, followSpeed * Time.deltaTime);
	}

	private void FixedUpdate()
	{
		if(Input.GetKey(KeyCode.RightArrow) || Input.GetKey(KeyCode.D))
        {
			offset.x -= 0.5f;
        }

		if (Input.GetKey(KeyCode.LeftArrow) || Input.GetKey(KeyCode.A))
		{
			offset.x += 0.5f;
		}

		if (Input.GetKey(KeyCode.UpArrow) || Input.GetKey(KeyCode.W))
		{
			offset.y += 0.5f;
		}

		if (Input.GetKey(KeyCode.DownArrow) || Input.GetKey(KeyCode.S))
		{
			offset.y -= 0.5f;
		}

		if (Input.mouseScrollDelta.y > 0)
		{
			offset.z -= 2f;
		}

		if (Input.mouseScrollDelta.y < 0)
		{
			offset.z += 2f;
		}

		LookAtTarget();
		MoveToTarget();
	}

	public Transform objectToFollow;
	public Vector3 offset;
	public float followSpeed = 10;
	public float lookSpeed = 10;
}