#!/usr/bin/python3
import sys
import os
from time import sleep

try:
    import RPi.GPIO as GPIO
except RuntimeError:
    print("Error")

PIN_13=13       
PIN_26=26

GPIO_Out_List=[PIN_13]
GPIO_In_List=[PIN_26]

def turnoff():
    GPIO.output(PIN_13, GPIO.LOW)    
    print ('turn OFF the LED')
    return

def turnon():
    GPIO.output(PIN_13, GPIO.HIGH)    
    print ('turn ON the LED')
    return
   
def main():  
    global PIN_13
    global PIN_26
    global GPIO_Chan_List

    print ('Start LED blinking')    
    GPIO.setmode(GPIO.BCM)    
    GPIO.setwarnings(False)
    
    GPIO.setup(GPIO_Out_List, GPIO.OUT)  #Initialize the led pin as output
    GPIO.setup(GPIO_In_List, GPIO.IN)    #Initializing sensor pin as input


    while True:
        i=GPIO.input(PIN_26)
        if i==0:                 #When output from motion sensor is LOW
            print "No Motion",i
            turnoff()
            sleep(0.5)
        elif i==1:               #When output from motion sensor is HIGH
            print "Motion",i
            turnon()
            sleep(0.5)
        i=0
            

	
if __name__ == "__main__":
  main()




