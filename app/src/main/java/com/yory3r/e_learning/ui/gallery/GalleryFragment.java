package com.yory3r.e_learning.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.yory3r.e_learning.R;
import com.yory3r.e_learning.adapter.LordsMobileAdapter;
import com.yory3r.e_learning.databinding.FragmentGalleryBinding;
import com.yory3r.e_learning.model.LordsMobile;
import com.yory3r.e_learning.model.LordsMobileDummy;
import java.util.ArrayList;

public class GalleryFragment extends Fragment
{
    private FragmentGalleryBinding fragmentGalleryBinding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        fragmentGalleryBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_gallery,container,false);
        fragmentGalleryBinding.setFragmentGallery(this);

        View view = fragmentGalleryBinding.getRoot();

        ArrayList<LordsMobile> listLordsMobile = new LordsMobileDummy().listLordsMobile;
        LordsMobileAdapter lordsMobileAdapter = new LordsMobileAdapter(listLordsMobile,view.getContext());

        fragmentGalleryBinding.rvGalleryLM.setAdapter(lordsMobileAdapter);
        fragmentGalleryBinding.rvGalleryLM.setLayoutManager(new LinearLayoutManager(view.getContext()));

        return view;
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        fragmentGalleryBinding = null;
    }
}