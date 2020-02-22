package ru.stqa.pft.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collection {
    public static void main(String[] args) {
        String[] lang = {"Java", "C#", "Python", "PHP"};
        for (int i=0; i<lang.length; i++ ){
            System.out.println(lang[i]);
        }


        List<String> languages = Arrays.asList("Java", "C#", "Python", "PHP");
        for (String l: languages){
            System.out.println("I wont to teach " + l);
        }


    }
}
