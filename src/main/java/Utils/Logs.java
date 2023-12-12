package Utils;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class Logs  {

    public  static Logger log= LogManager.getLogger(Logs.class.getName());
    public static String currentLocation() {
        StackTraceElement classMethod = new Throwable()
                .getStackTrace()[2];
        String   currMethod = classMethod.getMethodName();
        String   fullClass  = classMethod.getClassName();
        String[] smplClass  = fullClass.split("\\.");
        return smplClass[smplClass.length - 1] + "." + currMethod;
    }
    public static void info(String message) {
        log.info("["+currentLocation()+"] - "+message);
    }

    public static void warn(String message) {
        log.warn("["+currentLocation()+"] - "+message);
    }

    public static void error(String message) {
        log.error("["+currentLocation()+"] - "+message);
    }

    public static void fatal(String message) {
        log.fatal("["+currentLocation()+"] - "+message);
    }

    public static void debug(String message) {
        log.debug("["+currentLocation()+"] - "+message);
    }
    public static void pass(String message) {
        log.log(Level.getLevel("PASS"),"["+currentLocation()+"] - "+message);
    }
    public static void fail(String message) {

        log.log(Level.getLevel("FAIL"), "["+currentLocation()+"] - "+message);

    }
}
