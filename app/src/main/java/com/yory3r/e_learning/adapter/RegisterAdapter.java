package com.yory3r.e_learning.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;
import com.yory3r.e_learning.R;
import com.yory3r.e_learning.activity.LoginActivity;
import com.yory3r.e_learning.database.DatabaseRegister;
import com.yory3r.e_learning.databinding.DialogEditBinding;
import com.yory3r.e_learning.databinding.RegisterItemBinding;
import com.yory3r.e_learning.model.Register;
import com.yory3r.e_learning.preferences.LoginPreferences;

import java.util.List;

public class RegisterAdapter extends RecyclerView.Adapter<RegisterAdapter.viewHolder> implements View.OnClickListener
{
    private List<Register> registerList;
    private Context context;
    private int ID;
    private RegisterItemBinding registerItemBinding;
    private LoginActivity loginActivity;
    private Register register;
    private String temp;

    public RegisterAdapter(List<Register> registerList, Context context, int indexID)
    {
        this.registerList = registerList;
        this.context = context;
        this.ID = indexID - 1;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RegisterAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        registerItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.register_item,parent,false);
        return new viewHolder(registerItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RegisterAdapter.viewHolder holder, int position)
    {
        register = registerList.get(ID);

        holder.registerItemBinding.tvNamaProfil.setText(register.getNamaLengkap());
        holder.registerItemBinding.tvNPMProfil.setText(register.getNpm());
        holder.registerItemBinding.tvTeleponProfil.setText(register.getNomorTelepon());
        holder.registerItemBinding.tvAlamatProfil.setText(register.getAlamat());
        holder.registerItemBinding.tvUsernameProfil.setText(register.getUsername());
        holder.registerItemBinding.tvPasswordProfil.setText(register.getPassword());

        holder.registerItemBinding.btnNamaProfilEdit.setOnClickListener(this);
        holder.registerItemBinding.btnNPMProfilEdit.setOnClickListener(this);
        holder.registerItemBinding.btnTeleponProfilEdit.setOnClickListener(this);
        holder.registerItemBinding.btnAlamatProfilEdit.setOnClickListener(this);
        holder.registerItemBinding.btnUsernameProfilEdit.setOnClickListener(this);
        holder.registerItemBinding.btnPasswordProfilEdit.setOnClickListener(this);
    }

    @Override
    public int getItemCount()
    {
        return 1;
    }

    @Override
    public void onClick(View view)
    {
        if(view.getId() == R.id.btnNamaProfilEdit)
        {
            String title = "Nama";
            String value = registerItemBinding.tvNamaProfil.getText().toString();

            dialogEdit(view,title,value);
        }
        else if(view.getId() == R.id.btnNPMProfilEdit)
        {
            String title = "NPM";
            String value = registerItemBinding.tvNPMProfil.getText().toString();

            dialogEdit(view,title,value);
        }
        else if(view.getId() == R.id.btnTeleponProfilEdit)
        {
            String title = "Telepon";
            String value = registerItemBinding.tvTeleponProfil.getText().toString();

            dialogEdit(view,title,value);
        }
        else if(view.getId() == R.id.btnAlamatProfilEdit)
        {
            String title = "Alamat";
            String value = registerItemBinding.tvAlamatProfil.getText().toString();

            dialogEdit(view,title,value);
        }
        else if(view.getId() == R.id.btnUsernameProfilEdit)
        {
            String title = "Username";
            String value = registerItemBinding.tvUsernameProfil.getText().toString();

            dialogEdit(view,title,value);
        }
        else if(view.getId() == R.id.btnPasswordProfilEdit)
        {
            String title = "Password";
            String value = registerItemBinding.tvPasswordProfil.getText().toString();

            dialogEdit(view,title,value);
        }
    }

    public class viewHolder extends RecyclerView.ViewHolder
    {
        private RegisterItemBinding registerItemBinding;

        public viewHolder(@NonNull RegisterItemBinding registerItemBinding)
        {
            super(registerItemBinding.getRoot());

            this.registerItemBinding = registerItemBinding;
        }
    }

    private void dialogEdit(View view, String title, String value)
    {
        AlertDialog alertDialog = new AlertDialog.Builder(view.getContext()).setPositiveButton("Edit",null).create();

        View rootView = view;

        LayoutInflater inflater = LayoutInflater.from(alertDialog.getContext());

        DialogEditBinding dialogEditBinding = DataBindingUtil.inflate(inflater,R.layout.dialog_edit,null,false);
        dialogEditBinding.setDialogEdit(this);

        View viewEditDialog = dialogEditBinding.getRoot();

        EditText etEditDialog = dialogEditBinding.etEditDialog;
        etEditDialog.setText(value);
        etEditDialog.setSelection(value.length());

        alertDialog.setTitle("Edit " + title);
        alertDialog.setView(viewEditDialog);

        alertDialog.setOnShowListener(new DialogInterface.OnShowListener()
        {
            @Override
            public void onShow(DialogInterface dialogInterface)
            {
                Button btnEdit = ((AlertDialog) alertDialog).getButton(AlertDialog.BUTTON_POSITIVE);

                btnEdit.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        boolean check;

                        if(etEditDialog.getText().toString().isEmpty())
                        {
                            etEditDialog.setError(title + " Kosong !");
                            check = false;
                        }
                        else if(etEditDialog.getText().toString().equals(value))
                        {
                            etEditDialog.setError(title + " Masih Sama !");
                            check = false;
                        }
                        else
                        {
                            temp = value;
                            check = true;

                            if(title.equals("NPM") || title.equals("Telepon"))
                            {
                                for(int a = 0 ; a < etEditDialog.length() ; a++)
                                {
                                    if(etEditDialog.getText().toString().charAt(a) < 48 || etEditDialog.getText().toString().charAt(a) > 57)
                                    {
                                        etEditDialog.setError(title + "Tidak Valid !");
                                        check = false;

                                        break;
                                    }
                                    else
                                    {
                                        check = true;
                                    }
                                }
                            }

                            if(title.equals("Nama"))
                            {
                                register.setNamaLengkap(etEditDialog.getText().toString());
                            }
                            else if(title.equals("NPM"))
                            {
                                register.setNpm(etEditDialog.getText().toString());
                            }
                            else if(title.equals("Telepon"))
                            {
                                register.setNomorTelepon(etEditDialog.getText().toString());
                            }
                            else if(title.equals("Alamat"))
                            {
                                register.setAlamat(etEditDialog.getText().toString());
                            }
                            else if(title.equals("Username"))
                            {
                                register.setUsername(etEditDialog.getText().toString());
                            }
                            else if(title.equals("Password"))
                            {
                                register.setPassword(etEditDialog.getText().toString());
                            }

                            if(check == true)
                            {
                                updateRegister(register);

                                alertDialog.dismiss();

                                Snackbar.make(rootView,"Berhasil Mengedit " + title,Snackbar.LENGTH_LONG).setAction("CANCEL", new View.OnClickListener()
                                {
                                    @Override
                                    public void onClick(View view)
                                    {
                                        if(title.equals("Nama"))
                                        {
                                            register.setNamaLengkap(temp);
                                        }
                                        else if(title.equals("NPM"))
                                        {
                                            register.setNpm(temp);
                                        }
                                        else if(title.equals("Telepon"))
                                        {
                                            register.setNomorTelepon(temp);
                                        }
                                        else if(title.equals("Alamat"))
                                        {
                                            register.setAlamat(temp);
                                        }
                                        else if(title.equals("Username"))
                                        {
                                            register.setUsername(temp);
                                        }
                                        else if(title.equals("Password"))
                                        {
                                            register.setPassword(temp);
                                        }

                                        updateRegister(register);
                                    }
                                }).show();
                            }
                        }
                    }
                });
            }
        });

        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.dismiss();
            }
        });

        alertDialog.show();

        Button btnPositive = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
        Button btnNegative = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);

        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) btnPositive.getLayoutParams();

        layoutParams.weight = 10;
        layoutParams.rightMargin = 25;
        layoutParams.leftMargin = 10;

        btnPositive.setBackgroundColor(0xFF669900);
        btnPositive.setTextColor(0xFF000000);
        btnPositive.setLayoutParams(layoutParams);

        btnNegative.setBackgroundColor(0xFFCC0000);
        btnNegative.setTextColor(0xFFFFFFFF);
        btnNegative.setLayoutParams(layoutParams);
    }

    private void updateRegister(Register register)
    {
        class UpdateRegister extends AsyncTask<Void, Void, Void>
        {
            @Override
            protected Void doInBackground(Void... voids)
            {
                DatabaseRegister.getInstance(context).getDatabase().registerDao().updateRegister(register);
                return null;
            }

            @Override
            protected void onPostExecute(Void unused)
            {
                super.onPostExecute(unused);
                LoginPreferences loginPreferences = new LoginPreferences(context);
                loginPreferences.setLogin(register.getUsername(),register.getPassword());
            }
        }

        UpdateRegister updateRegister = new UpdateRegister();
        updateRegister.execute();
        notifyDataSetChanged();
    }
}
