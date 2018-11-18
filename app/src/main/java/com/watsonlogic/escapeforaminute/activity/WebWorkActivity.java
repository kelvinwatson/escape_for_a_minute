package com.watsonlogic.escapeforaminute.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.watsonlogic.escapeforaminute.R;
import com.watsonlogic.escapeforaminute.fragment.WebGalleryFragment;

import javax.inject.Inject;
import javax.inject.Provider;

import dagger.android.AndroidInjection;
import dagger.android.support.AndroidSupportInjection;

public class WebWorkActivity extends AppCompatActivity
{
    @Inject
    Provider<WebGalleryFragment> webGalleryFragmentProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        AndroidInjection.inject(this);
        //9. this is absolutely necessary so that we can inject webGalleryFragmentProvider
        //else compiles but crashes when you try to open the fragment (null)

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_web_work);
        if (savedInstanceState == null)
        {
            getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, webGalleryFragmentProvider.get(), null).commit();
        }
    }
}
