package com.watsonlogic.escapeforaminute.fragment;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.watsonlogic.escapeforaminute.OnClickListener;
import com.watsonlogic.escapeforaminute.activity.webwork.ColumnWidthModule;
import com.watsonlogic.escapeforaminute.viewmodel.AppViewModel;
import com.watsonlogic.escapeforaminute.viewmodel.WebGalleryItemViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.android.support.AndroidSupportInjection;

public class WebGalleryFragment extends RecyclerFragment implements OnClickListener
{
    @Inject
    Application application;
    @Inject
    ColumnWidthModule.ColumnWidth columnWidth;
    @Inject
    AutoFitGridLayoutManager layoutManager;

    @Inject //9. this is needed so we can inject webGalleryFragmentProvider into WebWorkActivity
    public WebGalleryFragment()
    {
        //for Dagger 2 injection in WebWorkActivity
    }

    @Override
    public void onAttach(Context context)
    {
        AndroidSupportInjection.inject(this); //11. for injecting gridLayoutmanager
        super.onAttach(context);
    }

    @Override
    public void onInitializeRecyclerView(@NonNull RecyclerView recyclerView)
    {
        Context context = recyclerView.getContext();
//        GridLayoutManager layoutManager = new AutoFitGridLayoutManager(context, 400);
//        GridLayoutManager layoutManager = new GridLayoutManager(context, 2);
        recyclerView.setLayoutManager(layoutManager);

        final List<AppViewModel> viewModels = new ArrayList<>();
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

    public static class AutoFitGridLayoutManager extends GridLayoutManager
    {
        private int columnWidth;
        private boolean columnWidthChanged = true;

        @Inject
        public AutoFitGridLayoutManager(Context context, ColumnWidthModule.ColumnWidth columnWidth)
        {
            super(context, 1);

            setColumnWidth(columnWidth.get());
        }

        public void setColumnWidth(int newColumnWidth)
        {
            if (newColumnWidth > 0 && newColumnWidth != columnWidth)
            {
                columnWidth = newColumnWidth;
                columnWidthChanged = true;
            }
        }

        @Override
        public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state)
        {
            if (columnWidthChanged && columnWidth > 0)
            {
                int totalSpace;
                if (getOrientation() == VERTICAL)
                {
                    totalSpace = getWidth() - getPaddingRight() - getPaddingLeft();
                }
                else
                {
                    totalSpace = getHeight() - getPaddingTop() - getPaddingBottom();
                }
                int spanCount = Math.max(1, totalSpace / columnWidth);
                setSpanCount(spanCount);
                columnWidthChanged = false;
            }
            super.onLayoutChildren(recycler, state);
        }
    }
}
