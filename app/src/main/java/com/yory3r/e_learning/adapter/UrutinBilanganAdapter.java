package com.yory3r.e_learning.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.snackbar.Snackbar;
import com.yory3r.e_learning.R;
import com.yory3r.e_learning.databinding.DialogEditAngkaBinding;
import com.yory3r.e_learning.databinding.UrutinBilanganItemBinding;
import com.yory3r.e_learning.model.UrutinBilangan;
import java.util.ArrayList;

public class UrutinBilanganAdapter extends RecyclerView.Adapter<UrutinBilanganAdapter.viewHolder>
{
    private ArrayList<UrutinBilangan> listAngka;
    private Context context;
    private AlertDialog alertDialog;
    private DialogEditAngkaBinding dialogEditAngkaBinding;
    private View rootView;
    private View viewEditDialogAngka;
    private EditText etEditDialog;

    public UrutinBilanganAdapter(ArrayList<UrutinBilangan> listAngka, Context context)
    {
        this.listAngka = listAngka;
        this.context = context;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        UrutinBilanganItemBinding urutinBilanganItemBinding = DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.urutin_bilangan_item,parent,false);
        return new viewHolder(urutinBilanganItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position)
    {
        holder.urutinBilanganItemBinding.tvUrutinBilangan.setText(FtoS(listAngka.get(position).getAngka()));

        holder.urutinBilanganItemBinding.btnEditAngka.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                int position = holder.getAdapterPosition();
                dialogEdit(view,FtoS(listAngka.get(position).getAngka()),position);
            }
        });

        holder.urutinBilanganItemBinding.btnDeleteAngka.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                int position = holder.getAdapterPosition();
                listAngka.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return listAngka.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder
    {
        private UrutinBilanganItemBinding urutinBilanganItemBinding;

        public viewHolder(@NonNull UrutinBilanganItemBinding urutinBilanganItemBinding)
        {
            super(urutinBilanganItemBinding.getRoot());
            this.urutinBilanganItemBinding = urutinBilanganItemBinding;
        }
    }

    private void dialogEdit(View view, String value, int position)
    {
        initDialog(view);
        initView(view);
        createEditDialog(value);
        createAlertDialog(value,position);
        createButton();
    }

    private void initDialog(View view)
    {
        alertDialog = new AlertDialog.Builder(view.getContext()).setPositiveButton("Edit",null).create();

        LayoutInflater inflater = LayoutInflater.from(alertDialog.getContext());

        dialogEditAngkaBinding = DataBindingUtil.inflate(inflater, R.layout.dialog_edit_angka,null,false);
        dialogEditAngkaBinding.setDialogEditAngka(this);
    }

    private void initView(View view)
    {
        rootView = view;
        viewEditDialogAngka = dialogEditAngkaBinding.getRoot();
    }

    private void createEditDialog(String value)
    {
        etEditDialog = dialogEditAngkaBinding.etEditAngkaDialog;
        etEditDialog.setText(value);
        etEditDialog.setSelection(value.length());
    }

    private void createAlertDialog(String value, int position)
    {
        alertDialog.setTitle("Edit Angka");
        alertDialog.setView(viewEditDialogAngka);

        alertDialog.setOnShowListener(new DialogInterface.OnShowListener()
        {
            @Override
            public void onShow(DialogInterface dialogInterface)
            {
                Button btnEditAngka = ((AlertDialog) alertDialog).getButton(AlertDialog.BUTTON_POSITIVE);

                btnEditAngka.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        editValidation(value,position);
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
    }

    private void createButton()
    {
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

    private void editValidation(String value, int position)
    {
        String editDialog = etEditDialog.getText().toString();

        if(editDialog.isEmpty())
        {
            etEditDialog.setError("Angka Kosong !");
        }
        else if(editDialog.equals(value))
        {
            etEditDialog.setError("Angka Masih Sama !");
        }
        else
        {
            listAngka.get(position).setAngka(StoF(editDialog));
            notifyDataSetChanged();

            alertDialog.dismiss();

            cancelEdit(value,position);
        }
    }

    private void cancelEdit(String value, int position)
    {
        Snackbar.make(rootView,"Berhasil Mengedit Angka",Snackbar.LENGTH_LONG).setAction("CANCEL", new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                listAngka.get(position).setAngka(StoF(value));
                notifyDataSetChanged();
            }
        }).show();
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