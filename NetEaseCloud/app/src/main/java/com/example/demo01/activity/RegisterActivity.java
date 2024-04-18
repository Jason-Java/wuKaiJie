package com.example.demo01.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.demo01.databinding.ActivityRegisterBinding;

/**
 * 注册界面
 */
public class RegisterActivity extends AppCompatActivity {

    ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}