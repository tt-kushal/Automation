package automation.drivers;

import automation.utility.ConstantsKey;

public class DriverStrategyImplementors {
    public static DriverStrategy chooseStrategy(String strategy){
        switch (strategy){
            case ConstantsKey.CHROME:
                return new Chrome();
            case ConstantsKey.EDGE:
                return new MicrosoftEdge();
            default:
                return null;
        }
    }
}
