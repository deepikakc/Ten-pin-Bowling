package ec.jorgetorres.bowling.entities;

public class Roll {
  private int numberOfPinFall;
  private String fault;

  public Roll(int numberOfPinFall){
    this.numberOfPinFall = numberOfPinFall;
  }

  public Roll(String fault) {
    this.setFault(fault);
  }

  public void setFault(String fault) {
    this.fault = fault;
  }

  public int getScore() {
    if (this.fault == null) {
      return numberOfPinFall;
    } else {
      return 0;
    }
  }

  public boolean isFault() {
    return (this.fault != null);
  }

}
