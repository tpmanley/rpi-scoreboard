#!/usr/bin/env python
from flask import Flask, render_template, Response
import leddisplay

app = Flask(__name__)

disp = leddisplay.LedDisplay()

@app.route('/pi/display/text/')
def clear_message():
    print "Clearing display"
    disp.clear()
    return "OK"

@app.route('/pi/display/text/<msg>')
def display_message(msg):
    print "Displaying text %s" % msg
    disp.display(msg)
    return "OK"

if __name__ == '__main__':
    app.run(host='0.0.0.0') #, debug=True)
    disp.stop()

