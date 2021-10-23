package com.yory3r.e_learning.fragment;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.yory3r.e_learning.R;
import com.yory3r.e_learning.databinding.FragmentBangunDatarRuangSegitigaBinding;
import com.yory3r.e_learning.model.BangunDatarRuang;
import com.yory3r.e_learning.model.BangunDatarRuangDummy;

public class BangunDatarRuangFragmentSegitiga extends Fragment
{
    private String title;
    private int page;
    private FragmentBangunDatarRuangSegitigaBinding fragmentBangunDatarRuangSegitigaBinding;

    public static BangunDatarRuangFragmentSegitiga newInstance(int page, String title)
    {
        BangunDatarRuangFragmentSegitiga bangunDatarRuangFragmentSegitiga = new BangunDatarRuangFragmentSegitiga();
        Bundle bundle = new Bundle();
        bundle.putInt("Page",page);
        bundle.putString("Title",title);
        bangunDatarRuangFragmentSegitiga.setArguments(bundle);

        return bangunDatarRuangFragmentSegitiga;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("Page",0);
        title = getArguments().getString("Title");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        fragmentBangunDatarRuangSegitigaBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_bangun_datar_ruang_segitiga,container,false);
        fragmentBangunDatarRuangSegitigaBinding.setFragmentBangunDatarRuangSegitiga(this);

        View view = fragmentBangunDatarRuangSegitigaBinding.getRoot();

        BangunDatarRuang bangunDatarRuang = new BangunDatarRuangDummy().listBangunDatarRuang.get(2);

        fragmentBangunDatarRuangSegitigaBinding.tvDeskripsiSegitiga.setText(bangunDatarRuang.getDeskripsiBangun());
        fragmentBangunDatarRuangSegitigaBinding.tvKelilingSegitiga.setText(bangunDatarRuang.getKelilingBangun());
        fragmentBangunDatarRuangSegitigaBinding.tvLuasSegitiga.setText(bangunDatarRuang.getLuasBangun());

        fragmentBangunDatarRuangSegitigaBinding.etKelilingSisiASegitiga.addTextChangedListener(new TextWatcher()
        {
            float sisiA;
            float sisiB;
            float sisiC;
            float hasil;

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2){}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                if(fragmentBangunDatarRuangSegitigaBinding.etKelilingSisiASegitiga.getText().toString().isEmpty() || fragmentBangunDatarRuangSegitigaBinding.etKelilingSisiBSegitiga.getText().toString().isEmpty() || fragmentBangunDatarRuangSegitigaBinding.etKelilingSisiCSegitiga.getText().toString().isEmpty())
                {
                    sisiA = 0;
                    sisiB = 0;
                    sisiC = 0;
                }
                else
                {
                    sisiA = Float.parseFloat(fragmentBangunDatarRuangSegitigaBinding.etKelilingSisiASegitiga.getText().toString());
                    sisiB = Float.parseFloat(fragmentBangunDatarRuangSegitigaBinding.etKelilingSisiBSegitiga.getText().toString());
                    sisiC = Float.parseFloat(fragmentBangunDatarRuangSegitigaBinding.etKelilingSisiCSegitiga.getText().toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable)
            {
                hasil = sisiA + sisiB + sisiC;

                fragmentBangunDatarRuangSegitigaBinding.tvHasilKelilingSegitiga.setText(String.valueOf(hasil));
            }
        });

        fragmentBangunDatarRuangSegitigaBinding.etKelilingSisiBSegitiga.addTextChangedListener(new TextWatcher()
        {
            float sisiA;
            float sisiB;
            float sisiC;
            float hasil;

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2){}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                if(fragmentBangunDatarRuangSegitigaBinding.etKelilingSisiASegitiga.getText().toString().isEmpty() || fragmentBangunDatarRuangSegitigaBinding.etKelilingSisiBSegitiga.getText().toString().isEmpty() || fragmentBangunDatarRuangSegitigaBinding.etKelilingSisiCSegitiga.getText().toString().isEmpty())
                {
                    sisiA = 0;
                    sisiB = 0;
                    sisiC = 0;
                }
                else
                {
                    sisiA = Float.parseFloat(fragmentBangunDatarRuangSegitigaBinding.etKelilingSisiASegitiga.getText().toString());
                    sisiB = Float.parseFloat(fragmentBangunDatarRuangSegitigaBinding.etKelilingSisiBSegitiga.getText().toString());
                    sisiC = Float.parseFloat(fragmentBangunDatarRuangSegitigaBinding.etKelilingSisiCSegitiga.getText().toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable)
            {
                hasil = sisiA + sisiB + sisiC;

                fragmentBangunDatarRuangSegitigaBinding.tvHasilKelilingSegitiga.setText(String.valueOf(hasil));
            }
        });

        fragmentBangunDatarRuangSegitigaBinding.etKelilingSisiCSegitiga.addTextChangedListener(new TextWatcher()
        {
            float sisiA;
            float sisiB;
            float sisiC;
            float hasil;

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2){}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                if(fragmentBangunDatarRuangSegitigaBinding.etKelilingSisiASegitiga.getText().toString().isEmpty() || fragmentBangunDatarRuangSegitigaBinding.etKelilingSisiBSegitiga.getText().toString().isEmpty() || fragmentBangunDatarRuangSegitigaBinding.etKelilingSisiCSegitiga.getText().toString().isEmpty())
                {
                    sisiA = 0;
                    sisiB = 0;
                    sisiC = 0;
                }
                else
                {
                    sisiA = Float.parseFloat(fragmentBangunDatarRuangSegitigaBinding.etKelilingSisiASegitiga.getText().toString());
                    sisiB = Float.parseFloat(fragmentBangunDatarRuangSegitigaBinding.etKelilingSisiBSegitiga.getText().toString());
                    sisiC = Float.parseFloat(fragmentBangunDatarRuangSegitigaBinding.etKelilingSisiCSegitiga.getText().toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable)
            {
                hasil = sisiA + sisiB + sisiC;

                fragmentBangunDatarRuangSegitigaBinding.tvHasilKelilingSegitiga.setText(String.valueOf(hasil));
            }
        });

        fragmentBangunDatarRuangSegitigaBinding.etLuasAlasSegitiga.addTextChangedListener(new TextWatcher()
        {
            float alas;
            float tinggi;
            float hasil;

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2){}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                if(fragmentBangunDatarRuangSegitigaBinding.etLuasAlasSegitiga.getText().toString().isEmpty() || fragmentBangunDatarRuangSegitigaBinding.etLuasTinggiSegitiga.getText().toString().isEmpty())
                {
                    alas = 0;
                    tinggi = 0;
                }
                else
                {
                    alas = Float.parseFloat(fragmentBangunDatarRuangSegitigaBinding.etLuasAlasSegitiga.getText().toString());
                    tinggi = Float.parseFloat(fragmentBangunDatarRuangSegitigaBinding.etLuasTinggiSegitiga.getText().toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable)
            {
                hasil = (alas * tinggi) / 2;

                fragmentBangunDatarRuangSegitigaBinding.tvHasilLuasSegitiga.setText(String.valueOf(hasil));
            }
        });

        fragmentBangunDatarRuangSegitigaBinding.etLuasTinggiSegitiga.addTextChangedListener(new TextWatcher()
        {
            float alas;
            float tinggi;
            float hasil;

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2){}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                if(fragmentBangunDatarRuangSegitigaBinding.etLuasAlasSegitiga.getText().toString().isEmpty() || fragmentBangunDatarRuangSegitigaBinding.etLuasTinggiSegitiga.getText().toString().isEmpty())
                {
                    alas = 0;
                    tinggi = 0;
                }
                else
                {
                    alas = Float.parseFloat(fragmentBangunDatarRuangSegitigaBinding.etLuasAlasSegitiga.getText().toString());
                    tinggi = Float.parseFloat(fragmentBangunDatarRuangSegitigaBinding.etLuasTinggiSegitiga.getText().toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable)
            {
                hasil = (alas * tinggi) / 2;

                fragmentBangunDatarRuangSegitigaBinding.tvHasilLuasSegitiga.setText(String.valueOf(hasil));
            }
        });

        return view;
    }
}