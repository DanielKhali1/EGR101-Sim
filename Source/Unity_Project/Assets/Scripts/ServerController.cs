using System.Collections;
using System.Collections.Generic;
using System.Net.Sockets;
using System.Net;
using System.Text;

public class ServerController : UnityEngine.MonoBehaviour
{
    Socket socket;
    byte[] bytes = new byte[1024];

    public UnityEngine.GameObject boeBot;
    

    // Start is called before the first frame update
    void Start()
    {
        socket = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp);
        IPAddress ipAddress = Dns.GetHostEntry("localhost").AddressList[0];

        socket.Connect(IPAddress.Parse("127.0.0.1"), 666);
        UnityEngine.Debug.Log("Socket connected to " + socket.RemoteEndPoint.ToString());
    }

    // Update is called once per frame
    void Update()
    {
        // Receiving
        byte[] rcvLenBytes = new byte[4];
        socket.Receive(rcvLenBytes);
        int rcvLen = System.BitConverter.ToInt32(rcvLenBytes, 0);
        byte[] rcvBytes = new byte[rcvLen];
        socket.Receive(rcvBytes);
        string rcv = System.Text.Encoding.ASCII.GetString(rcvBytes);

        string[] messages = rcv.Split('\n');


        foreach (string message in messages)
        {
            string[] info = message.Split(',');
            if(info[0] == "0")
            {
                //boeBot.GetComponent<BoeBotMove>().moveRightWheel();
            }
            else if(info[0] == "1")
            {
                //boeBot.GetComponent<BoeBotMove>().moveLeftWheel();
            }
        }


    }
}