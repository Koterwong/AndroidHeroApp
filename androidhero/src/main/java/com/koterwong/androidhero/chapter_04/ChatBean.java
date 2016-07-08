package com.koterwong.androidhero.chapter_04;

import android.graphics.Bitmap;

/**
 * ================================================
 * Created By：Koterwong; Time: 2016/06/02 07:12
 * <p>
 * Description:
 * =================================================
 */
public class ChatBean {

    private int type;
    private String chatText;
    private Bitmap useShort;

    public ChatBean(int type, String chatText, Bitmap useShort) {
        this.type = type;
        this.chatText = chatText;
        this.useShort = useShort;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getChatText() {
        return chatText;
    }

    public void setChatText(String chatText) {
        this.chatText = chatText;
    }

    public Bitmap getUseShort() {
        return useShort;
    }

    public void setUseShort(Bitmap useShort) {
        this.useShort = useShort;
    }
}
