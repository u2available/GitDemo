
import Utils.Logs;
import org.testng.annotations.Test;


public class SampleLogTests extends Logs {


    @Test
    public  void TestMethodForLog()
    {

        Logs.fatal("///////////Expected fatal////////////");
        Logs.error("//////////////Expected error////////////");
        Logs.pass("///////////////,,,,,,,, pass////////////");
        Logs.fail("///////////////,,,,,,,, fail////////////");
        Logs.info("//////////////Expected info////////////");
        Logs.warn("//////////////Expected warn////////////");
        Logs.debug("//////////////Expected debug////////////");
    }
}
