package com.yory3r.e_learning.adapter;

import android.content.Context;
import android.hardware.camera2.TotalCaptureResult;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.yory3r.e_learning.R;
import com.yory3r.e_learning.database.DatabaseRegister;
import com.yory3r.e_learning.databinding.RegisterItemAdminBinding;
import com.yory3r.e_learning.model.About;
import com.yory3r.e_learning.model.Register;
import com.yory3r.e_learning.preferences.LoginPreferences;

import java.util.List;

public class RegisterAdapterAdmin extends RecyclerView.Adapter<RegisterAdapterAdmin.viewHolder>
{
    private List<Register> registerList;
    private Context context;
    private RegisterItemAdminBinding registerItemAdminBinding;
    private Register register;
    private LoginPreferences loginPreferences;

    public RegisterAdapterAdmin(List<Register> registerList, Context context)
    {
        this.registerList = registerList;
        this.context = context;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RegisterAdapterAdmin.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        registerItemAdminBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.register_item_admin,parent,false);
        return new viewHolder(registerItemAdminBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RegisterAdapterAdmin.viewHolder holder, int position)
    {
        register = registerList.get(position);

        holder.registerItemAdminBinding.tvNamaProfilAdmin.setText(register.getNamaLengkap());
        holder.registerItemAdminBinding.tvNPMProfilAdmin.setText(register.getNpm());
        holder.registerItemAdminBinding.tvTeleponProfilAdmin.setText(register.getNomorTelepon());
        holder.registerItemAdminBinding.tvAlamatProfilAdmin.setText(register.getAlamat());
        holder.registerItemAdminBinding.tvUsernameProfilAdmin.setText(register.getUsername());
        holder.registerItemAdminBinding.tvPasswordProfilAdmin.setText(register.getPassword());

        holder.registerItemAdminBinding.btnDeleteAdmin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(registerList.size() == 1)
                {
                    Toast.makeText(view.getContext(), "Akses Ditolak !", Toast.LENGTH_SHORT).show();
                    Toast.makeText(view.getContext(), "Data Hanya Satu !", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(loginPreferences.getUserLogin().getUsername().equals(holder.registerItemAdminBinding.tvUsernameProfilAdmin.getText().toString()))
                    {
                        Toast.makeText(view.getContext(), "Akses Ditolak !", Toast.LENGTH_SHORT).show();
                        Toast.makeText(view.getContext(), "Tidak Boleh Hapus Data User Yang Sedang Login !", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        String tempNama = register.getNamaLengkap();
                        String tempNpm = register.getNpm();
                        String tempTelepon = register.getNomorTelepon();
                        String tempAlamat = register.getAlamat();
                        String tempUsername = register.getUsername();
                        String tempPassword = register.getPassword();

                        Register tempRegister = register;
                        registerList.remove(register);
                        deleteRegister(register);

                        Snackbar.make(view,"Berhasil Menghapus Data",Snackbar.LENGTH_LONG).setAction("CANCEL", new View.OnClickListener()
                        {
                            @Override
                            public void onClick(View view)
                            {
                                registerList.add(tempRegister);
                                addRegister(tempNama,tempNpm,tempTelepon,tempAlamat,tempUsername,tempPassword);
                            }
                        }).show();
                    }
                }
            }
        });

    }

    @Override
    public int getItemCount()
    {
        return registerList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder
    {
        private RegisterItemAdminBinding registerItemAdminBinding;

        public viewHolder(@NonNull RegisterItemAdminBinding registerItemAdminBinding)
        {
            super(registerItemAdminBinding.getRoot());

            loginPreferences = new LoginPreferences(context);
            this.registerItemAdminBinding = registerItemAdminBinding;
        }
    }

    private void deleteRegister(Register register)
    {
        class DeleteRegister extends AsyncTask<Void, Void, Void>
        {
            @Override
            protected Void doInBackground(Void... voids)
            {
                DatabaseRegister.getInstance(context).getDatabase().registerDao().deleteRegister(register);
                return null;
            }
        }

        DeleteRegister deleteRegister = new DeleteRegister();
        deleteRegister.execute();
        notifyDataSetChanged();
    }

    private void addRegister(String tempNama, String tempNpm, String tempTelepon, String tempAlamat, String tempUsername, String tempPassword)
    {
        class AddRegister extends AsyncTask<Void, Void, Void>
        {
            @Override
            protected Void doInBackground(Void... voids)
            {
                Register register = new Register();
                register.setNamaLengkap(tempNama);
                register.setNpm(tempNpm);
                register.setNomorTelepon(tempTelepon);
                register.setAlamat(tempAlamat);
                register.setUsername(tempUsername);
                register.setPassword(tempPassword);

                DatabaseRegister.getInstance(context).getDatabase().registerDao().insertRegister(register);

                return null;
            }
        }

        AddRegister addRegister = new AddRegister();
        addRegister.execute();
        notifyDataSetChanged();
    }
}
