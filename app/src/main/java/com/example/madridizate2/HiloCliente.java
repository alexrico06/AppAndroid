package com.example.madridizate2;

import android.content.Intent;
import android.os.Build;
import android.util.Log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HiloCliente extends Thread{

    Socket s;

    DataOutputStream dos=null;
    DataInputStream dis = null;
    ObjectOutputStream oos = null;

    public String correo;
    public String nombre;
    public String telefono;
    public String direccion;
    String contrasena;


    public ArrayList<String[]> listaResultados;
    String[] datosP = new String[7];
    String[] datosD = new String[5];
    String[] datosV = new String[5];
    int consulta;
    boolean existe;

    public HiloCliente(int consulta, String correo, String contrasena) {
        this.correo = correo;
        this.contrasena = contrasena;
        this.consulta = consulta;
    }

    public HiloCliente(int consulta, String[] datosP, String[] datosD){
        this.consulta=consulta;
        this.datosP=datosP;
        this.datosD=datosD;
    }

    public HiloCliente(int consulta,String[] datosV) {
        this.consulta = consulta;
        this.datosV = datosV;
    }

    public HiloCliente(int consulta) {
        this.consulta=consulta;
    }

    public HiloCliente(int consulta, String correo) {
        this.consulta=consulta;
        this.correo=correo;
    }

    @Override
    public void run() {

        try {
            //s = new Socket("192.168.1.18", 1522);
            s = new Socket("87.219.49.39", 1522);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            dos = new DataOutputStream(s.getOutputStream());
            dos.writeInt(consulta);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        switch (consulta){
            case 1:

                try {
                    dos.writeUTF(correo);
                    dos.writeUTF(contrasena);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                DataInputStream dis = null;
                try {
                    dis = new DataInputStream(s.getInputStream());
                    existe = dis.readBoolean();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;

            case 2:

                try {
                    oos = new ObjectOutputStream(s.getOutputStream());
                    oos.writeObject(datosP);
                    oos.writeObject(datosD);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;

            case 3:

                try {
                    oos = new ObjectOutputStream(s.getOutputStream());
                    oos.writeObject(datosV);

                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;

            case 4:

                ObjectInputStream ois = null;

                try {
                    ois=new ObjectInputStream(s.getInputStream());
                    listaResultados = (ArrayList<String[]>) ois.readObject();
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }

                break;

            case 5:

                try {
                    dos.writeUTF(correo);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    dis = new DataInputStream(s.getInputStream());
                    nombre = dis.readUTF();
                    correo = dis.readUTF();
                    telefono = dis.readUTF();
                    direccion = dis.readUTF();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;
        }

    }

}
