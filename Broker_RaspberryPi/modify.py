import ldr
import RPi.GPIO as GPIO
import time
import location

GPIO.setmode(GPIO.BCM)


value = 0 	        # this variable will be used to store the ldr value
ldrpin = [4,17,6,5] 	# ldr is connected with pin number 4,17,6,5s


duty = [55,0,0,0,0,0,0,0]



def setup(x,y,zone):


    global duty
    global i
    global flag
    
       
    if x >= 0 and x<=250 and y >= 251 and y <= 500 and zone != 1:

	 duty = [55,0,0,0,0,0,0,0]
         i = 0
         flag = 0
         zone = 1 

    if x >= 251 and x<=500 and y >= 251 and y <= 500 and zone != 2:

	 duty = [0,0,55,0,0,0,0,0]
         i = 2 
         flag = 2
         zone = 2

    if x >= 0 and x<=250 and y >= 0 and y <= 250 and zone != 3:

	 duty = [0,0,0,0,55,0,0,0]
         i = 4
         flag = 4
         zone = 3 

    if x >= 251 and x<=500 and y >= 0 and y <= 250 and zone != 4:

	 duty = [0,0,0,0,0,0,55,0]
         i = 6 
         flag = 6
         zone = 4
 
    return zone
    
def increase ():
   
    
    global duty
    global i
    global flag

    

    if duty[i] <= 0 :
         duty[i] = 0 
         if i >= 0 and i != flag:
         	i = i-1
        	#duty[i] -= 5
	 if i == -1 and i-1 != flag:
         	i = len(duty) - 1
    else:
         duty[i] -= 5

    j=0

    for x1 in duty:
    	
    	 ldr.pwm_led[j].ChangeDutyCycle(x1)  
         j=j+1

    time.sleep(0.25) 


def decrease ():
   
    global duty
    global i
    global flag
    
    if duty[i] >= 100 :
         duty[i] = 100 
         if i <= len(duty) - 1 and i+1 != flag:
         	i = i+1
                
        	#duty[i] += 5
         if i == len(duty)   and i+1 != flag:
         	i = 0
        	

    else:
         duty[i] += 5
         

    j=0
    for x1 in duty:
    	
    	 ldr.pwm_led[j].ChangeDutyCycle(x1)  
         j=j+1
         
  
    time.sleep(0.25) 


def modify (duty_s):
    
    
    global value
    global duty
    
    duty_s = 1000 - duty_s   

    lower = duty_s - 30
    upper = duty_s + 30

    zone = 0

    location.locateInitialize()
    while True:

        location.locate()
        x = location.x
        y = location.y
      
        if location.objectDetection == 1:

            zone = setup(location.x,location.y,zone)
        
 
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
            print("\n")
	
            print("Current illumination level: {} ".format(value) )   
        
            j=0
    
            for x1 in duty:
                print("Current LED{} Value: {} ".format(j+1,x1))  
                j=j+1

            value = original_value
        
				
            if (value > upper):			
                decrease()

            if (value >= lower and value <= upper):
                
               
                
                newzone = zone
                while True:
                      location.locate()
		      zone = setup(location.x,location.y,zone)

                      print(" MODIFIED LDR VALUE ACHIEVED :")
		      original_value = value
		      value = 1000 - value
    		
		      print(value)
                      value = original_value
       	              time.sleep(0.25) 

                      if (zone != newzone):
                           break
              

            if (value < lower):
                
	        increase()

        if location.objectDetection == 0:
            j=0

	    for x1 in duty:
    	
    	        ldr.pwm_led[j].ChangeDutyCycle(0)  
                j=j+1


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





