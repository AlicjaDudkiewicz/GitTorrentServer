package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Alicja on 2016-12-28.
 */
public class FileInstance
{
    private String name;
    private String md5;
    private long size;
    public FileInstance(File file)
    {
        this.name=file.getName();
        this.md5=getMD5(file);
        this.size=file.length();

    }

    @Override
    public String toString() {
        return "model.entities.FileInstance{" +
                "name='" + name + '\'' +
                ", md5='" + md5 + '\'' +
                ", size=" + size +
                '}';
    }

    private  String getMD5(File file)
    {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        byte[] byteArray = new byte[4096];
        int counter = 0;


        try {
            while ((counter = fileInputStream.read(byteArray)) != -1)
            {
                messageDigest.update(byteArray, 0, counter);
            }
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }



        byte[] bytes = messageDigest.digest();

        StringBuilder sb = new StringBuilder();
        for(int i=0; i< bytes.length ;i++)
        {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }

    public String getName() {
        return name;
    }

    public String getMd5() {
        return md5;
    }

    public long getSize() {
        return size;
    }
}
