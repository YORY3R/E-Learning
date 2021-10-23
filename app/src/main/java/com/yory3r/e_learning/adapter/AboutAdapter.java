package com.yory3r.e_learning.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.yory3r.e_learning.model.About;
import com.yory3r.e_learning.R;
import com.yory3r.e_learning.databinding.AboutItemBinding;

import java.util.ArrayList;

public class AboutAdapter extends RecyclerView.Adapter<AboutAdapter.viewHolder>
{
    private ArrayList<About> listAbout;
    private Context context;

    public AboutAdapter(ArrayList<About> listAbout, Context context)
    {
        this.listAbout = listAbout;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        AboutItemBinding aboutItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.about_item,parent,false);

        return new viewHolder(aboutItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position)
    {
        holder.aboutItemBinding.ivFotoAbout.setImageResource(listAbout.get(position).getFotoAbout());
        holder.aboutItemBinding.tvNamaAbout.setText(listAbout.get(position).getNamaAbout());
        holder.aboutItemBinding.tvNPMAbout.setText(String.valueOf(listAbout.get(position).getNPMAbout()));
        holder.aboutItemBinding.tvNomorTelpAbout.setText(listAbout.get(position).getNomorTelpAbout());
    }

    @Override
    public int getItemCount()
    {
        return listAbout.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder
    {

        private AboutItemBinding aboutItemBinding;

        public viewHolder(@NonNull AboutItemBinding aboutItemBinding)
        {
            super(aboutItemBinding.getRoot());

            this.aboutItemBinding = aboutItemBinding;
        }
    }
}
