package ec.jorgetorres.bowling.exceptions;

public class NegativePinFallException extends Exception {
  private String playerName;
  private int numPinFall;
  public NegativePinFallException(String playerName, int numPinFall) {
    this.setPlayerName(playerName);
    this.setNumPinFall(numPinFall);
  }

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
