import org.junit.Test;

import org.apache.log4j.Logger;


/**
 * Created by jony on 3/19/18.
 */
public class Log4jTest {

    static Logger logger = Logger.getLogger(String.valueOf(Log4jTest.class));

    @Test
    public void outputTest(){
        logger.info("logTest .......");
        logger.debug("log4j  debug");
        logger.error("log4j error");
    }
}
