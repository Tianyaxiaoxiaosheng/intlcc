package com.jony.intlcc.dao;

import com.jony.intlcc.domain.Room;

/**
 * Created by jony on 2018/4/10.
 */
public interface IRoomDao {

    Room queryRoomByRoomNum(String roomNum);
}
