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
    String direccion;
    String vehiculo;
    String texto;
    char letra;

    public ArrayList<String[]> listaResultados;
    public ArrayList<String> listaApodos = new ArrayList<>();
    public ArrayList<String> listaPlazasParkin;
    String[] datosP = new String[7];
    String[] datosD = new String[5];
    String[] datosV = new String[5];
    String[] datosR = new String[6];
    //String[] datosT = new String[4];
    String [] datos;
    int consulta;
    int id;
    boolean existe;


    //VERIFICAR USUARIO
    //INFORMACION PLAZAS DE UN PARKING POR DIRECCION
    public HiloCliente(int consulta, int id, String texto1, String texto2) {
        this.consulta = consulta;

        if(id==1){
            this.correo = texto1;
            this.contrasena = texto2;
        }else{
            this.direccion = texto1;
            this.vehiculo = texto2;
        }

    }

    //INSERTAR USUARIO Y DIRECCION
    public HiloCliente(int consulta, String[] datosP, String[] datosD){
        this.consulta=consulta;
        this.datosP=datosP;
        this.datosD=datosD;
    }


    //INSERTAR DATOS VEHICULO
    //INSERTAR DATOS RESERVA
    public HiloCliente(int consulta,int id, String[] datos) {
        this.consulta = consulta;

        if(id==1){
            this.datosV = datos;
        }else{
            this.datosR = datos;
        }

    }




    //LISTADO PLAZAS
    public HiloCliente(int consulta) {
        this.consulta=consulta;
    }

    //INFORMACION USUARIO POR CORREO
    //INFORMACION TARJETA POR CORREO
    //INFORMACION ALIAS VEHICULOS POR CORREO
    //INFORMACION VEHICULOS POR ALIAS
    //ELIMINAR VEHICULO POR MATRICULA
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
            s = new Socket("37.11.224.72", 1522);
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
        System.out.println(consulta);

        switch (consulta){
            case 1:

                //VERIFICAR USUARIO

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
                System.out.println(existe);
                break;

            case 2:  //INSERTAR DATOS USER Y SU DIRECCION

                try {
                    oos = new ObjectOutputStream(s.getOutputStream());
                    oos.writeObject(datosP);
                    oos.writeObject(datosD);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;

            case 3:  //INSERTAR DATOS DEL VEHICULO

                try {
                    oos = new ObjectOutputStream(s.getOutputStream());
                    oos.writeObject(datosV);

                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;

            case 4: //CONSULTA PARKINGS

                ObjectInputStream ois = null;

                try {
                    ois=new ObjectInputStream(s.getInputStream());
                    listaResultados = (ArrayList<String[]>) ois.readObject();
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }

                break;

            case 5: //CONSULTA DATOS USER

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
            case 6:   //CONSULTA DATOS DE TARJETA

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
                    String numT = dis.readUTF();
                    if(flag && !numT.equals("null")){

                        User.setNumTarjeta(numT);
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
                //MODIFICAR USER DATA (U)
                //MODIFICAR TARJETA USER (T)

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

            case 8: //CONSULTAR APODOS VEHCIULOS

                try {
                    dos.writeUTF(texto);
                } catch (IOException e) {
                    e.printStackTrace();
                }

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

            case 9: //CONSULTAR VEHICULO SEGUN APODO

                try {
                    dos.writeUTF(texto);
                } catch (IOException e) {
                    e.printStackTrace();
                }


                DataInputStream dataInputStream = null;
                try {
                    dataInputStream = new DataInputStream(s.getInputStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {

                    User.setMatricula(dataInputStream.readUTF());
                    User.setTipoVehiculo(dataInputStream.readUTF());
                    User.setMarcaVehiculo(dataInputStream.readUTF());
                    User.setModeloVehiculo(dataInputStream.readUTF());
                    User.setTamanoVehiculo(dataInputStream.readUTF());

                    //System.out.println(User.getTipoVehiculo());

                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;

            case 10: //ELIMINAR MATRICULA

                try {
                    dos.writeUTF(texto);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            case 11: //CONSULTAR PLAZAS DE UN PARKING

                try {
                    dos.writeUTF(direccion);
                    dos.writeUTF(vehiculo);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                ObjectInputStream oisPlazas = null;

                try {
                    oisPlazas =new ObjectInputStream(s.getInputStream());
                    listaPlazasParkin = (ArrayList<String>) oisPlazas.readObject();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

            case 12:

                try {
                    oos = new ObjectOutputStream(s.getOutputStream());
                    oos.writeObject(datosR);
                } catch (IOException e) {
                    e.printStackTrace();
                }

               break;
        }

    }

}
