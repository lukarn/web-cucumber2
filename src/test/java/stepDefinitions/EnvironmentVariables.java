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
        System.out.println("==" + label + "==" + varName + "=" + varValue);
    }


//    //  "yyyy-MM-dd HH-mm-ss"
//    default String getDateOrTime(String patternSimpleDateFormat, int daysToAdd){
//        String dt = new SimpleDateFormat(patternSimpleDateFormat).format(Calendar.getInstance().getTime());
//
//        SimpleDateFormat sdf = new SimpleDateFormat(patternSimpleDateFormat);
//        Calendar c = Calendar.getInstance();
//
//        try {
//            c.setTime(sdf.parse(dt));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        c.add(Calendar.DATE, daysToAdd);  // number of days to add
//        dt = sdf.format(c.getTime());  // dt is now the new date
//
//        return dt;
//
//    }
//
//    //  System.out.println(saveScreenShot((TakesScreenshot) driver, getDateOrTime("yyyy-MM-dd HH-mm-ss", 0)));
//    default String saveScreenShot(TakesScreenshot ts, String currentTime){
//
//
//        String filePath = null;
//
//        if(ts != null) {
//            File srcFile = ts.getScreenshotAs(OutputType.FILE);
//
//            try {
//                //ScreenShot
//                filePath = System.getProperty("user.dir") + "/screenShots/OK" + currentTime + ".png";
//                FileUtils.copyFile(srcFile, new File(filePath));
//                System.out.println("Saving screenshot on request... " + filePath + " [OK]");
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        else
//        {
//            System.out.println("No driver - no photo. Saving screenshot [NOK]");
//            filePath = "No file = no file path [NOK]";
//        }
//
//        return filePath;
//
//    }



}
