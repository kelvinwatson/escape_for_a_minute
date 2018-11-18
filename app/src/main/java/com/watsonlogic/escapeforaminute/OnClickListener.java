package com.watsonlogic.escapeforaminute;

import android.view.View;

import com.watsonlogic.escapeforaminute.viewmodel.AppViewModel;

public interface OnClickListener
{
    boolean onClick(View view, AppViewModel viewModel);
}
