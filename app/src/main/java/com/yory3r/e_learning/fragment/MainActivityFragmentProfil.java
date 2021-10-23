package com.yory3r.e_learning.fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.yory3r.e_learning.R;
import com.yory3r.e_learning.adapter.AboutAdapter;
import com.yory3r.e_learning.adapter.RegisterAdapter;
import com.yory3r.e_learning.database.DatabaseRegister;
import com.yory3r.e_learning.databinding.FragmentMainActivityProfilBinding;
import com.yory3r.e_learning.model.About;
import com.yory3r.e_learning.model.AboutDummy;
import com.yory3r.e_learning.model.Register;

import java.util.ArrayList;
import java.util.List;

public class MainActivityFragmentProfil extends Fragment
{
    private FragmentMainActivityProfilBinding fragmentMainActivityProfilBinding;
    private RecyclerView rvRegisterList;
    private int indexID;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        fragmentMainActivityProfilBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_main_activity_profil,container,false);
        fragmentMainActivityProfilBinding.setFragmentMainActivityProfil(this);

        View view = fragmentMainActivityProfilBinding.getRoot();

        rvRegisterList = fragmentMainActivityProfilBinding.rvRegisterList;
        rvRegisterList.setLayoutManager(new LinearLayoutManager(getContext()));

        indexID = getActivity().getIntent().getIntExtra("IndexID",0);

        getRegister();

        return view;
    }

    private void getRegister()
    {
        class GetRegister extends AsyncTask<Void, Void, List<Register>>
        {
            @Override
            protected List<Register> doInBackground(Void... voids)
            {
                return DatabaseRegister.getInstance(getContext()).getDatabase().registerDao().getAll();
            }

            @Override
            protected void onPostExecute(List<Register> registers)
            {
                super.onPostExecute(registers);

                RegisterAdapter registerAdapter = new RegisterAdapter(registers, getContext(), indexID);
                rvRegisterList.setAdapter(registerAdapter);
            }
        }

        GetRegister getRegister = new GetRegister();
        getRegister.execute();
    }
}