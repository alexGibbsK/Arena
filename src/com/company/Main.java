package com.company;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by java-1-07 on 01.03.2017.
 */
public class Main {

    public static void main(String[] args) {

        Random r = new Random();
        int id = 1;
        int fightNum = 0;

        List<Fighter> list = new LinkedList<>();


        //Заполнение списка бойцов
        getFighterList(r, list, id);

        //Вывод списка бойцов
        System.out.println("Fighters List: ");
        for (Fighter f :
                list) {
            System.out.println(f.toString());
        }

        System.out.println("\nДа начнется битва\n");

        //Остаться должен только один (С) Горец
        while (list.size() > 1) {
            System.out.println("Fight #" + ++fightNum);
            list.add(new Arena(list.get(0), list.get(1)).fight());
            list.remove(0);
            list.remove(0);
        }

        //Вывод победителя
        System.out.println("WINNER: ");
        for (Fighter f :
                list) {
            System.out.println(f.toString());
        }

    }

    private static void getFighterList(Random r, List<Fighter> list, int id) {
        for (int i = 0; i < 10; i++) {
            list.add(new Fighter(r.nextInt(100) + 1, r.nextInt(100) + 1, r.nextInt(100) + 1, id++));
        }
    }
}
