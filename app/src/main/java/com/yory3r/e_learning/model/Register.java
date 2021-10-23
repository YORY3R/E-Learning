package com.yory3r.e_learning.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "register")
public class Register
{
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "NamaLengkap")
    private String namaLengkap;

    @ColumnInfo(name = "NPM")
    private String npm;

    @ColumnInfo(name = "NomorTelepon")
    private String nomorTelepon;

    @ColumnInfo(name = "Alamat")
    private String alamat;

    @ColumnInfo(name = "Username")
    private String username;

    @ColumnInfo(name = "Password")
    private String password;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getNamaLengkap()
    {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap)
    {
        this.namaLengkap = namaLengkap;
    }

    public String getNpm()
    {
        return npm;
    }

    public void setNpm(String npm)
    {
        this.npm = npm;
    }

    public String getNomorTelepon()
    {
        return nomorTelepon;
    }

    public void setNomorTelepon(String nomorTelepon)
    {
        this.nomorTelepon = nomorTelepon;
    }

    public String getAlamat()
    {
        return alamat;
    }

    public void setAlamat(String alamat)
    {
        this.alamat = alamat;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
}