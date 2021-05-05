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

    public String dni;
    public String correo;

    String contrasena;
    String texto;
    char letra;

    public ArrayList<String[]> listaResultados;
    String[] datosP = new String[7];
    String[] datosD = new String[5];
    String[] datosV = new String[5];
    String[] datosT = new String[4];
    String [] datos;
    int consulta;
    boolean existe;

    //VERIFICAR USUARIO
    public HiloCliente(int consulta, String correo, String contrasena) {
        this.correo = correo;
        this.contrasena = contrasena;
        this.consulta = consulta;
    }

    //INSERTAR USUARIO Y DIRECCION
    public HiloCliente(int consulta, String[] datosP, String[] datosD){
        this.consulta=consulta;
        this.datosP=datosP;
        this.datosD=datosD;
    }

    //INSERTAR DATOS VEHICULO
    public HiloCliente(int consulta,String[] datosV) {
        this.consulta = consulta;
        this.datosV = datosV;
    }

    //LISTADO PLAZAS
    public HiloCliente(int consulta) {
        this.consulta=consulta;
    }

    //INFORMACION USUARIO
    //INFORMACION TARJETA
    //INFORMACION VEHICULOS
    public HiloCliente(int consulta, String texto) {
        this.consulta=consulta;
        this.texto=texto;
    }

    //MODIFICAR TARJETA
    //MODIFICAR DATOS USUARIO
    public HiloCliente(int consulta,String[] datos,String correo,char letra) {
        this.consulta = consulta;
        this.datos = datos;
        this.correo = correo;
        this.letra = letra;
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

                //DataInputStream dis = null;
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
                    dos.writeUTF(texto);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    dis = new DataInputStream(s.getInputStream());
                    User.setDni(dis.readUTF());
                    User.setNombre(dis.readUTF());
                    User.setTel(dis.readUTF());
                    User.setCalle(dis.readUTF());
                    User.setNumero(dis.readUTF());
                    User.setPortal(dis.readUTF());
                    User.setPiso(dis.readUTF());
                    User.setCiudad(dis.readUTF());
                    User.setCp(dis.readUTF());

                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;
            case 6:
                Boolean flag;

                System.out.println(texto);
                try {
                    dos.writeUTF(texto);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {

                    dis = new DataInputStream(s.getInputStream());
                    flag = dis.readBoolean();
                    System.out.println(flag);
                    if(flag){

                        User.setNumTarjeta(dis.readUTF());
                        User.setCvv(dis.readUTF());
                        User.setFechaCaducidad(dis.readUTF());
                        User.setTipoTarjeta(dis.readUTF());

                    }else{

                        User.setNumTarjeta("");
                        User.setCvv("");
                        User.setFechaCaducidad("");
                        User.setTipoTarjeta("");

                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case 7:
                try {

                    if(letra == 'u'){
                        dos.writeChar('u');
                    }else{
                        dos.writeChar('t');
                    }

                } catch (IOException e) {
                e.printStackTrace();
                }

                try {

                    dos.writeUTF(correo);

                    oos = new ObjectOutputStream(s.getOutputStream());
                    oos.writeObject(datos);

                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;

            case 8:

                try {
                    dos.writeUTF(texto);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ArrayList<String> listaApodos = new ArrayList<>();
                ObjectInputStream ois2 = null;

                try {
                    ois2 =new ObjectInputStream(s.getInputStream());
                    listaApodos = (ArrayList<String>) ois2.readObject();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                break;
        }

    }

}
