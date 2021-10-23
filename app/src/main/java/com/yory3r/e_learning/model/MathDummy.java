package com.yory3r.e_learning.model;

import com.yory3r.e_learning.R;

import java.util.ArrayList;

public class MathDummy
{
    public ArrayList<Math> listMath;
    public MathDummy()
    {
        listMath = new ArrayList<>();
        listMath.add(Kalkulator);
        listMath.add(BangunDatarRuang);
        listMath.add(UrutinBilangan);
        listMath.add(temp);
    }

    public static final Math Kalkulator = new Math("Kalkulator");
    public static final Math BangunDatarRuang = new Math("Bangun Datar + Ruang");
    public static final Math UrutinBilangan = new Math("Mengurutkan Bilangan");
    public static final Math temp = new Math("On Progress !");
}
