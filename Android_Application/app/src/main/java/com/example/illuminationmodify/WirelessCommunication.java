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



            if (requestID.equals("1") || requestID.equals("4"))
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
    String k ,k1,k2,ko1;


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

            int f,f1,f2 ;


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

            int stringlength = k.length();
            String stringlen = Integer.toString(stringlength);

            k1 = inp.readUTF();
            f1 = inp.available();



            char c1 ;
            String msg1= "";
            byte[] ary1 = new byte[f1];
            inp.read(ary1);
            for (byte bt1 : ary1) {
                c1 = (char) bt1;
                msg1= String.valueOf(c1);
                k1 = k1 + msg1;
            }

            int stringlength1 = k1.length();
            int sum= stringlength + stringlength1;



            socket = new java.net.Socket(inetAddress,21567);


            DataOutputStream dataOutputStream1 = new DataOutputStream(socket.getOutputStream());
            dataOutputStream1.writeUTF(stringlen);
            dataOutputStream1.flush();

            DataInputStream inp1 = new DataInputStream(socket.getInputStream());

            k2 = inp1.readUTF();
            f2 = inp1.available();


            char c2 ;
            String msg2= "";
            byte[] ary2 = new byte[f2];
            inp1.read(ary2);
            for (byte bt2 : ary2)
            {
                c2 = (char) bt2;
                msg2 = String.valueOf(c2);
                k2 = k2 + msg2;
            }



            String firstTwoChars = k2.substring(0, 2);
            ko1 = k+ firstTwoChars +k1;



            socket.close();
        }
        catch (UnknownHostException e){e.printStackTrace();}catch (IOException e){e.printStackTrace();}
        return null;
    }
}

