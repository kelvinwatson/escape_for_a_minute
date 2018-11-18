package com.watsonlogic.escapeforaminute.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.watsonlogic.escapeforaminute.BR;

public abstract class AppViewModel extends AndroidViewModel
{
    public AppViewModel(@NonNull Application application)
    {
        super(application);
    }

    public abstract int getLayoutId();

    public int getBindingResourceViewModelName()
    {
        return BR.viewModelContent;
    }

    public int getBindingResourceOnClickListenerName()
    {
        return BR.onClickListener;
    }
}
