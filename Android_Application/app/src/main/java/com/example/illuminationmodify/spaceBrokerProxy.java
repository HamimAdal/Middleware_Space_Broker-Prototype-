package com.example.illuminationmodify;



interface characteristicsInterface
{
    String query( SpecificLocation  location );
    String query(  UserLocation location );

    void modify( SpecificLocation location,String Value);
    void modify(  UserLocation location,String Value);

    void maintain( SpecificLocation location,String Value);
    void maintain(  UserLocation location,String Value);


}

class Characteristics   implements characteristicsInterface
{
    // *****************
    // CMD is the string or message which will be sent  to the space broker from the application which consists
    // 1. requestType and/or
    // 2. location and/or
    // 3. value
    // *****************

    public static String requestType ;
    public static String CMD;
    String queriedValue= "";


    @Override
    public String query( SpecificLocation location )  // ****** implementation of query method at Specific Location******
    {

        requestType = "1";

        CMD = requestType + ":" + location.X + ":" + location.Y ;
        wirelessCommunication wirelesscommunication = new wirelessCommunication();
        wirelesscommunication.execute(CMD);  // ****** wireless communication between app and raspberry pi (Space Broker) ******

        try
        {
            Thread.sleep(5000);
        }
        catch (InterruptedException e)

        {
            e.printStackTrace();
        }


        queriedValue = wirelesscommunication.k;
        return queriedValue;

    }
    @Override
    public String query(  UserLocation location )  // ****** implementation of query method at User Location******
    {

        requestType = "4";

        CMD = requestType + ":" + location.X + ":" + location.Y ;
        wirelessCommunication wirelesscommunication = new wirelessCommunication();
        wirelesscommunication.execute(CMD);  // ****** wireless communication between app and raspberry pi (Space Broker) ******

        try
        {
            Thread.sleep(5000);
        }
        catch (InterruptedException e)

        {
            e.printStackTrace();
        }


        queriedValue = wirelesscommunication.k;
        return queriedValue;

    }

    @Override
    public void modify( SpecificLocation location,String Value)  // ****** implementation of modify method at Specific Location ******
    {

        requestType = "2";

        CMD = requestType + ":" + location.X + ":" + location.Y + ":" + Value;
        wirelessCommunication wirelesscommunication = new wirelessCommunication();
        wirelesscommunication.execute(CMD);  // ****** wireless communication between app and raspberry pi (Space Broker) ******





    }

    @Override
    public void modify(  UserLocation location,String Value)  // ****** implementation of modify method at User Location ******
    {


        requestType = "5";

        CMD = requestType + ":" + location.X + ":" + location.Y + ":" + Value;
        wirelessCommunication wirelesscommunication = new wirelessCommunication();
        wirelesscommunication.execute(CMD);  // ****** wireless communication between app and raspberry pi (Space Broker) ******

    }


    @Override
    public void maintain( SpecificLocation location,String Value)  // implementation of maintain method at Specific Location
    {


        requestType = "3";

        CMD = requestType + ":" + location.X + ":" + location.Y + ":" + Value;
        wirelessCommunication wirelesscommunication = new wirelessCommunication();
        wirelesscommunication.execute(CMD);  // ****** wireless communication between app and raspberry pi (Space Broker) ******

    }

    @Override
    public void maintain(  UserLocation location,String Value)  // implementation of maintain method at User Location
    {


        requestType = "6";

        CMD = requestType + ":" + location.X + ":" + location.Y + ":" + Value;
        wirelessCommunication wirelesscommunication = new wirelessCommunication();
        wirelesscommunication.execute(CMD);  // ****** wireless communication between app and raspberry pi (Space Broker) ******

    }



}



