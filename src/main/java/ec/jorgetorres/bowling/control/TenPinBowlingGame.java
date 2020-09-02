package ec.jorgetorres.bowling.control;

import ec.jorgetorres.bowling.domain.PoolofPlayers;
import ec.jorgetorres.bowling.exceptions.BadLineFormatException;
import ec.jorgetorres.bowling.exceptions.ExceededPinFallException;
import ec.jorgetorres.bowling.exceptions.NegativePinFallException;
import ec.jorgetorres.bowling.exceptions.NotAllowedPinFallException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TenPinBowlingGame {
  private PoolofPlayers poolofPlayers = new PoolofPlayers();
  private int gameIndex;

  public TenPinBowlingGame(String path, int gameIndex) {
    this.gameIndex = gameIndex;
    try {
      File file = new File(path);
      Scanner sc = new Scanner(file);
      while (sc.hasNextLine()) {
        parseLine(sc.nextLine());
      }
    } catch (ExceededPinFallException e) {
      if (e.getPlayerName() != null) {
        System.out.println(String.format("====== Game Number %d ended, Incorrect number of pins thrown ('%s', %d) for Game.",
            gameIndex, e.getPlayerName(), e.getNumPinFall()));
      } else {
        System.out.println(String.format("====== Game Number %d ended, Incorrect number of pins thrown for Game.",
            gameIndex));
      }
    } catch (NotAllowedPinFallException e) {
      System.out.println(String.format("====== Game Number %d ended, There has been not allowed or exceeding pinfall for Game.",
          gameIndex));
    } catch (NegativePinFallException e) {
      System.out.println(String.format("====== Game Number %d ended, There has been negative pinfall ('%s', %d) for Game.",
          gameIndex, e.getPlayerName(), e.getNumPinFall()));
    } catch (BadLineFormatException e) {
      System.out.println(String.format("====== Game Number %d ended, Incorrect Game Text Line Formating for Game.",
          gameIndex));
    } catch (NumberFormatException e) {
      System.out.println(String.format("====== Game Number %d ended, Incorrect Game Text Line Formating (%s) for Game.",
          gameIndex, e.getMessage()));
    } catch (FileNotFoundException e) {
      System.out.println(String.format("====== No file has been found for Game Number %d.\n", gameIndex));
    }
  }

  private void parseLine(String line) throws ExceededPinFallException,
      NotAllowedPinFallException, NumberFormatException, NegativePinFallException, BadLineFormatException {
    String[] splitedLines = line.split(" ");
    if (splitedLines.length == 2) {
      String name = splitedLines[0];
      if (splitedLines[1].equals("F")) {
        poolofPlayers.addFault(name, splitedLines[1]);
      } else {
        int numberOfPinFall = Integer.parseInt(splitedLines[1]);
        if (numberOfPinFall < 0)
          throw new NegativePinFallException(name, numberOfPinFall);
        poolofPlayers.addPinFall(name, numberOfPinFall);
      }
    } else {
      throw new BadLineFormatException();
    }
  }

  public void printGame() {
    this.poolofPlayers.printGame(gameIndex);
  }

  public PoolofPlayers getPoolofPlayers() {
    return poolofPlayers;
  }
}
