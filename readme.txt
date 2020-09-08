web test exercises (Jenkins with github, env. variables, docker, Jenkinsfile - pipeline, Maven with Cucumber report)
https://youtu.be/stX4SSzUQDA
env. variables (url, login, pass) directly in Jenkins job

launch by Maven: pom.xml
launch by runner (Unit test): RunTest.java

important files:
POM.xml: project_dir/pom.xml
feature file: project_dir/src/test/resources/features/TestMainTasks.feature
test runner: project_dir/src/test/java/runner/RunTest.java
Step definitions: project_dir/src/test/java/stepDefinitions/Steps.java
pages: project_dir/src/test/java/pages
FF/chrome configuration: project_dir/src/test/java/utilities/DriverManager.java

screenshots e.g. on failed or passed  in /screenShoots