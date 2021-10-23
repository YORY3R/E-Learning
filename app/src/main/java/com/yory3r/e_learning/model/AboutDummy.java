package com.yory3r.e_learning.model;

import com.yory3r.e_learning.R;

import java.util.ArrayList;

public class AboutDummy
{
    public ArrayList<About> listAbout;
    public AboutDummy()
    {
        listAbout = new ArrayList<>();
        listAbout.add(Yolif);
        listAbout.add(Tegar);
        listAbout.add(Stanley);
    }

    public static final About Yolif = new About(R.drawable.picture_yolif,"Yolif Syebathanim",190710072,"081542701211");
    public static final About Tegar = new About(R.drawable.picture_tegar,"Geraldo Tegar Sanchaka", 190710076, "08113336101");
    public static final About Stanley = new About(R.drawable.picture_stanley,"Antonius Stanley Waskita", 190710100, "081390466525");
}
