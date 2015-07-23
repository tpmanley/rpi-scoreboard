#!/usr/bin/env python

import sys
import subprocess
import requests

TARGET_URL = "localhost"

def display_score(score):
    subprocess.call(["text-example", "-r", "16", 
                     "-x", "8", "-y", "-5", 
                     "-f", "fonts/8x13.bdf",
                     score])


def main():
    while True:
        r = requests.get(TARGET_URL)
        display_score(r.json()['score'])

if __name__ == "__main__":
    main()
    sys.exit(0)

