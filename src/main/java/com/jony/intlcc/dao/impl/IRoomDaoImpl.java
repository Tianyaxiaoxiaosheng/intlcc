package com.jony.intlcc.dao.impl;

import com.jony.intlcc.dao.IRoomDao;
import com.jony.intlcc.domain.Room;
import com.jony.intlcc.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

/**
 * Created by jony on 2018/4/10.
 */
public class IRoomDaoImpl implements IRoomDao{


    /**
     * Check the room information according to room number
     * @param roomNum
     * @return Room Object
     */
    public Room queryRoomByRoomNum(String roomNum) {


        SqlSession sqlSession = MybatisUtil.getSqlSession();
        Room room = null;

        try {

            room = sqlSession.selectOne("selectByRoomNum", roomNum);
            sqlSession.commit();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            MybatisUtil.closeSession(sqlSession);
        }

        return room;
    }
}
