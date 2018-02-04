package dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class UsersDaoFactory {
    private static final String PATH_TO_PROPERTIES = "D:/MyJava/myWebProjects/simple_web_project/src/main/webapp/config.properties";

    public static UsersDAO getUsersDAO(){
        FileInputStream fileInputStream;
        Properties property = new Properties();
        System.out.println(new File(".").getAbsolutePath());
        try{
          fileInputStream = new FileInputStream(PATH_TO_PROPERTIES);

            property.load(fileInputStream);
        } catch (IOException e){
            System.err.println("file " + PATH_TO_PROPERTIES + " does not exist");
        }

        if(property.getProperty("dao").equals("hibernate")){
            return new UsersDaoHibernateImpl();
        } else {
            return new UsersDaoJdbcImpl();
        }
    }
}
