import RPi.GPIO as GPIO
import time

GPIO.setmode(GPIO.BCM)


value = 0 	# this variable will be used to store the ldr value

ldrpin = [4,17,6,5]  #4,17,6,5


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

def query (x,y):
    
    global value
    global unitvalue

    time.sleep(3)

    if x >= 0 and x<=250 and y >= 251 and y <= 500 :
    	value = rc_time(ldrpin[0]) * 1000
	
    if x >= 251 and x<=500 and y >= 251 and y <= 500 :
    	value = rc_time(ldrpin[1]) * 1000
    if x >= 0 and x<=250 and y >= 0 and y <= 250 :
    	value = rc_time(ldrpin[2]) * 1000
	
    if x >= 251 and x<=500 and y >= 0 and y <= 250 :
    	value = rc_time(ldrpin[3]) * 1000

    original_value = value
    value = 1000 - value

    if value < 0:
        value = 0

    unitvalue= value

    print("\nCurrent illumination level: {} unit".format(value))
    
    value = original_value













