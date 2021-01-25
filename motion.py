#!/usr/bin/python
from gpiozero import MotionSensor
from gpiozero import LED

green_led = LED(26)
red_led = LED(13)
pir = MotionSensor (11) # the data pin
red_led.on()
green_led.off()

while True:
pir.wait_for_motion() #pir.when_motion()
red_led.off()
green_led.on()
print("Motion Detected")

pir.wait_for_no_motion() #pir.when_no_motion()
green_led.off()
print("Motion Stopped")
red_led.on()
#!/usr/bin/python
from gpiozero import MotionSensor
from gpiozero import LED


led = LED(13) # LED pin 
pir = MotionSensor(26) # sensor pin

while True:
    pir.wait_for_motion() # when motion is detected
    led.on()
    print("Motion Detected")

    pir.wait_for_no_motion() # when no motion detected
    led.off()
    print("Motion Stopped")