package com.yory3r.e_learning.model;

public class LordsMobile
{
    String namaLM;
    String urlLM;

    public LordsMobile(String namaLM, String urlLm)
    {
        this.namaLM = namaLM;
        this.urlLM = urlLm;
    }

    public String getNamaLM()
    {
        return namaLM;
    }

    public void setNamaLM(String namaLM)
    {
        this.namaLM = namaLM;
    }

    public String getUrlLM()
    {
        return urlLM;
    }

    public void setUrlLM(String urlLM)
    {
        this.urlLM = urlLM;
    }
}
