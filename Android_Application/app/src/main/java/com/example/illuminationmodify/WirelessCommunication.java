package com.example.illuminationmodify;

import android.os.AsyncTask;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

class wirelessCommunication extends AsyncTask<String,Void,Void>
{
    Socket socket;
    String k ;

    @Override
    protected Void doInBackground(String... arg)
    {

        String string_message = arg[0];

        String arr[] = string_message.split(":");


        String requestID =  arr[0] ;


        try
        {
            InetAddress inetAddress = InetAddress.getByName("10.0.0.32");
            socket = new java.net.Socket(inetAddress,21567);


            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataOutputStream.writeUTF(string_message);
            dataOutputStream.flush();

            if (requestID.equals("1"))
            {


                DataInputStream inp = new DataInputStream(socket.getInputStream());

                int f;


                k = inp.readUTF();
                f = inp.available();



                char c = 'c';
                String msg= "";
                byte[] ary = new byte[f];
                inp.read(ary);
                for (byte bt : ary) {
                    c = (char) bt;
                    msg = String.valueOf(c);
                    k = k + msg;
                }


            }


            socket.close();
        }
        catch (UnknownHostException e){e.printStackTrace();}catch (IOException e){e.printStackTrace();}
        return null;
    }


}


class spaceImage extends AsyncTask<String,Void,Void>
{
    Socket socket;
    String k1;

    @Override
    protected Void doInBackground(String... arg)
    {
        String requestSpaceImage=arg[0];
        try
        {

            InetAddress inetAddress = InetAddress.getByName("10.0.0.32");
            socket = new java.net.Socket(inetAddress,21567);


            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataOutputStream.writeUTF(requestSpaceImage);
            dataOutputStream.flush();

            DataInputStream inp = new DataInputStream(socket.getInputStream());

            int f;

            k1 = inp.readUTF();
            f = inp.available();

            char c = 'c';
            String msg= "";
            byte[] ary = new byte[f];
            inp.read(ary);
            for (byte bt : ary) {
                c = (char) bt;
                msg = String.valueOf(c);
                k1 = k1 + msg;
            }

            socket.close();
        }
        catch (UnknownHostException e){e.printStackTrace();}catch (IOException e){e.printStackTrace();}
        return null;
    }
}

