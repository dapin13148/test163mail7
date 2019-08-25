package log4j;



import org.apache.log4j.Logger;
import org.testng.annotations.Test;


/**
 * Created by lenovo on 2016/11/9.
 */
public class LoggerTest {
	//   final static LoggerControler log= getLogger(LoggerTest.class);
   final static LoggerControler log= LoggerControler.getLogger(LoggerTest.class);
    @Test
    public void testLog(){
        log.info("this is info log");
        log.warning("this is warn log");
        log.severe("this is error log");
    }

}
