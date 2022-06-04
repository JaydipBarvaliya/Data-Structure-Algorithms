package com.serialization;

import java.io.*;

public class SerializedChildWithoutParentBeingSerializable {

    public static void main(String[] args) throws Exception {
        Activa activa = new Activa();

        // Serialization
        System.out.println("----------------------------------Serialization Started----------------------------------");
        FileOutputStream fos = new FileOutputStream("activa.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(activa);


        // Deserialization with Instance Control flow from JVM
        // 1) Identification of instance variable in Non-Serializable Parent Class
        // 2) Execution of Instance variable assignment and initialization blocks
        // 3) Execution of Constructor
        System.out.println("----------------------------------Deserialization Started----------------------------------");
        FileInputStream fis = new FileInputStream("activa.ser");
        ObjectInputStream ois = new ObjectInputStream(fis);
        Activa readActiva = (Activa) ois.readObject();

        System.out.println("Before Serialization " +activa);
        System.out.println("After Serialization " +readActiva);
    }
}


class Vehicle {
    int i = 10;

    // Mandatory No-arg constructor should be there in every Non-Serializable parent class
//    Vehicle(int k) {
//        System.out.println("Vehicle Constructor Called");
//    }

    Vehicle() {
        System.out.println("Vehicle Constructor Called");
    }
}

// Parent is Not serializable but Chid is
class Activa extends Vehicle implements Serializable {
    int j = 999;

    Activa() {
//        super(10);
        System.out.println("Activa Constructor Called");
    }

    public String toString() {
        return "Activa: [i=" + super.i + ", j=" + j + "]";
    }
}