package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    public static String str;
    static Properties prop;// static variable to use it for many methods
    public static Properties readProperties (String filePath){

        try {
            FileInputStream fis=new FileInputStream(filePath); //this line takes us to
            //that particular file some time file is not there so it throws exception
        prop=new Properties();// to load that file in to java readable
            prop.load(fis);// this finds the file if file is currupted or has any issue
                            //it throws another exception
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) { //throws input and output exception if file has issues
          e.printStackTrace();
        }
        return prop; //this method will make the file read from java
    }
public static String getPropertyValue(String key){
        //get property is the mathod which will read the value as per the key provided
    return prop.getProperty(key);//this is gonna return one value
}
}
