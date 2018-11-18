package com.watsonlogic.escapeforaminute.fragment;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.watsonlogic.escapeforaminute.OnClickListener;
import com.watsonlogic.escapeforaminute.R;
import com.watsonlogic.escapeforaminute.util.ObjectUtil;
import com.watsonlogic.escapeforaminute.viewmodel.AppViewModel;

import java.lang.ref.WeakReference;
import java.util.List;

public abstract class RecyclerFragment extends Fragment
{
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
        savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_recycler, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.common_recycler_view);
        if (recyclerView != null)
            onInitializeRecyclerView(recyclerView);
    }

    public abstract void onInitializeRecyclerView(RecyclerView recyclerView);

    public static class BindingAdapter<T extends AppViewModel> extends RecyclerView
        .Adapter<BindingAdapter
        .BindingViewHolder>
    {
        private final WeakReference<OnClickListener> onClickListener;
        private List<T> viewModels;

        public BindingAdapter(@Nullable List<T> viewModels, OnClickListener
            onClickListener)
        {
            this.viewModels = viewModels;
            this.onClickListener = new WeakReference<>(onClickListener);
        }

        /**
         * For delayed viewModel list instantiation.
         *
         * @param onClickListener
         */
        public BindingAdapter(OnClickListener onClickListener)
        {
            this(null, onClickListener);
        }

        @NonNull
        @Override
        public BindingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
        {
            ViewDataBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), viewType, parent, false);
            return new BindingViewHolder(binding, onClickListener.get());
        }

        @Override
        public void onBindViewHolder(BindingViewHolder holder, int position)
        {
            AppViewModel viewModel = (AppViewModel)getObjForPosition(position);
            holder.bind(viewModel);
        }

        @Override
        public int getItemViewType(int position)
        {
            return getLayoutIdForPosition(position);
        }

        @Override
        public int getItemCount()
        {
            return ObjectUtil.isEmpty(viewModels) ? 0 : viewModels.size();
        }

        public void updateViewModels(@Nullable List<T> viewModels)
        {
            this.viewModels = viewModels;
            notifyDataSetChanged();
        }

        protected Object getObjForPosition(int position)
        {
            if (ObjectUtil.isEmpty(viewModels))
                return null;

            return viewModels.get(position);

        }

        protected int getLayoutIdForPosition(int position)
        {
            return ObjectUtil.isEmpty(viewModels) ? 0 : viewModels.get(position).getLayoutId();
        }

        public static class BindingViewHolder extends RecyclerView.ViewHolder
        {
            private final ViewDataBinding dataBinding;
            private final OnClickListener onClickListener;

            BindingViewHolder(ViewDataBinding dataBinding, OnClickListener onClickListener)
            {
                super(dataBinding.getRoot());
                this.onClickListener = onClickListener;
                this.dataBinding = dataBinding;
            }

            void bind(AppViewModel viewModel)
            {
                dataBinding.setVariable(getBindingResourceOnClickListenerName(viewModel), onClickListener);
                dataBinding.setVariable(getBindingResourceViewModelName(viewModel), viewModel);
                dataBinding.executePendingBindings();
            }

            ViewDataBinding getDataBinding()
            {
                return dataBinding;
            }

            /**
             * Extending class should override this method
             *
             * @return e.g. BR.myViewModel
             */
            static int getBindingResourceViewModelName(AppViewModel viewModel)
            {
                return viewModel.getBindingResourceViewModelName();
            }

            /**
             * Extending class should override this method
             *
             * @return e.g. BR.onClickListener
             */
            static int getBindingResourceOnClickListenerName(AppViewModel viewModel)
            {
                return viewModel.getBindingResourceOnClickListenerName();
            }
        }
    }
}
