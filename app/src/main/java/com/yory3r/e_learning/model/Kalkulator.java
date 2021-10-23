package com.yory3r.e_learning.model;

public class Kalkulator
{
    private float angka;
    private int action;

    public Kalkulator(float angka, int action)
    {
        this.angka = angka;
        this.action = action;
    }

    public float getAngka()
    {
        return angka;
    }

    public void setAngka(float angka)
    {
        this.angka = angka;
    }

    public int getAction()
    {
        return action;
    }

    public void setAction(int action)
    {
        this.action = action;
    }
}
