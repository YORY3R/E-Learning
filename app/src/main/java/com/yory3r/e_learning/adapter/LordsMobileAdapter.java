package com.yory3r.e_learning.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.yory3r.e_learning.R;
import com.yory3r.e_learning.databinding.GalleryItemBinding;
import com.yory3r.e_learning.model.LordsMobile;

import java.util.ArrayList;

public class LordsMobileAdapter extends RecyclerView.Adapter<LordsMobileAdapter.viewHolder>
{
    private ArrayList<LordsMobile> listLordsMobile;
    private Context context;

    public LordsMobileAdapter(ArrayList<LordsMobile> listLordsMobile, Context context)
    {
        this.listLordsMobile = listLordsMobile;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        GalleryItemBinding galleryItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.gallery_item,parent,false);

        return new viewHolder(galleryItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position)
    {
        LordsMobile lordsMobile = listLordsMobile.get(position);

        Glide.with(context).load(lordsMobile.getUrlLM()).into(holder.galleryItemBinding.ivFotoLM);

        holder.galleryItemBinding.tvNamaLM.setText(lordsMobile.getNamaLM());
    }

    @Override
    public int getItemCount()
    {
        return listLordsMobile.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder
    {
        private GalleryItemBinding galleryItemBinding;
        public viewHolder(@NonNull GalleryItemBinding galleryItemBinding)
        {
            super(galleryItemBinding.getRoot());

            this.galleryItemBinding = galleryItemBinding;
        }
    }
}
