package com.example.practico2laboratorio3.ui.registro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.practico2laboratorio3.R;
import com.example.practico2laboratorio3.databinding.ActivityRegistroBinding;

public class RegistroActivity extends AppCompatActivity {

    private ActivityRegistroBinding binding;
    private RegistroActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRegistroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(RegistroActivityViewModel.class);

        viewModel.getUsuario().observe(this, usuario -> {
            binding.etDni.setText(usuario.getDni() + "");
            binding.etNombre.setText(usuario.getNombre());
            binding.etApellido.setText(usuario.getApellido());
            binding.etMail.setText(usuario.getMail());
            binding.etPass.setText(usuario.getPassword());
        });

        binding.btGuardar.setOnClickListener(v -> {
            viewModel.registrar(
                    Long.parseLong(binding.etDni.getText().toString()),
                    binding.etNombre.getText().toString(),
                    binding.etApellido.getText().toString(),
                    binding.etMail.getText().toString().trim(),
                    binding.etPass.getText().toString()
            );
        });

        viewModel.obtenerDatos(getIntent());
    }
}