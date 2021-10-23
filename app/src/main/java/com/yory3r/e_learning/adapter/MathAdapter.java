package com.yory3r.e_learning.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.yory3r.e_learning.R;
import com.yory3r.e_learning.databinding.MathItemBinding;
import com.yory3r.e_learning.fragment.MathActivityFragmentBangunDatarRuang;
import com.yory3r.e_learning.fragment.MathActivityFragmentKalkulator;
import com.yory3r.e_learning.fragment.MathActivityFragmentUrutinBilangan;
import com.yory3r.e_learning.model.Math;
import java.util.ArrayList;

public class MathAdapter extends RecyclerView.Adapter<MathAdapter.viewHolder>
{
    private ArrayList<Math> listMath;
    private Context context;
    private MathItemBinding mathItemBinding;

    public MathAdapter(ArrayList<Math> listMath, Context context)
    {
        this.listMath = listMath;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        mathItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.math_item,parent,false);
        return new viewHolder(mathItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position)
    {
        Math math = listMath.get(position);

        holder.mathItemBinding.btnMath.setText(math.getNamaMath());

        holder.mathItemBinding.btnMath.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                AppCompatActivity appCompatActivity = (AppCompatActivity) view.getContext();

                if(holder.getAdapterPosition() == 0)
                {
                    MathActivityFragmentKalkulator mathActivityFragmentKalkulator = new MathActivityFragmentKalkulator();

                    appCompatActivity.getSupportFragmentManager().beginTransaction().replace(R.id.fragmentChangeMath,mathActivityFragmentKalkulator).addToBackStack(null).commit();
                }
                else if(holder.getAdapterPosition() == 1)
                {
                    MathActivityFragmentBangunDatarRuang mathActivityFragmentBangunDatarRuang = new MathActivityFragmentBangunDatarRuang();

                    appCompatActivity.getSupportFragmentManager().beginTransaction().replace(R.id.fragmentChangeMath, mathActivityFragmentBangunDatarRuang).addToBackStack(null).commit();
                }
                else if(holder.getAdapterPosition() == 2)
                {
                    MathActivityFragmentUrutinBilangan mathActivityFragmentUrutinBilangan = new MathActivityFragmentUrutinBilangan();

                    appCompatActivity.getSupportFragmentManager().beginTransaction().replace(R.id.fragmentChangeMath, mathActivityFragmentUrutinBilangan).addToBackStack(null).commit();
                }
                else
                {
                    Toast.makeText(context, "On Progress !", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return listMath.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder
    {
        private MathItemBinding mathItemBinding;

        public viewHolder(@NonNull MathItemBinding mathItemBinding)
        {
            super(mathItemBinding.getRoot());
            this.mathItemBinding = mathItemBinding;
        }
    }
}