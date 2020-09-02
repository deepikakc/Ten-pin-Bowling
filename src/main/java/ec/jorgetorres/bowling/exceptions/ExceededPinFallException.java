package ec.jorgetorres.bowling.exceptions;

public class ExceededPinFallException extends Exception{
  private String playerName;
  private int numPinFall;
  public ExceededPinFallException(String playerName, int numPinFall) {
    this.playerName = playerName;
    this.numPinFall = numPinFall;
  }

  public ExceededPinFallException() {}

  public String getPlayerName() {
    return playerName;
  }

  public void setPlayerName(String playerName) {
    this.playerName = playerName;
  }

  public int getNumPinFall() {
    return numPinFall;
  }

  public void setNumPinFall(int numPinFall) {
    this.numPinFall = numPinFall;
  }
}
