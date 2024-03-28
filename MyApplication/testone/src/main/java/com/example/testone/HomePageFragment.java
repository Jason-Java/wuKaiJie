package com.example.testone;


import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class HomePageFragment extends Fragment {
    private boolean flag = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.homepage_fragment,null);

        LinearLayout cartoon = view.findViewById(R.id.cartoon);
        AnimationDrawable anim = (AnimationDrawable)cartoon.getBackground();
        anim.start();
        cartoon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flag){
                    anim.start();
                    flag = false;
                }else{
                    anim.stop();
                    flag = true;
                }
            }
        });

        return view;
    }
}
