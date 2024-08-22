package automation.utility;

import automation.drivers.DriverSingleton;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Utils {
    public static boolean takeScreenShots(){
        File file = ((TakesScreenshot) DriverSingleton.getDriver()).getScreenshotAs(OutputType.FILE);
        try {
            FileCopyUtils.copy(file, new File(ConstantsKey.SCREENSHOT_FOLDER +generateRandomName(ConstantsKey.FILE_LENGTH)+ConstantsKey.PIC_FORMAT));
            return true;
        } catch (IOException e) {
            return false;
        }
    };

    private static String generateRandomName(int length){
        String seedChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();
        int i=0;
        Random random = new Random();
        while (i < length){
            sb.append(seedChars.charAt(random.nextInt(seedChars.length())));
            i++;
        }
        return sb.toString();
    };
}
