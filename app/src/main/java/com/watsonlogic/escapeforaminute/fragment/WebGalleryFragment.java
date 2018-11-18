package com.watsonlogic.escapeforaminute.fragment;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.watsonlogic.escapeforaminute.OnClickListener;
import com.watsonlogic.escapeforaminute.viewmodel.AppViewModel;
import com.watsonlogic.escapeforaminute.viewmodel.WebGalleryItemViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class WebGalleryFragment extends RecyclerFragment implements OnClickListener
{
    @Inject
    Application application;

    @Inject //9. this is needed so we can inject webGalleryFragment into WebWorkActivity
    public WebGalleryFragment()
    {
        //for Dagger 2 injection
    }

    @Override
    public void onInitializeRecyclerView(@NonNull RecyclerView recyclerView)
    {
        Context context = recyclerView.getContext();
        GridLayoutManager layoutManager = new GridLayoutManager(context, 2, LinearLayoutManager
            .HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        List<AppViewModel> viewModels = new ArrayList<>();
        viewModels.add(new WebGalleryItemViewModel(application));
        viewModels.add(new WebGalleryItemViewModel(application));
        viewModels.add(new WebGalleryItemViewModel(application));

        recyclerView.setAdapter(new BindingAdapter(viewModels, this));
    }

    @Override
    public boolean onClick(View view, AppViewModel viewModel)
    {
        return false;
    }
}
