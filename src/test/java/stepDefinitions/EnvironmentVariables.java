package stepDefinitions;

public interface EnvironmentVariables {
    //from Jenkins
    String LOGIN_LOGIN = System.getenv("LOGIN_LOGIN");
    String LOGIN_PASSWORD = System.getenv("LOGIN_PASSWORD");
    int TEST_TIMEOUT = Integer.parseInt(System.getenv("TEST_TIMEOUT"));




    //from here
//    private final String envLoginLogin = "sledzik";
//    private final String envLoginPassword = "dieh@rd";
//    private final int envTimeoutIsAt = 60;
}
