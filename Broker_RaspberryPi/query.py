import ldr
import RPi.GPIO as GPIO
import time
import location

GPIO.setmode(GPIO.BCM)


value = 0 	        # this variable will be used to store the ldr value
ldrpin = [4,17,6,5] 	# ldr is connected with pin number 4,17,6,5s


def rc_time (ldr):
   
 
        #Output on the pin for

    GPIO.setup(ldr, GPIO.OUT)
    GPIO.output(ldr, GPIO.LOW)
    time.sleep(0.1)
 
        #Change the pin back to input

    GPIO.setup(ldr, GPIO.IN)
    currentTime = time.time()
    diff = 0
 
        #Count until the pin goes high


    while (GPIO.input(ldr) == GPIO.LOW):
        diff  = time.time() - currentTime
    
    return diff

def query ():
    
    
    global value
    global unitvalue
    location.locateInitialize()
    while True:

        location.locate()
        x = location.x
        y = location.y
      
        if location.objectDetection == 1:

 
            if x >= 0 and x<=250 and y >= 251 and y <= 500 :
    	        value = rc_time(ldrpin[0]) * 1000
	
            if x >= 251 and x<=500 and y >= 251 and y <= 500 :
    	        value = rc_time(ldrpin[1]) * 1000

            if x >= 0 and x<=250 and y >= 0 and y <= 250 :
    	        value = rc_time(ldrpin[2]) * 1000
	
            if x >= 251 and x<=500 and y >= 0 and y <= 250 :
    	        value = rc_time(ldrpin[3]) * 1000

            value = 1000 - value
            if value < 0:
       	        value = 0
            unitvalue= value
            print("\n")
	
            print("Current illumination level: {} ".format(value) )   
            break
           







