package com.example.practico2laboratorio3.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.practico2laboratorio3.R;
import com.example.practico2laboratorio3.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainActivityViewModel.class);

        binding.btLogin.setOnClickListener(v -> {
            viewModel.login(binding.etMail.getText().toString().trim(), binding.etPass.getText().toString());
        });

        binding.btRegistro.setOnClickListener(v -> {
            viewModel.registrar();
        });
    }
}