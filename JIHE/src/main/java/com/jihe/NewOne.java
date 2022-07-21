package com.jihe;

import com.entity.Book;
import com.entity.Dog;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * @author wangkx
 */
public class NewOne {
    @SuppressWarnings({"all"})
    public static void main(String[] args) {
        List<Dog> dogs = new ArrayList<>();
        dogs.add(new Dog("王珣",1));
        dogs.add(new Dog("王垚",2));
        dogs.add(new Dog("晋港",3));
        for (int i = 0; i < dogs.size(); i++) {
            System.out.println(dogs.remove(i));
        }

    }
    public static void sort( ){

    }

}

