package ec.jorgetorres.bowling.entities;

import java.util.*;

public class Player {
  private String name;
  private int frame = 0;
  private int score = 0;

  private TenthFrame tenthFrame;
  private NormalFrame[] normalFrames = new NormalFrame[9];

  private List<Roll> rolls = new ArrayList<>();

  public Player(String name, int numberOfPinFall) {
    this.name = name;
    Roll roll = new Roll(numberOfPinFall);
    this.normalFrames[frame] = new NormalFrame(roll);

    if (numberOfPinFall == 10) {
      frame++;
    }
    this.rolls.add(roll);
  }

  public Player(String name, String fault) {
    this.name = name;
    Roll roll = new Roll(fault);
    this.normalFrames[frame] = new NormalFrame(roll);
    this.rolls.add(roll);
  }

  public void addResult(int numberofPinFall) {
    Roll roll = new Roll(numberofPinFall);
    this.rolls.add(roll);
    if (frame < 9) {
      if (normalFrames[frame] == null) {
        normalFrames[frame] = new NormalFrame(roll);
        if (numberofPinFall == 10) {
          frame++;
        }
      } else if (normalFrames[frame].firstRollTaken()) {
        normalFrames[frame].setLatestRoll(roll);
        frame++;
      }
    } else {
      setTenthFrame(roll);
    }
  }

  public void addResult(String fault) {
    Roll roll = new Roll(fault);
    this.rolls.add(roll);
    if (frame < 9) {
      if (normalFrames[frame] == null) {
        normalFrames[frame] = new NormalFrame(roll);
      } else if (normalFrames[frame].firstRollTaken()) {
        normalFrames[frame].setLatestRoll(roll);
        frame++;
      }
    } else {
      setTenthFrame(roll);
    }
  }

  private void setTenthFrame(Roll roll) {
    if (tenthFrame == null) {
      tenthFrame = new TenthFrame(roll);
    } else if (tenthFrame.firstRollTaken()) {
      if (tenthFrame.secondRollTaken()) {
        if (tenthFrame.getPartialScore() >= 10) {
          tenthFrame.setThirdRoll(roll);
        } else {
          return;
        }
      } else {
        tenthFrame.setSecondRoll(roll);
      }
    }
  }

  public void calculateScore() {
    for (int i = 0; i < normalFrames.length; i++) {
      if (normalFrames[i] != null
          && !normalFrames[i].isCalculated()
          && (i == 0 || normalFrames[i-1].isCalculated())) {
        if (normalFrames[i].isStrike()) {
          final Roll firstRoll = normalFrames[i].getFirstRoll();
          int pos = rolls.indexOf(firstRoll);
          if (rolls.size() > pos + 2) {
            int bonusScoreOne = rolls.get(pos + 1).getScore();
            int bonusScoreTwo = rolls.get(pos + 2).getScore();
            int currentScore = normalFrames[i].getFirstRoll().getScore();
            int totalScore = this.score + currentScore + bonusScoreOne + bonusScoreTwo;
            normalFrames[i].setScore(totalScore);
            this.score = totalScore;
            normalFrames[i].setCalculated(true);
          }
        } else {
          final Roll firstRoll = normalFrames[i].getFirstRoll();
          final Roll secondRoll = normalFrames[i].getSecondRoll();
          int posFirstRoll = rolls.indexOf(firstRoll);
          if (normalFrames[i].isSpare()) {
            if (rolls.size() > posFirstRoll + 2) {
              int bonusScoreOne = rolls.get(posFirstRoll + 2).getScore();
              int currentFirstScore = firstRoll.getScore();
              int currentSecondScore = secondRoll.getScore();
              int totalScore = this.score + currentFirstScore + currentSecondScore + bonusScoreOne;
              normalFrames[i].setScore(totalScore);
              this.score = totalScore;
              normalFrames[i].setCalculated(true);
            }
          } else if (secondRoll != null) {
            int currentFirstScore = firstRoll.getScore();
            int currentSecondScore = secondRoll.getScore();
            int totalScore = this.score + currentFirstScore + currentSecondScore;
            normalFrames[i].setScore(totalScore);
            this.score = totalScore;
            normalFrames[i].setCalculated(true);
          }
        }
      }
    }
    if (tenthFrame != null) {
      if (tenthFrame.firstRollTaken() && !tenthFrame.isFirstCalculated()) {
        int totalScore = this.score + tenthFrame.getFirstRoll().getScore();
        tenthFrame.setScore(totalScore);
        this.score = totalScore;
        tenthFrame.setFirstCalculated(true);
      } else if (tenthFrame.secondRollTaken() && !tenthFrame.isSecondCalculated()){
        int totalScore = this.score + tenthFrame.getSecondRoll().getScore();
        tenthFrame.setScore(totalScore);
        this.score = totalScore;
        tenthFrame.setSecondCalculated(true);
      } else if (!tenthFrame.isThirdCalculated()) {
        int totalScore = this.score + tenthFrame.getThirdRoll().getScore();
        tenthFrame.setScore(totalScore);
        this.score = totalScore;
        tenthFrame.setThirdCalculated(true);
      }
    }
  }

  public void printPinFalls() {
    for (int i = 0; i < 9; i++) {
      if (normalFrames[i].isStrike()){
        System.out.print("\t\tX");
      } else {
        if (normalFrames[i].getFirstRoll().isFault()) {
          System.out.print("\tF");
        } else {
          System.out.print("\t"+normalFrames[i].getFirstRoll().getScore());
        }
        if (normalFrames[i].isSpare()) {
          System.out.print("\t/");
        } else if (normalFrames[i].getSecondRoll().isFault()) {
          System.out.print("\tF");
        } else {
          System.out.print("\t"+normalFrames[i].getSecondRoll().getScore());
        }
      }
    }
    if (tenthFrame != null) {
      if (tenthFrame.firstRollTaken()) {
        if (tenthFrame.getFirstRoll().getScore() == 10) {
          System.out.print("\tX");
        } else if (tenthFrame.getFirstRoll().isFault()){
          System.out.print("\tF");
        } else {
          System.out.print("\t"+tenthFrame.getFirstRoll().getScore());
        }
      }
      if (tenthFrame.secondRollTaken()) {
        if (tenthFrame.getPartialScore() == 10) {
          System.out.print("\t/");
        } else if (tenthFrame.getSecondRoll().getScore() == 10) {
          System.out.print("\tX");
        } else if (tenthFrame.getSecondRoll().isFault()){
          System.out.print("\tF");
        } else {
          System.out.print("\t"+tenthFrame.getSecondRoll().getScore());
        }
      }
      if (tenthFrame.thirdRollTaken()) {
        if (tenthFrame.getSecondPartialScore() == 10) {
          System.out.print("\t/");
        } else if (tenthFrame.getThirdRoll().getScore() == 10) {
          System.out.print("\tX");
        } else if (tenthFrame.getThirdRoll().isFault()) {
          System.out.print("\tF");
        } else {
          System.out.print("\t"+tenthFrame.getThirdRoll().getScore());
        }
      }
    } else {
      System.out.println();
    }
  }

  public void printScore() {
    for (int i = 0; i < 9; i++)
      System.out.print("\t\t"+normalFrames[i].getScore());
    if (tenthFrame != null)
      System.out.print("\t\t"+tenthFrame.getScore());
  }

  public String getName() {
    return name;
  }
}
