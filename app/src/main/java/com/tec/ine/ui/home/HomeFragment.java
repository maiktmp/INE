package com.tec.ine.ui.home;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.tec.ine.R;
import com.tec.ine.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding vBind;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vBind = FragmentHomeBinding.inflate(inflater, container, false);
        vBind.vvHome.setVideoURI(Uri.parse("android.resource://" + getActivity().getPackageName() + "/" + R.raw.ine_homemp4));
        vBind.vvHome.start();
        vBind.vvHome.setOnPreparedListener(mp -> mp.setVolume(0, 0));
        return vBind.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        vBind.vvHome.stopPlayback();
        vBind.vvHome.start();

    }
}
