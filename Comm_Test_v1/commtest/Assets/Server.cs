using System.Collections;
using System.Collections.Generic;
using System.Net.Sockets;
using System.Net;
using System.Text;


using UnityEngine;

public class Server : MonoBehaviour
{
    Socket socket;
    byte[] bytes = new byte[1024];

    // Start is called before the first frame update
    void Start()
    {
        socket = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp);
        IPAddress ipAddress = Dns.GetHostEntry("localhost").AddressList[0];

        socket.Connect(IPAddress.Parse("127.0.0.1"), 666);
        Debug.Log("Socket connected to " + socket.RemoteEndPoint.ToString());
    }

    // Update is called once per frame
    void Update()
    {
        socket.Receive(bytes);
        string shit = Encoding.ASCII.GetString(bytes);
        Debug.Log(shit);
    }
}
