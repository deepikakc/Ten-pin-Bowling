# Ten-pin-Bowling App
Jorge Torres: jorgetorresr6@outlook.com, jorgevtorresr@gmail.com, jorgevtorresr@outlook.com

## Installation
Use Gradle to compile an executable jar

Linux & Mac
```bash
./gradlew clean build
```
Windows
```bash
gradlew.bat clean build
```

## Usage
Search and execute the jar in the build folder (current version 0.1-SNAPSHOT), make sure to add at least one path to a game file as an argument or as many paths as you want

```bash
java -jar ./build/libs/Ten-pin-Bowling-0.1-SNAPSHOT.jar Ten-pin-Sample
```

## Test Execution
Please test the Ten-pin-Bowling App with the following tests files

Integration Tests
```bash
./gradlew :test --tests "ec.jorgetorres.bowling.ITestTenPinBowling"
```

Unit Tests
```bash
./gradlew :test --tests "ec.jorgetorres.bowling.TenPinBowlingTestCase"
```
