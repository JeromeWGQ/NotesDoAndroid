package com.example.jerome.myapplication.util;


/**
 * Created by alan on 2016/8/26.
 */
public class Contract {
    private byte[] img;
    private int id;
    private String userName;
    private String content;

    public byte[] getImg()
    {
        return img;
    }
    public void setImg(byte[] img)
    {
        this.img=img;
    }
    public int getId()
    {
        return id;
    }
    public void setId(int id)
    {
        this.id=id;
    }
    public String getUserName()
    {
        return userName;
    }
    public void setUserName(String userName)
    {
        this.userName=userName;
    }
    public String getContent()
    {
        return content;
    }
    public void setContent(String content)
    {
        this.content=content;
    }

}
