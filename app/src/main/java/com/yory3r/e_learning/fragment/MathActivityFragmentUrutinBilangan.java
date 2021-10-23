package com.yory3r.e_learning.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.yory3r.e_learning.R;
import com.yory3r.e_learning.adapter.UrutinBilanganAdapter;
import com.yory3r.e_learning.databinding.FragmentMathActivityUrutinBilanganBinding;
import com.yory3r.e_learning.model.UrutinBilangan;

import java.util.ArrayList;

public class MathActivityFragmentUrutinBilangan extends Fragment implements View.OnClickListener
{
    private FragmentMathActivityUrutinBilanganBinding fragmentMathActivityUrutinBilanganBinding;
    private UrutinBilanganAdapter urutinBilanganAdapter;
    private ArrayList<UrutinBilangan> listAngka;
    private RecyclerView rvUrutinBilangan;
    private EditText etAngka;
    private Button btnAddAngka;
    private Button btnUrutinBesarKecil;
    private Button btnUrutinKecilBesar;
    private Button btnDeleteAll;
    private TextView tvMean;
    private TextView tvMedian;
    private TextView tvModus;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        fragmentMathActivityUrutinBilanganBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_math_activity_urutin_bilangan,container,false);
        fragmentMathActivityUrutinBilanganBinding.setFragmentMathActivityUrutinBilangan(this);

        View view = fragmentMathActivityUrutinBilanganBinding.getRoot();

        listAngka = new ArrayList<>();

        initBinding();

        btnAddAngka.setOnClickListener(this);
        btnUrutinBesarKecil.setOnClickListener(this);
        btnUrutinKecilBesar.setOnClickListener(this);
        btnDeleteAll.setOnClickListener(this);

        return view;
    }

    private void initBinding()
    {
        rvUrutinBilangan = fragmentMathActivityUrutinBilanganBinding.rvUrutinBilangan;
        etAngka = fragmentMathActivityUrutinBilanganBinding.etAngka;
        btnAddAngka = fragmentMathActivityUrutinBilanganBinding.btnAddAngka;
        btnUrutinBesarKecil = fragmentMathActivityUrutinBilanganBinding.btnUrutinBesarKecil;
        btnUrutinKecilBesar = fragmentMathActivityUrutinBilanganBinding.btnUrutinKecilBesar;
        btnDeleteAll = fragmentMathActivityUrutinBilanganBinding.btnDeleteAll;
        tvMean = fragmentMathActivityUrutinBilanganBinding.tvMean;
        tvMedian = fragmentMathActivityUrutinBilanganBinding.tvMedian;
        tvModus = fragmentMathActivityUrutinBilanganBinding.tvModus;
    }

    private void addAngka(ArrayList<UrutinBilangan> listAngka, float angka)
    {
        listAngka.add(new UrutinBilangan(angka));
    }

    private void initAdapter(ArrayList<UrutinBilangan> listAngka)
    {
        urutinBilanganAdapter = new UrutinBilanganAdapter(listAngka,getContext());
    }

    private void setAdapter(UrutinBilanganAdapter urutinBilanganAdapter)
    {
        rvUrutinBilangan.setAdapter(urutinBilanganAdapter);
        rvUrutinBilangan.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onClick(View view)
    {
        if(view.getId() == btnAddAngka.getId())
        {
            if(etAngka.getText().toString().isEmpty())
            {
                etAngka.setError("Angka Kosong !");
            }
            else
            {
                addAngka(listAngka, StoF(etAngka.getText().toString()));
                initAdapter(listAngka);
                setAdapter(urutinBilanganAdapter);

                etAngka.setText("");
            }
        }
        else if(view.getId() == btnUrutinBesarKecil.getId())
        {
            validationAngka(0,view);
        }
        else if(view.getId() == btnUrutinKecilBesar.getId())
        {
            validationAngka(1,view);
        }
        else if(view.getId() == btnDeleteAll.getId())
        {
            tvMean.setText("");
            tvMedian.setText("");
            tvModus.setText("");
            listAngka.clear();
            initAdapter(listAngka);
            setAdapter(urutinBilanganAdapter);
        }
    }

    private void validationAngka(int action, View view)
    {
        if(listAngka.isEmpty())
        {
            Toast.makeText(getContext(), "Angka Kosong !", Toast.LENGTH_SHORT).show();
        }
        else
        {
            urutinBilangan(action,view);
        }
    }

    private void urutinBilangan(int action, View view)
    {
        int a;
        int b;
        float temp;
        float angka[] = new float[listAngka.size()];

        ArrayList<UrutinBilangan> tempListAngka = new ArrayList<>();

        for(a = 0 ; a < listAngka.size() ; a++)
        {
            tempListAngka.add(new UrutinBilangan(listAngka.get(a).getAngka()));
        }

        for(a = 0 ; a < listAngka.size() ; a++)
        {
            angka[a] = listAngka.get(a).getAngka();
        }

        for(a = 0 ; a < angka.length ; a++)
        {
            for(b = 0 ; b < angka.length ; b++)
            {
                if(action == 0)
                {
                    if(angka[a] > angka[b])
                    {
                        temp = angka[a];
                        angka[a] = angka[b];
                        angka[b] = temp;
                    }
                }
                else if(action == 1)
                {
                    if(angka[a] < angka[b])
                    {
                        temp = angka[a];
                        angka[a] = angka[b];
                        angka[b] = temp;
                    }
                }
            }
        }

        listAngka.clear();

        for(a = 0 ; a < angka.length ; a++)
        {
            listAngka.add(new UrutinBilangan(angka[a]));
        }

        setMean(angka);
        setMedian(angka);
        setModus(angka);






        initAdapter(listAngka);
        setAdapter(urutinBilanganAdapter);

        Snackbar.make(view,"Berhasil Mengurutkan Angka",Snackbar.LENGTH_LONG).setAction("CANCEL", new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                listAngka.clear();

                for(int a = 0 ; a < tempListAngka.size() ; a++)
                {
                    listAngka.add(new UrutinBilangan(tempListAngka.get(a).getAngka()));
                }

                initAdapter(listAngka);
                setAdapter(urutinBilanganAdapter);
            }
        }).show();
    }

    private void setMean(float angka[])
    {
        int temp = 0;
        float mean;

        for(int a = 0 ; a < angka.length ; a++)
        {
            temp += angka[a];
        }

        mean = (float) temp / angka.length;

        tvMean.setText(FtoS(mean));
    }

    private void setMedian(float angka[])
    {
        int ganjil = 0;
        int genap[] = new int[2];

        float median;

        if(angka.length%2 == 0)
        {
            genap[0] = angka.length / 2;
            genap[1] = genap[0] - 1;

            median = (angka[genap[1]] + angka[genap[0]]) / 2;
        }
        else
        {
            ganjil = (angka.length - 1) / 2;

            median = angka[ganjil];
        }

        tvMedian.setText(FtoS(median));

    }

    private void setModus(float angka[])
    {
        int temp = 1;
        int max = 1;
        float modus = angka[0];

        for(int a = 1 ; a < angka.length ; a++)
        {
            if(angka[a] == angka[a - 1])
            {
                temp++;
            }
            else
            {
                if(temp > max)
                {
                    max = temp;
                    modus = angka[a - 1];
                }

                temp = 1;
            }
        }

        if(temp > max)
        {
            max = temp;
            modus = angka[angka.length - 1];
        }

        tvModus.setText(FtoS(modus));
    }

    private float StoF(String string)
    {
        return Float.parseFloat(string);
    }

    private String FtoS(float Float)
    {
        return String.valueOf(Float);
    }
}