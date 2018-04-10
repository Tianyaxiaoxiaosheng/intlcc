package com.jony.intlcc.domain;

import net.sf.json.JSONObject;

/**
 * Created by jony on 2018/4/10.
 */
public class Room {

    private int roomId;
    private int roomType;

    private String rcuIp;
    private int rcuPort;

    public Room() {
    }

    public Room(int roomId, int roomType, String rcuIp, int rcuPort) {
        this.roomId = roomId;
        this.roomType = roomType;
        this.rcuIp = rcuIp;
        this.rcuPort = rcuPort;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getRoomType() {
        return roomType;
    }

    public void setRoomType(int roomType) {
        this.roomType = roomType;
    }

    public String getRcuIp() {
        return rcuIp;
    }

    public void setRcuIp(String rcuIp) {
        this.rcuIp = rcuIp;
    }

    public int getRcuPort() {
        return rcuPort;
    }

    public void setRcuPort(int rcuPort) {
        this.rcuPort = rcuPort;
    }

    public String toJSONString(){
        //concise
//        return JSONObject.fromObject(this).toString();

        //optimization
        String jsonString = null;

        try {
            JSONObject jsonObject = JSONObject.fromObject(this);
            jsonString = jsonObject.toString();
        } catch (Exception e){
            e.printStackTrace();
        }

        return jsonString;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomId: " + roomId +
                ", roomType: " + roomType +
                ", rcuIp: " + rcuIp +
                ", rcuPort: " + rcuPort +
                "}";
    }
}
