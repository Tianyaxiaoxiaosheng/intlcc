import com.jony.intlcc.util.InUdpMPThread;
import org.junit.Test;

/**
 * Created by jony on 2018/4/11.
 */
public class UdpTest {

    int count = 0;

    @Test
    public void udpSendTest(){
        new InUdpMPThread("state message : "+(count++)).start();
    }

    /**
     * 结果无反应，需要多线程测试
     */

}
