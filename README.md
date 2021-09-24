# Middleware_Space_Broker

Space Broker is a middleware that  works as a mediator between personal devices and physical IoT devices in a smart space to make the user experience more personalized. So far, the following tasks were accomplished:

    ---- We made a demonstration in a small-scale IoT environment (using foam core) based on the framework we have designed. We named the framework 'Space Broker'.

    ---- Two characteristics that include illumination and temperature have been tested in a limited-scaled IoT environment. 
Currently, we are approaching towards designing the model for Security which will be implemented in the future.

---- An android device was used as a personal device to facilitate our experiment so that a user can have a similar experience interacting with space when he/she is in a real IoT environment. 

---- The task of building the interface 'Space Broker', was accomplished on the Raspberry-pi where LEDs, sensors, cooling fans and other equipments were used as an alternative to real physical devices.

# Getting Started

This is the repository which holds code both for the 'Space Broker API' and the 'Space Broker System' that was built on the raspberry-pi. 
To run the Space Broker Server, run main from Broker_RaspberryPi/server.py from the Raspberry-pi.

# How It Works

After the Space Broker server is up and running, it is ready to take any command from the Space Broker application.

**Space Broker API**

So far three kind of methods have been examined: query, modify and maintain. All the methods have been tested for both Specific Location and User Location. 

An overhead camera was used to track a green ball, which is our proxy for the user in the small scaled environment. So, the overhead camera feeds the space broker with the user location. For the user to point to any speciofic location, he/she has to download a map (floor plan), which is the 2D top view of the floor plan captured also by the overheasd camera. By clciking on the floorplan, a user can spot any location.

In our source code for the Space Broker application, the application developer can use any of the following methods (from Android_Application/app/src/main/java/com/example/illuminationmodify/spaceBrokerProxy.java) to make a request to the Space Broker system (built on raspberry-pi):


    String query (SpecificLocation  location);
    String query (UserLocation location);

    void modify (SpecificLocation location,String Value);
    void modify (UserLocation location,String Value);

    void maintain (SpecificLocation location,String Value);
    void maintain (UserLocation location,String Value);

After one of these methods is invoked in the Space Broker Application, the similar named method (from server.py in the Broker_RaspberryPi folder) in the Space Broker system gets trigerred to deploy any action.   

**Space Broker system (built on raspberry-pi)**

The Space Broker system was built on the raspberry-pi. The devices and sensors we used include but not limited to LEDs, Light sensors, cooling fan, temperaure sensors, raspberry-pi camera etc. The implementation of the query, modify and maintain methods can be found in the Broker_RaspberryPi/query.py, Broker_RaspberryPi/modify.py and Broker_RaspberryPi/maintain.py  (Broker_RaspberryPi/queryspecific.py, Broker_RaspberryPi/modifyspecific.py, Broker_RaspberryPi/maintainspecific.py for the specific locations) respectively (for the user lcoation). The fucntionalities for the overhead camera can be found in the Broker_RaspberryPi/location.py file. 

# Video Demonstration

Following are video links of this project when Space Broker is up and running (we used a red card, as a proxy to the user in the videos, later we shifted that with a green ball which was easy to work with):

---- https://www.youtube.com/watch?v=-L-g3scnlGY (for specific location)

---- https://www.youtube.com/watch?v=WUGQ4JW9Wbs (for user location)


