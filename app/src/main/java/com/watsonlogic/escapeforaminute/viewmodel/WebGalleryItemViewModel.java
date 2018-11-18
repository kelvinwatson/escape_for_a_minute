package com.watsonlogic.escapeforaminute.viewmodel;

import android.app.Application;
import android.support.annotation.NonNull;

import com.watsonlogic.escapeforaminute.R;

public class WebGalleryItemViewModel extends AppViewModel
{
    public WebGalleryItemViewModel(@NonNull Application application)
    {
        super(application);
    }

    @Override
    public int getLayoutId()
    {
        return R.layout.web_gallery_grid_item;
    }
}
