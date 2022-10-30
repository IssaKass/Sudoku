# Sudoku
Sudoku game writtern in Java (100%), and Swing library.

## Program use
Game can be started by running the sudoku.exe file.

1. Start a new game by clicking the Play button.

2. Choose a diffucltiy.

3. To fill in cell, click on it with mouse, or go to it with arrow keys, then press any number from 1 to 9. Press delete to remove the number, or press the same number to remove it.

4. Various colors are used to describe what is happening: highlight common numbers, wrong numbers, input numbers... You can disable it by going to Edit in menu -> Uncheck the highlight.

5. Two themes are used (Dark and Light themes).

6. You can import and export a sudoku (Game->Import, Game->Export)
  The .txt file should be like :
  
  100382090
  084010000
  300470018
  763249000
  005000000
  008600370
  002090001
  801020049
  540103027
  
  Where 0 represents the empty cell
  
  7. You can pause and unpause the game.
  
  8. A timer is shown in the upper right corner to show the time spent.
  
  Notes:
  - Puzzles are always generating using an algorithm.
  - The frame has the 'flatlaf' look and feel.
