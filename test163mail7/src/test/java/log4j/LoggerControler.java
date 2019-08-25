package log4j;

import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.Test;

import java.io.*;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * Created by lenovo on 2016/11/9.
 */
public class LoggerControler {
    private static Logger logger=null;
    private static LoggerControler loggCon=null;
    public static LoggerControler getLogger(Class<?> T) {
        if(logger==null){
            Properties props=new Properties();   //瀹炰緥鍖栦竴涓狿roperties绫伙紝澶勭悊log4j.Properties鏂囦欢
            //String proPath=System.getProperty("user.dir")+ File.separator+"config"+File.separator+"log4j.properties";
            String proPath=System.getProperty("user.dir")+ File.separator+"configs"+File.separator+"log4j2.properties";
            
            InputStream inputStream= null;
            try {
                inputStream = new FileInputStream(proPath);
                props.load(inputStream);
                //log4j鐨凱ropertyConfigurator绫荤殑configure鏂规硶杈撳叆鏁版嵁娴�
                PropertyConfigurator.configure(props);
                logger= Logger.getLogger(String.valueOf(T));
                loggCon=new LoggerControler();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return loggCon;
    }
    //閲嶅啓logger鏂规硶
    public void config(String msg) {
        logger.config(msg);
    }
    public void info(String msg){
        logger.info(msg);
    }
    public void warning(String msg) {
        logger.warning(msg);
    }
    public void severe(String msg) {
        logger.severe(msg);
    }

/*    @Test
    public class LoggerTest {
    	   final  LoggerControler log= LoggerControler.getLogger(LoggerTest.class);
    	    @Test
    	    public void testLog(){
    	        log.info("this is info log");
    	        log.warning("this is warn log");
    	        log.severe("this is error log");
    	    }
    }*/
}
