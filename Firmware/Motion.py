#!/usr/bin/python3
import sys
import os
from pyrebase import pyrebase
import time


#SenseAlert database config                
config = {
  "apiKey": "APIKEY",
  "authDomain": "yourProjectID.fdirebaseapp.com",
  "databaseURL": "https://yourProjectID-default-rtdb.firebaseio.com/",
  "storageBucket": "yourProjectID.appspot.com"
}

firebase = pyrebase.initialize_app(config)

auth = firebase.auth()   

email = "your_email"           
password ="your_password"                         
user = auth.sign_in_with_email_and_password(email, password)    

db = firebase.database()


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
        temp = os.popen("vcgencmd measure_temp").readline()        
        return (temp.replace("temp=",""))                       
    
def main():  
    global PIN_13
    global PIN_7
    global GPIO_Chan_List
    global counter
    #global total_count
    
    data = {"Motion": counter}
    data_temprature ={"Temperature": measure_temp()}             
    
    print ('Start LED blinking')    
    GPIO.setmode(GPIO.BCM)    
    GPIO.setwarnings(False)
    
    GPIO.setup(GPIO_Out_List, GPIO.OUT)  #Initialize the led pin as output
    GPIO.setup(GPIO_In_List, GPIO.IN)    #Initializing sensor pin as input


    while True:
        data_temperature ={"Temperature": measure_temp()}         
        db.child("/Temperature_Sensor").update(data_temperature,user['idToken'])  
        check_UI = db.child("Control_Motion_Sensor").get(user['idToken']).val()        
        if check_UI["Status"] == 'True':
            i=GPIO.input(PIN_7)

            if i==0:                 #When output from motion sensor is LOW
                print ("No Motion",i)
                turnoff()
                time.sleep(1)
            elif i==1:               #When output from motion sensor is HIGH
                print ("Motion",i)
                turnon()
                counter+=1
                data = {"Motion": counter}   
                db.child("/Motion_Counter").update(data, user['idToken'])  
                                                
                                               
                                               
                time.sleep(5)                
            i=0
        else:
            print(check_UI["Status"]);
                  
    
    
if __name__ == "__main__":
  main()





