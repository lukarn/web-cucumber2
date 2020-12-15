package utilities;

import java.security.InvalidParameterException;

public class MyWebDriver {

        public void driverConf(DriverConfiguration configuration) {
            if (configuration == null) {
                throw new InvalidParameterException("Can not set driver configuration");
            }

            configuration.setConfiguration();
        }

    //public class Calculator {
    //
    //    public void calculate(CalculatorOperation operation) {
    //        if (operation == null) {
    //            throw new InvalidParameterException("Can not perform operation");
    //        }
    //
    //        operation.perform();
    //    }
    //}
}
