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
import com.yory3r.e_learning.databinding.FragmentBangunDatarRuangPersegiBinding;
import com.yory3r.e_learning.model.BangunDatarRuang;
import com.yory3r.e_learning.model.BangunDatarRuangDummy;

public class BangunDatarRuangFragmentPersegi extends Fragment
{
    private String title;
    private int page;
    private FragmentBangunDatarRuangPersegiBinding fragmentBangunDatarRuangPersegiBinding;

    public static BangunDatarRuangFragmentPersegi newInstance(int page, String title)
    {
        BangunDatarRuangFragmentPersegi bangunDatarRuangFragmentPersegi = new BangunDatarRuangFragmentPersegi();
        Bundle bundle = new Bundle();
        bundle.putInt("Page",page);
        bundle.putString("Title",title);
        bangunDatarRuangFragmentPersegi.setArguments(bundle);

        return bangunDatarRuangFragmentPersegi;
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
        fragmentBangunDatarRuangPersegiBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_bangun_datar_ruang_persegi,container,false);
        fragmentBangunDatarRuangPersegiBinding.setFragmentBangunDatarRuangPersegi(this);

        View view = fragmentBangunDatarRuangPersegiBinding.getRoot();

        BangunDatarRuang bangunDatarRuang = new BangunDatarRuangDummy().listBangunDatarRuang.get(0);

        fragmentBangunDatarRuangPersegiBinding.tvDeskripsiPersegi.setText(bangunDatarRuang.getDeskripsiBangun());
        fragmentBangunDatarRuangPersegiBinding.tvKelilingPersegi.setText(bangunDatarRuang.getKelilingBangun());
        fragmentBangunDatarRuangPersegiBinding.tvLuasPersegi.setText(bangunDatarRuang.getLuasBangun());

        fragmentBangunDatarRuangPersegiBinding.etKelilingSisiPersegi.addTextChangedListener(new TextWatcher()
        {
            float sisi;
            float hasil;

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2){}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                if(charSequence.toString().isEmpty())
                {
                    sisi = 0;
                }
                else
                {
                    sisi = Float.parseFloat(fragmentBangunDatarRuangPersegiBinding.etKelilingSisiPersegi.getText().toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable)
            {
                hasil = 4 * sisi;

                fragmentBangunDatarRuangPersegiBinding.tvHasilKelilingPersegi.setText(String.valueOf(hasil));
            }
        });

        fragmentBangunDatarRuangPersegiBinding.etLuasSisiPersegi.addTextChangedListener(new TextWatcher()
        {
            float sisi;
            float hasil;

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2){}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                if(charSequence.toString().isEmpty())
                {
                    sisi = 0;
                }
                else
                {
                    sisi = Float.parseFloat(fragmentBangunDatarRuangPersegiBinding.etLuasSisiPersegi.getText().toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable)
            {
                hasil = sisi * sisi;

                fragmentBangunDatarRuangPersegiBinding.tvHasilLuasPersegi.setText(String.valueOf(hasil));
            }
        });

        return view;
    }
}