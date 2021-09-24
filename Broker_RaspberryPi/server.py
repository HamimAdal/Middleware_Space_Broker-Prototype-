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
                        #print(data)
                        temp = data.split(':')
			print(temp)

			choice = temp[0]
                        
                                         

			if (choice == '1'):

    			                                       
                                x = temp[1] 
                                y = temp[2]  
                                
                                x = int(x)
                                y = int(y)
                                	
			
				queryspecific.query(x,y)
    				message = str(queryspecific.unitvalue)
                                
				message = sp + message
				
                                print(message)
				tcpCliSock.sendall(message)

                                
				
				
                           
				
    			        #tcpSerSock.close()  
				break

 
    			#time.sleep(2)
    			


                        if (choice == '2'):
                          
                                
                                x = temp[1] 
                                y = temp[2]  

                                x = int(x)
                                y = int(y)
			
                                
                                duty_s = temp[3] 
    			        duty_s = int(duty_s)

    				modifyspecific.modify(duty_s,x,y)

				
				break
   		        
                          
                        if (choice == '3'):

                               	                                                             
                                x = temp[1] 
                                y = temp[2]  

                                x = int(x)
                                y = int(y)

                                
                                duty_s = temp[3] 
    			        duty_s = int(duty_s)

    				maintainspecific.maintainspecific(duty_s,x,y)
				break

                        if (choice == '4'):

    			                                       
                               
                                	
			
				query.query()
    				message = str(query.unitvalue)
                                
                                
				message = sp + message
                                
    				tcpCliSock.sendall(message)
    			        #tcpSerSock.close()  
				break

                        if (choice == '5'):
                          
                                
                          
                                duty_s = temp[3] 
    			        duty_s = int(duty_s)

    				modify.modify(duty_s)
				break
   			 

                        if (choice == '6'):
                                                             
                                duty_s = temp[3] 
    			        duty_s = int(duty_s)
                                maintain.maintain(duty_s)

		        
			if (choice == '7'):

    			                                       
                        	with open("floorplan.jpg", "rb") as imageFile:
    					strToSend = base64.b64encode(imageFile.read())       

                                tcpCliSock.sendall(sp+strToSend)
				

				#print(len(strToSend))
			   	tcpCliSock,addr = tcpSerSock.accept()
        			#print '...connected from :', addr

	                        dat = ''
        	                dat = tcpCliSock.recv(BUFSIZE)
		                
                                #print(dat)
				#print(len(dat))
                                #res = [int(i) for i in dat.split() if i.isdigit()] 
				res = int(filter(str.isdigit, dat))

		

                                #print(res)
				
                                
				first = strToSend[res] 
                                second = strToSend[res+1]
				#print(first)
				#print(second)
                             
                                strToSend2 = sp+first + second + strToSend 
                                tcpCliSock.sendall(strToSend2)	
				
                           
				
    			        #tcpSerSock.close()  
				break
			time.sleep(2)
    			
                        


                        
        except KeyboardInterrupt:
                ldr.close()
                GPIO.cleanup()

tcpSerSock.close();






