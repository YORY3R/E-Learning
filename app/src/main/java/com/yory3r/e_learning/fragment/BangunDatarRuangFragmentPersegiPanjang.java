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
import com.yory3r.e_learning.databinding.FragmentBangunDatarRuangPersegiPanjangBinding;
import com.yory3r.e_learning.model.BangunDatarRuang;
import com.yory3r.e_learning.model.BangunDatarRuangDummy;

public class BangunDatarRuangFragmentPersegiPanjang extends Fragment
{
    private String title;
    private int page;
    private FragmentBangunDatarRuangPersegiPanjangBinding fragmentBangunDatarRuangPersegiPanjangBinding;

    public static BangunDatarRuangFragmentPersegiPanjang newInstance(int page, String title)
    {
        BangunDatarRuangFragmentPersegiPanjang bangunDatarRuangFragmentPersegiPanjang = new BangunDatarRuangFragmentPersegiPanjang();
        Bundle bundle = new Bundle();
        bundle.putInt("Page",page);
        bundle.putString("Title",title);
        bangunDatarRuangFragmentPersegiPanjang.setArguments(bundle);

        return bangunDatarRuangFragmentPersegiPanjang;
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
        fragmentBangunDatarRuangPersegiPanjangBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_bangun_datar_ruang_persegi_panjang,container,false);
        fragmentBangunDatarRuangPersegiPanjangBinding.setFragmentBangunDatarRuangPersegiPanjang(this);

        View view = fragmentBangunDatarRuangPersegiPanjangBinding.getRoot();

        BangunDatarRuang bangunDatarRuang = new BangunDatarRuangDummy().listBangunDatarRuang.get(1);

        fragmentBangunDatarRuangPersegiPanjangBinding.tvDeskripsiPersegiPanjang.setText(bangunDatarRuang.getDeskripsiBangun());
        fragmentBangunDatarRuangPersegiPanjangBinding.tvKelilingPersegiPanjang.setText(bangunDatarRuang.getKelilingBangun());
        fragmentBangunDatarRuangPersegiPanjangBinding.tvLuasPersegiPanjang.setText(bangunDatarRuang.getLuasBangun());

        fragmentBangunDatarRuangPersegiPanjangBinding.etKelilingPanjangPersegiPanjang.addTextChangedListener(new TextWatcher()
        {
            float panjang;
            float lebar;
            float hasil;

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2){}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                if(fragmentBangunDatarRuangPersegiPanjangBinding.etKelilingPanjangPersegiPanjang.getText().toString().isEmpty() || fragmentBangunDatarRuangPersegiPanjangBinding.etKelilingLebarPersegiPanjang.getText().toString().isEmpty())
                {
                    panjang = 0;
                    lebar = 0;
                }
                else
                {
                    panjang = Float.parseFloat(fragmentBangunDatarRuangPersegiPanjangBinding.etKelilingPanjangPersegiPanjang.getText().toString());
                    lebar = Float.parseFloat(fragmentBangunDatarRuangPersegiPanjangBinding.etKelilingLebarPersegiPanjang.getText().toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable)
            {
                hasil = 2 * (panjang + lebar);

                fragmentBangunDatarRuangPersegiPanjangBinding.tvHasilKelilingPersegiPanjang.setText(String.valueOf(hasil));
            }
        });

        fragmentBangunDatarRuangPersegiPanjangBinding.etKelilingLebarPersegiPanjang.addTextChangedListener(new TextWatcher()
        {
            float panjang;
            float lebar;
            float hasil;

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2){}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                if(fragmentBangunDatarRuangPersegiPanjangBinding.etKelilingPanjangPersegiPanjang.getText().toString().isEmpty() || fragmentBangunDatarRuangPersegiPanjangBinding.etKelilingLebarPersegiPanjang.getText().toString().isEmpty())
                {
                    panjang = 0;
                    lebar = 0;
                }
                else
                {
                    panjang = Float.parseFloat(fragmentBangunDatarRuangPersegiPanjangBinding.etKelilingPanjangPersegiPanjang.getText().toString());
                    lebar = Float.parseFloat(fragmentBangunDatarRuangPersegiPanjangBinding.etKelilingLebarPersegiPanjang.getText().toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable)
            {
                hasil = 2 * (panjang + lebar);

                fragmentBangunDatarRuangPersegiPanjangBinding.tvHasilKelilingPersegiPanjang.setText(String.valueOf(hasil));
            }
        });

        fragmentBangunDatarRuangPersegiPanjangBinding.etLuasPanjangPersegiPanjang.addTextChangedListener(new TextWatcher()
        {
            float panjang;
            float lebar;
            float hasil;

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2){}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                if (fragmentBangunDatarRuangPersegiPanjangBinding.etLuasPanjangPersegiPanjang.getText().toString().isEmpty() || fragmentBangunDatarRuangPersegiPanjangBinding.etLuasLebarPersegiPanjang.getText().toString().isEmpty())
                {
                    panjang = 0;
                    lebar = 0;
                }
                else
                {
                    panjang = Float.parseFloat(fragmentBangunDatarRuangPersegiPanjangBinding.etLuasPanjangPersegiPanjang.getText().toString());
                    lebar = Float.parseFloat(fragmentBangunDatarRuangPersegiPanjangBinding.etLuasLebarPersegiPanjang.getText().toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable)
            {
                hasil = panjang * lebar;

                fragmentBangunDatarRuangPersegiPanjangBinding.tvHasilLuasPersegiPanjang.setText(String.valueOf(hasil));
            }
        });

        fragmentBangunDatarRuangPersegiPanjangBinding.etLuasLebarPersegiPanjang.addTextChangedListener(new TextWatcher()
        {
            float panjang;
            float lebar;
            float hasil;

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2){}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                if(fragmentBangunDatarRuangPersegiPanjangBinding.etKelilingPanjangPersegiPanjang.getText().toString().isEmpty() || fragmentBangunDatarRuangPersegiPanjangBinding.etKelilingLebarPersegiPanjang.getText().toString().isEmpty())
                {
                    panjang = 0;
                    lebar = 0;
                }
                else
                {
                    panjang = Float.parseFloat(fragmentBangunDatarRuangPersegiPanjangBinding.etLuasPanjangPersegiPanjang.getText().toString());
                    lebar = Float.parseFloat(fragmentBangunDatarRuangPersegiPanjangBinding.etLuasLebarPersegiPanjang.getText().toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable)
            {
                hasil = panjang * lebar;

                fragmentBangunDatarRuangPersegiPanjangBinding.tvHasilLuasPersegiPanjang.setText(String.valueOf(hasil));
            }
        });

        return view;
    }
}