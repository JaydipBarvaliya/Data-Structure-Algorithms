package com.serialization;

import java.io.*;

public class SerializationWithInheritance {

    public static void main(String[] args) throws Exception {
        Dog object = new Dog();

        // Serialization
        FileOutputStream fos = new FileOutputStream("abc.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(object);

        // Deserialization
        FileInputStream fis = new FileInputStream("abc.ser");
        ObjectInputStream ois = new ObjectInputStream(fis);
        Dog object2 = (Dog) ois.readObject();

        System.out.println(object.i + "::::" + object.j);

        System.out.println("Serialized Object");
        System.out.println(object2.i + "::::" + object2.j);
    }
}

abstract class Animal implements Serializable {
    int i = 10;
    int j = 20;
}

class Dog extends Animal {
}