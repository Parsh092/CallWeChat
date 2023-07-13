package com.parsh.callwechat.Models;

public class Message {
  String uid,message,messageId;
  Long timestamp;
int feeling;

    public String getMessageId() {
        return messageId;
    }
    public Message(String message,String uid,  Long timestamp) {
        this.uid = uid;
        this.message = message;
        this.timestamp = timestamp;
    }
    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public int getFeeling() {
        return feeling;
    }

    public void setFeeling(int feeling) {
        this.feeling = feeling;
    }



    public Message(String uid, String message) {
        this.uid = uid;
        this.message = message;
    }

    public Message() {

    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}