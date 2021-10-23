package com.yory3r.e_learning.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.yory3r.e_learning.R;
import com.yory3r.e_learning.adapter.RegisterAdapter;
import com.yory3r.e_learning.adapter.RegisterAdapterAdmin;
import com.yory3r.e_learning.database.DatabaseRegister;
import com.yory3r.e_learning.databinding.ActivityAdminBinding;
import com.yory3r.e_learning.fragment.MainActivityFragmentProfil;
import com.yory3r.e_learning.model.Register;

import java.util.List;

public class AdminActivity extends AppCompatActivity
{
    private ActivityAdminBinding activityAdminBinding;
    private RecyclerView rvRegisterListAdmin;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        activityAdminBinding = DataBindingUtil.setContentView(this,R.layout.activity_admin);
        activityAdminBinding.setActivityAdmin(this);

        rvRegisterListAdmin = activityAdminBinding.rvtemp;
        rvRegisterListAdmin.setLayoutManager(new LinearLayoutManager(this));


        activityAdminBinding.btnBackAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "TEST", Toast.LENGTH_SHORT).show();
            }
        });

        getRegister();

    }

    public View.OnClickListener btnClicked = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            if(view.getId() == R.id.btnBackAdmin)
            {
                finish();
            }
        }
    };

    private void getRegister()
    {
        class GetRegister extends AsyncTask<Void, Void, List<Register>>
        {
            @Override
            protected List<Register> doInBackground(Void... voids)
            {
                return DatabaseRegister.getInstance(getApplicationContext()).getDatabase().registerDao().getAll();
            }

            @Override
            protected void onPostExecute(List<Register> registers)
            {
                super.onPostExecute(registers);

                RegisterAdapterAdmin registerAdapterAdmin = new RegisterAdapterAdmin(registers,getApplicationContext());
                rvRegisterListAdmin.setAdapter(registerAdapterAdmin);
            }
        }

        GetRegister getRegister = new GetRegister();
        getRegister.execute();
    }
}