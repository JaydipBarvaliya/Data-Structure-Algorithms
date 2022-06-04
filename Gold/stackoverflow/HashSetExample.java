package com.stackoverflow;

/* package whatever; // don't place package name! */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.TreeMap;

class Book {
    int id;
    String name, author, publisher;
    int quantity;

    public Book(int id, String name, String author, String publisher, int quantity) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.publisher = publisher;
        this.quantity = quantity;
    }

    @Override
    public int hashCode() {
        return 10;
    }

    @Override
    public boolean equals(Object obj) {
        Book b = (Book) obj;
        return this.name.equals(b.name);
    }

}

class HashSetExample {
    public static void main(String[] args) {
        HashSet<Book> set = new HashSet<Book>();
        //Creating Books
        Book b1 = new Book(101, "Let us C", "Yashwant Kanetkar", "BPB", 8);
        Book b2 = new Book(102, "Data Communications & Networking", "Forouzan", "Mc Graw Hill", 4);
        Book b3 = new Book(103, "Operating System", "Galvin", "Wiley", 6);
        Book b4 = new Book(103, "Operating System", "Galvin", "Wiley", 6);
        //Adding Books to HashSet
        set.add(b1);
        set.add(b2);
        set.add(b3);
        set.add(b4);
        //Traversing HashSet
        for (Book b : set) {
            System.out.println(b.id + " " + b.name + " " + b.author + " " + b.publisher + " " + b.quantity);
        }


        Map<String, Integer> map = new HashMap<>();
        map.put("Neerahj", 123);
        map.put("Murali", 809);
        map.put("Murali", 125);

        for(Map.Entry<String, Integer> entry: map.entrySet()) {
            System.out.println(entry.getKey()+":::"+entry.getValue());
        }

        map = new TreeMap<>();
        map.put("Ajay", 123);
        map.put("Raju", 123);
        map.put("Vijay", 123);
        map.put("Ankit", 123);

        for(Map.Entry<String, Integer> entry: map.entrySet()) {
            System.out.println(entry.getKey()+":::"+entry.getValue());
        }
    }
}