# Middleware_Space_Broker

Space Broker is a middleware that  works as a mediator between personal devices and physical IoT devices in a smart space to make the user experience more personalized. So far, the following tasks were accomplished:

---- We made a demonstration in a small-scale IoT environment (using foam core) based on the framework we have designed. We named the framework 'Space Broker'.

---- Two characteristics that include illumination and temperature have been tested in a limited-scaled IoT environment. 
Currently, we are approaching towards designing the model for Security which will be implemented in the future.

---- An android device was used as a personal device to facilitate our experiment so that a user can have a similar experience interacting with space when he/she is in a real IoT environment. 

---- The task of building the interface 'Space Broker', was accomplished on the Raspberry-pi where LEDs, sensors, cooling fans and other equipments were used as an alternative to real physical devices.

# Getting Started

This is the repository which holds code both for the 'Space Broker API' and the 'Space Broker System' that was built on the raspberry-pi. 
To run the Space Broker Server, run main from Broker_RaspberryPi/server.py from the Raspberry-pi. After the Space Broker server is up and running, it is ready to take any command from the Space Broker application.

# Demonstration

**Space Broker API**

So far three kind of methods have been examined: query, modify and maintain. All the methods have been tested for both Specific Location and User Location. 

An overhead camera was used to track a red card (an alternate to the real user, later we swapped that with a green ball which was easy to work with; the updated code will be uploaded soon), which is our proxy for the user in the small scaled environment. So, the overhead camera feeds the space broker with the user location. For the user to point to any speciofic location, he/she has to download a map (floor plan), which is the 2D top view of the floor plan captured also by the overhead camera. By clciking on the floorplan, a user can spot any location.

<table>
  <tr>
    <td>Figure 1: Screenshot of the Appllication</td>
  </tr>
  <tr>
    <td><img src="https://github.com/HamimAdal/Middleware_Space_Broker/blob/main/appscreenshot.jpg" width=800 height=400></td>
  </tr>
</table>


In our source code for the Space Broker application, the application developer can use any of the following methods (from Android_Application/app/src/main/java/com/example/illuminationmodify/spaceBrokerProxy.java) to make a request to the Space Broker system (built on raspberry-pi):

    String query(Location  location);
    void modify(Location location,String Value);
    void maintain(Location location,String Value);

After one of these methods is invoked in the Space Broker Application, the similar named method (from Broker_RaspberryPi/server.py) in the Space Broker system gets trigerred to deploy any action.   

**Space Broker system (built on raspberry-pi)**

The Space Broker system was built on the raspberry-pi. The devices and sensors we used include but not limited to LEDs, Light sensors, cooling fan, temperaure sensors, raspberry-pi camera etc. The implementation of the query, modify and maintain methods can be found in the Broker_RaspberryPi/query.py, Broker_RaspberryPi/modify.py and Broker_RaspberryPi/maintain.py  (Broker_RaspberryPi/queryspecific.py, Broker_RaspberryPi/modifyspecific.py, Broker_RaspberryPi/maintainspecific.py for the specific locations) respectively (for the user lcoation). The fucntionalities for the overhead camera can be found in the Broker_RaspberryPi/location.py file. 

<table>
  <tr>
    <td>Figure 2: Architecture of the floorplan</td>
    <td>Figure 3: Captured picture from the overhead camera</td>
  </tr>
  <tr>
    <td><img src="https://github.com/HamimAdal/Middleware_Space_Broker/blob/main/floorplan.jpg" width=400 height=300></td>
    <td><img src="https://github.com/HamimAdal/Middleware_Space_Broker/blob/main/foamcorebox.jpg" width=400 height=300></td>
 
  </tr>
</table>

**User Manual**

The Home Screen (Figure 1) has two different options: the ‘User Location’ button and the ‘Specific Location’ button.
Clicking on the ‘User Location’ button in the Home Screen will take the user into the User Location Screen, where he/she will be able to query, modify or maintain the value of the illumination at his/her location. Querying the value of illumination requires the user to press the ‘Query with user location’ button, and as a response, the queried result gets displayed on the screen. But requesting to modify or maintain illumination at the user location requires the user to provide the desired value in the input fields before pressing the corresponding buttons assigned for it. For each of the methods, the user location is continuously retrieved from the overhead camera.

**Space Settings**

Figure 4 and 5 demonstrates a use case scenario illustrating what happens when a user uses the Space Broker Application to launch a request at his/her location. The size of the camera frame shown in the figure is approximately 500 × 480 grid. To keep things simple, the entire space was divided  into four regions: Space 1, Space 2, Space 3, and Space 4. The following table provides the inkling of the space partitions along with LED devices and LDR sensors associated with each region. For a better understanding, we can relate the environment setup with Figure 2, which provides the architectural layout of the floor plan labeling each component used in the experiment along with the space split-ups.

| Region | Space Coverage (x,y denotes horizontal and vertical pixels of the camera frame) |Assigned devices |
| :---: | :---: | :---: |
| Space 1 | 0 ≤ x ≤ 250 and 241 ≤ y ≤ 480 | LED1, LED2, LDR1 |
| Space 2 | 251 ≤ x ≤ 500 and 241 ≤ y ≤ 480 | LED3, LED4, LDR2 |
| Space 3 | 0 ≤ x ≤ 250 and 0 ≤ y ≤ 240 | LED5, LED6, LDR3 |
| Space 4 | 251 ≤ x ≤ 500 and 0 ≤ y ≤ 240 | LED7, LED8, LDR4 |

When the user is in Space 1, the LED1, LED2, and LDR1 become operational and attempts to deliver the service. Similarly, LED3, LED4, and LDR2 are responsible for providing service in Space 2; LED5, LED6, and LDR3 in Space 3; LED7, LED8, and LDR4 in SPACE 4. Each of the LED devices has its value range between 0 to 100 (0 means the LED is turned off, and 100 means the LED is at its maximum capacity). Each of the LDR devices also has its own value range between 0 to 1000 units (0 means dark and 1000 means the maximum illumination level). When the designated LED devices fail to achieve the intended illumination level for a particular region, the other LED devices contribute to fulfilling the purpose. For example, If a user wants to have a ‘Z’ unit of illumination in Space 1, LED1 and LED2 will start operating to deliver the desired level of luminance. If both of the LEDs reach the maximum level of their capacity and yet fail to provide the intended luminosity level, then the nearest LEDs to Space 1 will contribute to fulfilling the request. Therefore, as there is no wall in between Space 1 and Space 2, LED3 and LED4 from Space 2 will start functioning to assist Space 1 in achieving the requested luminance level (we have not fed the implementation with any algorithm yet, so the LED devices were chosen in increasing order. i.e., LED1, LED2, LED3 … … LED7, LED8). 

<table>
  <tr>
    <td>Figure 4: An attempt to achieve 850 unit of illumination at the user location.</td>
    <td>Figure 5: 850 unit of illumination achieved at the user location.</td>
  </tr>
  <tr>
    <td><img src="https://github.com/HamimAdal/Middleware_Space_Broker/blob/main/modifybefore.png" width=400 height=300></td>
    <td><img src="https://github.com/HamimAdal/Middleware_Space_Broker/blob/main/modifyafter.png" width=400 height=300></td>
 
  </tr>
</table>

**Use Case Demonstartion with User Location**

We will now use Figure 4 and 5 to manifest an interaction between the user and the Space Broker where a user asks the Space Broker to modify illumination at his/her location. Suppose a user (the green ball) desires to achieve 850 units of illumination at his/her user location. The overhead camera detects the green ball avatar in Space 4 (X= 454, Y= 84, which falls in the Space 4 region).  When a user puts the value of 850 as an input in the User Location Screen and presses the ‘Modify With User Location’ button, the value gets passed from the application to the Space Broker (via invoking the proxy method, in this case ‘modify’ method), setting off the identically named method in the Space Broker. As a result, LED7 and LED8 become functional in Space 4 (in Figure 4), attempting to reach the target of 850 units of illumination. In Figure 5, we can see that the intended amount of 850 units has been achieved when LED7 is at its maximum capacity (100 unit), and LED8  reaches the value of 90 unit. Querying and maintaining an illumination level is also achieved in the same way with an exception for the maintenance request, where the application has to continuously feed the Space Broker with user’s current position in order to maintain a constant luminance level around him/her.

**Use Case Demonstration with Specific Location**

To initiate  user requests regarding Specific Locations, a user needs to enter into the Specific Location Screen by pressing the ‘Specific Location’ button from the Home Screen. In the Specific Location Screen, a user can request to display the top view image of the 2D space by pressing the ‘Show the Space’ button. By tapping on the space image displayed, a user will be able to set the location which he/she wants to query, modify, or maintain a value for illumination. After selecting the desired spot (for example, X=349, Y=118 as shown in Figure 1) from the space image, a user needs to provide values in the corresponding input fields before pressing the designated buttons assigned for modifying or maintaining illumination. Querying for illumination only requires the user to press the ‘Query With Specific Location’ button, and as a consequence, the queried value is displayed on the screen.


# Video Presentation

Following are video links of this project when Space Broker is up and running (use headphone for better experience):

---- https://www.youtube.com/watch?v=-L-g3scnlGY (for specific location)

---- https://www.youtube.com/watch?v=WUGQ4JW9Wbs (for user location)

# License

MIT License

Copyright (c) 2021 HamimAdal

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

# Reference

---- https://pysource.com/2019/06/05/control-webcam-with-servo-motor-and-raspberry-pi-opencv-with-python/

---- https://github.com/trieutuanvnu/AndroidWifiRasPi

---- https://github.com/trieutuanvnu/PiServoControlWifi




