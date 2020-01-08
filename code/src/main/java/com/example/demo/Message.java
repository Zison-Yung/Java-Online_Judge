package com.example.demo;

import java.util.Date;

public class Message {

    private String err;
    private String result;
    private Date date;
    private String compare;
    private long time;


    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getCompare() {
        return compare;
    }

    public void setCompare(String compare) {
        this.compare = compare;
    }

    public String getErr() {
        return err;
    }

    public void setErr(String err) {
        this.err = err;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Message{" +
                "err='" + err + '\'' +
                ", result='" + result + '\'' +
                ", date=" + date +
                ", compare='" + compare + '\'' +
                ", time=" + time +
                '}';
    }
}
