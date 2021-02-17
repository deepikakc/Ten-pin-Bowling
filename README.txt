##### Ten-pin-Bowling App
Jorge Torres: jorgetorresr6@outlook.com, jorgevtorresr@gmail.com, jorgevtorresr@outlook.com

##### Installation
Use Gradle to compile an executable jar

Linux & Mac
./gradlew clean build

Windows
gradlew.bat clean build

##### Usage
Search and execute the jar in the build folder (current version 0.1-SNAPSHOT), make sure to add at least one path to a game file as an argument or as many paths as you want

java -jar ./build/libs/Ten-pin-Bowling-0.1-SNAPSHOT.jar Ten-pin-Sample

##### Test Execution
Please test the Ten-pin-Bowling App with the following tests files

Integration Tests
./gradlew :test --tests "ec.jorgetorres.bowling.ITestTenPinBowling"

Unit Tests
./gradlew :test --tests "ec.jorgetorres.bowling.TenPinBowlingTestCase"

## Test by Deepika KC