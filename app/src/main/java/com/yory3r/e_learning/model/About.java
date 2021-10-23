package com.yory3r.e_learning.model;

public class About
{
    int fotoAbout;
    String namaAbout;
    int npmAbout;
    String nomorTelpAbout;

    public About(int fotoAbout, String namaAbout, int npmAbout, String nomorTelpAbout)
    {
        this.fotoAbout = fotoAbout;
        this.namaAbout = namaAbout;
        this.npmAbout = npmAbout;
        this.nomorTelpAbout = nomorTelpAbout;
    }

    public int getFotoAbout()
    {
        return fotoAbout;
    }

    public void setFotoAbout(int fotoAbout)
    {
        this.fotoAbout = fotoAbout;
    }

    public String getNamaAbout()
    {
        return namaAbout;
    }

    public void setNamaAbout(String namaAbout)
    {
        this.namaAbout = namaAbout;
    }

    public int getNPMAbout()
    {
        return npmAbout;
    }

    public void setNPMAbout(int npmAbout)
    {
        this.npmAbout = npmAbout;
    }

    public String getNomorTelpAbout()
    {
        return nomorTelpAbout;
    }

    public void setNomorTelpAbout(String nomorTelpAbout)
    {
        this.nomorTelpAbout = nomorTelpAbout;
    }
}