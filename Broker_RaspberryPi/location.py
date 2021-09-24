import cv2
import numpy as np
import RPi.GPIO as GPIO
import time
import math
import ldr


def locateInitialize():

	
	
        global x_medium
        global y_medium
        

	global cap
	global rows
	global cols
	

	cap = cv2.VideoCapture(0)
	cap.set(3, 480)
	cap.set(4, 360)

	_, frame = cap.read()
	rows, cols, _ = frame.shape

	x_medium = int(cols / 2)
	y_medium = int(rows / 2)
	


def locate():
    
    
        global x_medium
        global y_medium
        global center
        global x 
        global y
        global objectDetection

    	_, frame = cap.read()
    	hsv_frame = cv2.cvtColor(frame, cv2.COLOR_BGR2HSV)
    
    	            # green color

    	low_green = np.array([161, 155, 84])
    	high_green = np.array([179, 255, 255])
    	green_mask = cv2.inRange(hsv_frame, low_green, high_green)
    	_, contours, _ = cv2.findContours(green_mask, cv2.RETR_TREE, cv2.CHAIN_APPROX_SIMPLE)
    	contours = sorted(contours, key=lambda x:cv2.contourArea(x), reverse=True)
    	objectDetection = 0

    	for cnt in contours:

        	(x, y, w, h) = cv2.boundingRect(cnt)
        
        	x_medium = int((x + x + w) / 2)
        	y_medium = int((y + y + h) / 2)
        	objectDetection = 1       
        	break
    
    	cv2.line(frame, (x_medium, 0), (x_medium, 360), (0, 255, 0), 2)
   	cv2.line(frame, (0,y_medium), (480,y_medium), (0, 255, 0), 2)
    	cv2.imshow("Frame", frame)
    
    	key = cv2.waitKey(1)
    
 

    	x = x_medium
    	y = 360 - y_medium 
    
   
    	if objectDetection ==  1:
    	
        	print("\n          OBJECT DETECTED !!!!!!\n")
    		print("The value of X is : {} and the value of Y is :{}".format(x,y))
    
   
    	if objectDetection ==  0:
     
		x_medium = int(cols / 2)
        	y_medium = int(rows / 2) 
        	print("NO OBJECT DETECTED !!!")
        
        
 
        
    
def cameraclose():

        cap.release()
        cv2.destroyAllWindows()












