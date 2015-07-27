#!/usr/bin/env python
import subprocess

class LedDisplay(object):
    def __init__(self):
        self._proc = subprocess.Popen(["/home/pi/display16x32/rpi-rgb-led-matrix/text-example", "-r", "16", 
                                       "-f", "/home/pi/display16x32/rpi-rgb-led-matrix/fonts/4x6.bdf"],
                                      stdin=subprocess.PIPE)

    def clear(self):
        self._proc.stdin.write('\n')

    def display(self, line1, line2=None):
        self._proc.stdin.write('%s\n' % line1)
        if line2:
            self._proc.stdin.write('%s\n' % line2)

    def stop(self):
        self._proc.communicate()

