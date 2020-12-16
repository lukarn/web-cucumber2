package stepDefinitions;

public interface EnvironmentVariables {
    //from Jenkins
    String LOGIN_LOGIN = getEnvironmentVariableString("LOGIN_LOGIN", "sledzik"); //System.getenv("LOGIN_LOGIN");
    String LOGIN_PASSWORD = getEnvironmentVariableString("LOGIN_PASSWORD", "dieh@rd"); //System.getenv("LOGIN_PASSWORD");
    int TEST_TIMEOUT = getEnvironmentVariableInt("TEST_TIMEOUT", 60); //Integer.parseInt(System.getenv("TEST_TIMEOUT"));

    static String getEnvironmentVariableString(String varName, String defaultValue){


        try {
            if(System.getenv(varName) == null){
                printVariable("DEFAULT", varName, defaultValue);
                return defaultValue;
            }else{
                printVariable("JENKINS", varName, System.getenv(varName));
                return System.getenv(varName);
            }
        }catch (Exception e){
            printVariable("DEFAULT", varName, defaultValue);
            return defaultValue;
        }

    }

    static int getEnvironmentVariableInt(String varName, int defaultValue){


        try {
            if(Integer.parseInt(System.getenv(varName)) > 0){
                printVariable("JENKINS", varName, System.getenv(varName));
                return Integer.parseInt(System.getenv(varName));
            }else{
                printVariable("DEFAULT", varName, Integer.toString(defaultValue));
                return defaultValue;
            }
        }catch (Exception e){
            printVariable("DEFAULT", varName, Integer.toString(defaultValue));
            return defaultValue;
        }

    }

    static void printVariable(String label, String varName, String varValue){
        System.out.println("==" + label + "== >>>>>>>>>>>>>>>>>> " + varName + "=" + varValue);
    }






}
