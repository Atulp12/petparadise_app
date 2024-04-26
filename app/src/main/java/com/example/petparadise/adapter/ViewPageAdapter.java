package com.example.petparadise.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.petparadise.Fragments.CompleteFragment;
import com.example.petparadise.Fragments.TrackFragment;
import com.example.petparadise.Fragments.WaitingFragment;

public class ViewPageAdapter extends FragmentStateAdapter {
    public ViewPageAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public ViewPageAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    public ViewPageAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new WaitingFragment();

            case 1:
                return new TrackFragment();

            case 2:
                return new CompleteFragment();

            default:
                return new WaitingFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
