using System.Collections;
using System.Collections.Generic;
using System.Net.Sockets;
using System.Net;
using System.Text;

using System;


public class ServerController : UnityEngine.MonoBehaviour
{
    Socket socket;
    byte[] bytes = new byte[1024];
    public string sendBuffer = "";

    public UnityEngine.GameObject boeBot;
    EndPoint p;
    double timer = 0;
    // Start is called before the first frame update
    void Start()
    {
        socket = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp);
        IPAddress ipAddress = Dns.GetHostEntry("localhost").AddressList[0];

        socket.Connect(IPAddress.Parse("127.0.0.1"), 667);
        UnityEngine.Debug.Log("Socket connected to " + socket.RemoteEndPoint.ToString());
    }

    // Update is called once per frame
    void Update()
    {
        UnityEngine.Debug.Log(socket.Connected);
        if (socket.Connected)
        {
            // Receiving
            byte[] rcvLenBytes = new byte[4];
            //UnityEngine.Debug.Log(p + " " + socket);
            socket.Receive(rcvLenBytes);
            int rcvLen = System.BitConverter.ToInt32(rcvLenBytes, 0);
            byte[] rcvBytes = new byte[rcvLen];
            socket.Receive(rcvBytes);
            string rcv = System.Text.Encoding.ASCII.GetString(rcvBytes);

            string[] messages = rcv.Split('\n');

            float[] motorInput = { 0, 0 };

            foreach (string message in messages)
            {
                //UnityEngine.Debug.Log(message);
                UnityEngine.Debug.Log(message);
                string[] info = message.Split(',');
                if (info.Length > 2)
                {
                    if (info[1].Equals("leftMotor"))
                    {
                        motorInput[1] = Single.Parse(info[2]);
                    }
                    else if (info[1].Equals("rightMotor"))
                    {
                        motorInput[0] = Single.Parse(info[2]);
                    }
                }
            }

            boeBot.GetComponent<BoeBotMove>().GetInput(motorInput[0] / 5.0f, motorInput[1] / 5.0f);

            UnityEngine.Debug.Log("BRAINFUCK");
            int toSendLen = System.Text.Encoding.ASCII.GetByteCount(sendBuffer);
            byte[] toSendBytes = System.Text.Encoding.ASCII.GetBytes(sendBuffer);
            byte[] toSendLenBytes = System.BitConverter.GetBytes(toSendLen);
            //socket.Send(toSendLenBytes);
            socket.Send(toSendBytes);
            UnityEngine.Debug.Log(sendBuffer);
            sendBuffer = "";
            //timer = 1;
            
            //timer -= UnityEngine.Time.deltaTime;
        }
        else
        {
            UnityEngine.Debug.Log("trying to connect");
            socket.Connect(IPAddress.Parse("127.0.0.1"), 667);
        }
    }
}