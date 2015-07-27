import requests
import time

import leddisplay

disp = leddisplay.LedDisplay()

def get_game_info(game):
    return requests.get('http://us.sensenet.nu/?lead=%s' % game).json()

def main():
   while True:
      game_info = get_game_info('simon')
      disp.display('Team: %s' % game_info['team'], 'Score: %s' % game_info['score'])
      time.sleep(5)

if __name__ == "__main__":
    main()

