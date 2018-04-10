import com.jony.intlcc.dao.impl.IRoomDaoImpl;
import com.jony.intlcc.domain.Room;
import com.jony.intlcc.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

/**
 * Created by jony on 2018/4/10.
 */
public class RoomTest {

    SqlSession sqlSession;

    @Test
    public void queryById() {

        sqlSession = MybatisUtil.getSqlSession();
        String roomNum = "1205";
        try {
            Room result = sqlSession.selectOne("selectByRoomNum", roomNum);

            sqlSession.commit();
            System.out.println("result:" + result.toString());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MybatisUtil.closeSession(sqlSession);
        }

    }

    @Test
    public void roomDaoFunction(){

        String roomNum = "1201";

        Room room = new IRoomDaoImpl().queryRoomByRoomNum(roomNum);

        System.out.println(room.toString());
    }

}
