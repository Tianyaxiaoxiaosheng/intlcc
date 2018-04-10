package com.jony.intlcc.domain;

/**
 * Created by jony on 2018/4/2.
 */
public class RegReplyMessage {

    private String user;
    private boolean status;
    private String roomInfo;


    public RegReplyMessage() {
    }

    public RegReplyMessage(String user, boolean status, String roomInfo) {
        this.user = user;
        this.status = status;
        this.roomInfo = roomInfo;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getRoomInfo() {
        return roomInfo;
    }

    public void setRoomInfo(String roomInfo) {
        this.roomInfo = roomInfo;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
