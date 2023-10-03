package com.example.practico2laboratorio3.request;

import android.content.Context;
import android.widget.Toast;

import com.example.practico2laboratorio3.modelo.Usuario;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ApiClient {
    public static void guardar(Context context, Usuario usuario){
        File archivo = new File(context.getFilesDir(), "usuario.dat");
        try{
            FileOutputStream fos = new FileOutputStream(archivo, false);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(usuario);
            bos.flush();
            oos.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Usuario leer(Context context){
        File archivo = new File(context.getFilesDir(), "usuario.dat");
        if(!archivo.exists()){
            return null;
        }
        Usuario usuario = null;
        try {
            FileInputStream fis = new FileInputStream(archivo);
            BufferedInputStream bis = new BufferedInputStream(fis);
            ObjectInputStream ois = new ObjectInputStream(bis);
            usuario = (Usuario)ois.readObject();
            ois.close();
            return usuario;
        } catch (FileNotFoundException e) {
            Toast.makeText(context, "Archivo no encontrado", Toast.LENGTH_SHORT).show();
            return null;
        } catch (IOException e) {
            Toast.makeText(context, "Error de E/S", Toast.LENGTH_SHORT).show();
            return null;
        } catch (ClassNotFoundException e) {
            Toast.makeText(context, "Class not found Exception", Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    public static Usuario login(Context context, String mail, String pass){
        Usuario usuario = leer(context);
        if (mail.equals(usuario.getMail())&& pass.equals(usuario.getPassword())){
            return usuario;
        }
        return null;
    }
}
