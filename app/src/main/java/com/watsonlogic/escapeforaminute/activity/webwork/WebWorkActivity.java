package com.watsonlogic.escapeforaminute.activity.webwork;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.watsonlogic.escapeforaminute.R;
import com.watsonlogic.escapeforaminute.fragment.WebGalleryFragment;

import javax.inject.Inject;
import javax.inject.Provider;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.AndroidSupportInjection;
import dagger.android.support.HasSupportFragmentInjector;

public class WebWorkActivity extends AppCompatActivity implements HasSupportFragmentInjector
{
    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingFragmentInjector;

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

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector()
    {
        return dispatchingFragmentInjector;
    }
}
