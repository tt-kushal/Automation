package automation.utility;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FrameworkProperties {
    private String value;
    private InputStream inputStream ;

    public String getProperty(String key ) {
        try {
            Properties properties = new Properties();
            String fileName = ConstantsKey.FILE_NAME;

            inputStream = getClass().getClassLoader().getResourceAsStream(fileName);

            if (inputStream!= null){
                properties.load(inputStream);
            }else{
              throw new FileNotFoundException("file is not found");
            }

            this.value = properties.getProperty(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return value;
    };
}
