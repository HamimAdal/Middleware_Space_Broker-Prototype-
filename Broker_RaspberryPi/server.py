import ldr
import queryspecific
import modifyspecific
import maintainspecific
import query
import modify
import maintain
import base64
 

from socket import *
import time
import RPi.GPIO as GPIO


ldr.initialize()

HOST = ''
PORT = 21567
BUFSIZE = 1024
ADDR = (HOST,PORT)

tcpSerSock = socket(AF_INET, SOCK_STREAM)
tcpSerSock.bind(ADDR)
tcpSerSock.listen(5)

while True:

        print 'Waiting for connection'
        tcpCliSock,addr = tcpSerSock.accept()
        print '...connected from :', addr

        try:
                while True:
                        data = ''
                        data = tcpCliSock.recv(BUFSIZE)
                       
                        if not data:
                                break
                 
                        print(data)
			sp = data[0] + data[1]                 
  			data = data [2:]       
                        temp = data.split(':')
			
			choice = temp[0]
                        
			if (choice == '1'):

    			                                       
                                x = temp[1] 
                                y = temp[2]  
                                
				if (x == 'NULL' && y == 'NULL'):
			
					query.query()
    					message = str(query.unitvalue)
                                	        	 
			        else:	
                                	x = int(x)
                                	y = int(y)
                              		
					queryspecific.query(x,y)
    					message = str(queryspecific.unitvalue)
                                
				message = sp + message
				
				tcpCliSock.sendall(message) 
				break

 
                        if (choice == '2'):
                          
                                
                                x = temp[1] 
                                y = temp[2]  
				duty_s = temp[3] 
    			        duty_s = int(duty_s)
				
				if (x == 'NULL' && y == 'NULL'):
					
					modify.modify(duty_s)
				else:
                                	x = int(x)
                                	y = int(y)		                
    					modifyspecific.modify(duty_s,x,y)
	
				break
   		        
                          
                        if (choice == '3'):

                               	                                                             
                                x = temp[1] 
                                y = temp[2]  
				duty_s = temp[3] 
    			        duty_s = int(duty_s)
				
				if (x == 'NULL' && y == 'NULL'):
					maintain.maintain(duty_s)
				else:
                                	x = int(x)
                                	y = int(y)
    					maintainspecific.maintainspecific(duty_s,x,y)
				break
		        
			if (choice == '4'):

    			                                       
                        	with open("floorplan.jpg", "rb") as imageFile:
    					strToSend = base64.b64encode(imageFile.read())       
                                tcpCliSock.sendall(sp+strToSend)										   					                         		 			        
				break
			time.sleep(2)
    			                                              
        except KeyboardInterrupt:
                ldr.close()
                GPIO.cleanup()

tcpSerSock.close();






