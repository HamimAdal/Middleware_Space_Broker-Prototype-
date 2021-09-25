package com.example.illuminationmodify;


interface characteristicsInterface // Space Broker API methods
{
    String query(Location  location);
    void modify(Location location,String Value);
    void maintain(Location location,String Value);
}

class Characteristics   implements characteristicsInterface
{
    public static String requestId ;
    public static String STS;
    // *****************
    // STS is the string or message which will be sent to the space broker (raspberry-pi) from the application which concatenates-
    //      1. requestId and/or,
    //      2. location and/or,
    //      3. value.
    // *****************
    String queriedValue= "";

    @Override
    public String query( Location location )  // implementation of query method
    {
        requestId = "1";

        STS = requestId + ":" + location.X + ":" + location.Y ;
        wirelessCommunication wireless = new wirelessCommunication();
        wireless.execute(STS);  // sending a string to the Space Broker (raspberry-pi) which includes requestId (for query) and location.

        try
        {
            Thread.sleep(5000);
        }
        catch (InterruptedException e)

        {
            e.printStackTrace();
        }


        queriedValue = wireless.k;
        return queriedValue;
    }

    @Override
    public void modify( Location location,String Value)  //implementation of modify method
    {
        requestId = "2";

        STS = requestId + ":" + location.X + ":" + location.Y + ":" + Value;
        wirelessCommunication wireless = new wirelessCommunication();
        wireless.execute(STS);  //sending a string to the Space Broker (raspberry-pi) which includes requestId (for modify), location and the value to be modified.
    }

    @Override
    public void maintain( Location location,String Value)  // implementation of maintain method
    {
        requestId = "3";
        STS = requestId + ":" + location.X + ":" + location.Y + ":" + Value;
        wirelessCommunication wireless = new wirelessCommunication();
        wireless.execute(STS);  //sending a string to the Space Broker (raspberry-pi) which includes requestId (for maintain), location and the value to be maintained.
    }

}



