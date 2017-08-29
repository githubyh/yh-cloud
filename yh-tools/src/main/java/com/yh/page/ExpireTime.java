package com.yh.page;

import java.io.Serializable;

public class ExpireTime implements Serializable {
    private static final long serialVersionUID = 1909605310743183879L;
    private ExpireTime.NXXX nxxx;
    private ExpireTime.EXPX expx;
    private long time;

    public ExpireTime.NXXX getNxxx() {
        return this.nxxx;
    }

    public ExpireTime.EXPX getExpx() {
        return this.expx;
    }

    public long getTime() {
        return this.time;
    }

    public void setNxxx(ExpireTime.NXXX nxxx) {
        this.nxxx = nxxx;
    }

    public void setExpx(ExpireTime.EXPX expx) {
        this.expx = expx;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public ExpireTime() {
        this.nxxx = ExpireTime.NXXX.NX;
        this.expx = ExpireTime.EXPX.EX;
    }

    public ExpireTime(long time) {
        this.nxxx = ExpireTime.NXXX.NX;
        this.expx = ExpireTime.EXPX.EX;
        this.time = time;
    }

    public ExpireTime(ExpireTime.NXXX nxxx, long time) {
        this.nxxx = ExpireTime.NXXX.NX;
        this.expx = ExpireTime.EXPX.EX;
        this.nxxx = nxxx;
        this.time = time;
    }

    public ExpireTime(ExpireTime.NXXX nxxx, ExpireTime.EXPX expx, long time) {
        this.nxxx = ExpireTime.NXXX.NX;
        this.expx = ExpireTime.EXPX.EX;
        this.nxxx = nxxx;
        this.expx = expx;
        this.time = time;
    }

    public String toString() {
        return "ExpireTime [nxxx=" + this.nxxx + ", expx=" + this.expx + ", time=" + this.time + "]";
    }

    public static enum EXPX {
        EX,
        PX;

        private EXPX() {
        }
    }

    public static enum NXXX {
        NX,
        XX;

        private NXXX() {
        }
    }
}