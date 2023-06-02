package org.example;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args)  {
        Main main = new Main();
        System.out.println(main.canConstruct("aab", "baa"));
    }

    public boolean canConstruct(String a, String b) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < b.length(); i++) {
            list.add(String.valueOf(b.charAt(i)));
        }

        int size = list.size();
        String result = "";
        for (int i = 0; i < list.size(); i++) {
            if(b.contains(list.get(i))){
                result = b.substring(0, i) + b.substring(i + 1);
            }
        }
        System.out.println(result);


        return result.isBlank();

    }
}