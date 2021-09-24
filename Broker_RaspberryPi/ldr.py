import RPi.GPIO as GPIO
import time

GPIO.setmode(GPIO.BCM)

#led = [18,23,26,19]  #18,23,26,19 ,21 ,20 16,12
led = [18,23,26,19,21,20,16,12]	

def initialize():   

    for x1 in led:
    	
    	 GPIO.setup(x1, GPIO.OUT) 	
          
    global pwm_led
    #pwm_led = [0,0,0,0]
    pwm_led = [0,0,0,0,0,0,0,0]
    i=0 

    for x1 in led:
     	
    	 pwm_led[i] = GPIO.PWM( x1, 50)  

         if i==0 or i == 2 or i == 4 or i == 6 :
            
             pwm_led[i].start(55)
         else:

             pwm_led[i].start(0) 

         i = i+1

def close():

    for x1 in pwm_led:   	
         x1.stop()
    

























