#!/usr/bin/python3
import sys
import os
#from time import sleep
from gpiozero import CPUTemperature
from pyrebase import pyrebase
from pusher_push_notifications import PushNotifications    
import time




config = {
  "apiKey": "APIKEY",
  "authDomain": "yourProjectID.fdirebaseapp.com",
  "databaseURL": "https://yourProjectID-default-rtdb.firebaseio.com/",
  "storageBucket": "yourProjectID.appspot.com"
}

firebase = pyrebase.initialize_app(config)

auth = firebase.auth()    

email = "youremail"          
password ="yourpassword"                        
user = auth.sign_in_with_email_and_password(email, password)    

db = firebase.database()
 
beams_client = PushNotifications(                                                      
    instance_id='your-id',                            
    secret_key='yourkey',     
)                                                                                       


try:
    import RPi.GPIO as GPIO
except RuntimeError:
    print("Error")


PIN_13=27     #led pin  
PIN_7=4       #motion sensor pin

counter = 0
total_count =counter
GPIO_Out_List=[PIN_13]
GPIO_In_List=[PIN_7]

def turnoff():
    GPIO.output(PIN_13, GPIO.LOW)    
    print ('LED is OFF')
    return

def turnon():
    GPIO.output(PIN_13, GPIO.HIGH)    
    print ('LED is ON')
    return

def measure_temp():                                              
    cpu = CPUTemperature()
    return cpu.temperature

    
def main():  
    global PIN_13
    global PIN_7
    global GPIO_Chan_List
    global counter
    #global total_count
    
    data = {"Motion": counter}
    #db.child("message").push(data)
    data_temprature ={"Temperature": measure_temp()}             
    
    print ('Start LED blinking')    
    GPIO.setmode(GPIO.BCM)    
    GPIO.setwarnings(False)
    
    GPIO.setup(GPIO_Out_List, GPIO.OUT)  
    GPIO.setup(GPIO_In_List, GPIO.IN)    
   
    data_temperature ={"Temperature": measure_temp()}        
    db.child("/Temperature_Sensor").update(data_temperature,user['idToken'])  
    check_Temp = db.child("/Temperature_Sensor").get(user['idToken']).val()


    while True:
        data_temperature ={"Temperature": measure_temp()}         
        db.child("/Temperature_Sensor").update(data_temperature,user['idToken'])  
        check_Temp = db.child("/Temperature_Sensor").get(user['idToken']).val()
        check_UI = db.child("Control_Motion_Sensor").get(user['idToken']).val()               
        data_temperature ={"Temperature": measure_temp()}        
        db.child("/Temperature_Sensor").update(data_temperature,user['idToken'])  
        check_Temp = db.child("/Temperature_Sensor").get(user['idToken']).val()
        if (check_Temp["Temperature"] > 28) :
            response = beams_client.publish_to_interests(
            interests=['hello'],
            publish_body={
                'apns': {
                    'aps': {
                        'alert': 'High Temperature!'
                    }
                },
                'fcm': {
                    'notification': {
                        'title': 'High Temperature!',
                        'body': 'Please check and make sure everyone is safe'
                    }
                }
            }
        )
        if check_UI["Status"] == 'True':
            i=GPIO.input(PIN_7)

            if i==0:                
                print ("No Motion",i)
                turnoff()
                time.sleep(1)
            elif i==1:               
                print ("Motion",i)
                turnon()
                counter+=1
                data = {"Motion": counter}  
                db.child("/Motion_Counter").update(data, user['idToken']) 
                response = beams_client.publish_to_interests(                      
                    interests=['hello'],                                           
                    publish_body={                                                  
                        'apns': {                                                   
                            'aps': {                                                
                                'alert': 'Motion Detected!'                                   
                            }                                                       
                        },                                                         
                        'fcm': {                                                     
                            'notification': {                                       
                                'title': 'Motion Detected!',                                    
                                'body': 'Please check and make sure everyone is safe'                           
                            }                                                      
                        }                                                               
                    }                                                              
                )                                                                 
                                         

                time.sleep(5)               
            i=0
        else:
            print(check_UI["Status"]);
                  
    
    
if __name__ == "__main__":
  main()






