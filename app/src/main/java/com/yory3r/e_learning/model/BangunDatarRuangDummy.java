package com.yory3r.e_learning.model;

import java.util.ArrayList;

public class BangunDatarRuangDummy
{
    public ArrayList<BangunDatarRuang> listBangunDatarRuang;
    public BangunDatarRuangDummy()
    {
        listBangunDatarRuang = new ArrayList<>();
        listBangunDatarRuang.add(Persegi);
        listBangunDatarRuang.add(PersegiPanjang);
        listBangunDatarRuang.add(Segitiga);
    }

    public static final BangunDatarRuang Persegi = new BangunDatarRuang("Persegi","Persegi merupakan macam-macam bangun datar yang mempunyai 4 sisi. Keempat sisi dari persegi sama panjang dan keempat sudutnya siku-siku atau 90°. Diagonal persegi juga membagi dua satu sama lain pada 90 °. Sisi-sisi persegi yang berlawanan akan selalu sejajar.","4 × sisi", "sisi × sisi");
    public static final BangunDatarRuang PersegiPanjang = new BangunDatarRuang("Persegi","Persegi panjang merupakan macam-macam bangun datar segiempat yang mempunyai 2 pasang sisi sejajar dan sama panjang serta keempat sudutnya siku-siku. Persegi panjang memiliki dua pasang sisi yang sama. Sisi persegi panjang yang lebih panjang adalah panjangnya dan sisi yang lebih pendek adalah lebarnya. Sisi berlawanan dari persegi panjang juga sejajar.","2 × (panjang + lebar)", "panjang × lebar");
    public static final BangunDatarRuang Segitiga = new BangunDatarRuang("Persegi","Segitiga merupakan macam-macam bangun datar yang memiliki 3 buah sisi dan mempunyai 3 buah titik sudut. Semua sisi dan sudut bisa memiliki ukuran yang berbeda. Segitiga ada tiga, antara lain: segitiga sama sisi, segitiga sama kaki, segitiga sembarang. Sedangkan berdasarkan berdasarkan besar sudutnya segitiga dibedakan menjadi 3 yaitu: segitiga tumpul, segitiga siku-siku dan segitiga lancip.","sisi a + sisi b + sisi c", "½ * alas * tinggi");
}
