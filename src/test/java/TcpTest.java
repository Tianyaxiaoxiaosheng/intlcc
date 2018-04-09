import com.jony.intlcc.util.ClientTcpUtil;
import org.junit.Test;

/**
 * Created by jony on 3/26/18.
 */
public class TcpTest {

    private static final int LOCALPORT = 7777;
//    private static final String SERVERHOST = "47.97.192.24";
    private static final String SERVERHOST = "192.168.1.107";

    @Test
    public void tcpTest(){

        ClientTcpUtil sharedClientTcpUtil = ClientTcpUtil.getInstance();

        boolean isSuccess = sharedClientTcpUtil.createClientTcp(SERVERHOST, LOCALPORT);

        System.out.println("Created client tcp "+isSuccess);

//        while (true){
//            try {
//                Thread.sleep(3000);
//                sharedClientTcpUtil.sendByTcp("我是内网tcp Client 在Test");
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

//        sharedClientTcpUtil.sendByTcp("我是内网tcp Client 在Test");
    }
}
