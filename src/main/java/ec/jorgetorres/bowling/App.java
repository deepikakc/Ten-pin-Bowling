/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package ec.jorgetorres.bowling;

import ec.jorgetorres.bowling.control.TenPinBowlingGame;

public class App {

  public static void main(String[] args) {
    if (args.length > 0) {
      for (int i = 0; i < args.length; i++) {
        game(args[i], i + 1);
      }
    } else {
      System.out.println("No Text file route for Game has been passed.");
    }
  }

  //public static void game(String path, int gameIndex) { -rightcode
	public static void game(String path, int gamIndex) {
    new TenPinBowlingGame(path, gameIndex).printGame();
  }

}
