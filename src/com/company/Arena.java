package com.company;

import java.util.Random;

/**
 * Created by Alex on 3/1/2017.
 */
public class Arena {
    Fighter f1;
    Fighter f2;
    //Урон наносимый бойцом(рассчет идет во время удара)
    int damage;
    Random r = new Random();

    //Свитч для реализации очередности ударов
    int fighterSwitch;

    public Arena(Fighter f1, Fighter f2) {
        this.f1 = f1;
        this.f2 = f2;
    }


    public Fighter fight() {
        System.out.println(this.f1.toString());
        System.out.println("VS");
        System.out.println(this.f2.toString() + "\n");

        //Реализация первого удара
        if (r.nextDouble() > 0.5) {
            f1Hit();
            this.fighterSwitch = 0;
        } else {
            f2Hit();
            this.fighterSwitch = 1;
        }

        //Последующий замес
        while (this.f1.getHp() > 0 && this.f2.getHp() > 0) {
            if (fighterSwitch == 0) {
                f2Hit();
            } else {
                f1Hit();
            }
        }

        System.out.println("Figter1 hp: " + this.f1.getHp());
        System.out.println("Figter2 hp: " + this.f2.getHp());
        System.out.println("\n----------------------------\n");

        //Возвращение победителя в замесе обратно в лист
        if (this.f1.getHp() > 0) {
            f1.setHp(100);
            return f1;
        } else {
            f2.setHp(100);
            return f2;
        }
    }

    //Реализация удара для Fighter 1
    public void f1Hit() {
        this.damage = r.nextInt(f1.getStr()) + 1;
        //Реализация крита;
        if (r.nextDouble() < (double) (f1.getPer() / 100)) {
            System.out.println((char) 27 + "[31mCRIT " + (char) 27 + "[0m");
            this.f2.hp -= (this.damage * 2);
            System.out.println("CRIT F1 hit F2 for: " + this.damage * 2 + " HP");
        }
        //Реализация уворота
        else if (r.nextDouble() < (double) (f2.getDex() / 100)) {
            System.out.println((char) 27 + "[31mDODGE " + (char) 27 + "[0m");
            this.f2.hp -= (this.damage * 0.2);
            System.out.println("DODGE F1 hit F2 for: " + this.damage * 0.2 + " HP");
        }
        //Реализация обычного удара
        else {
            this.f2.hp -= this.damage;
            System.out.println("NORMAL F1 hit F2 for: " + this.damage + " HP");
        }
        this.fighterSwitch = 0;
    }

    //Реализация удара для Fighter 2
    public void f2Hit() {
        this.damage = r.nextInt(f2.getStr()) + 1;
        //Реализация крита;
        if (r.nextDouble() < (double) (f2.getPer() / 100)) {
            System.out.println((char) 27 + "[31mCRIT " + (char) 27 + "[0m");
            this.f1.hp -= (this.damage * 2);
            System.out.println("CRIT F2 hit F1 for: " + this.damage * 2 + " HP");
        }
        //Реализация уворота с нанесением 20% DMG
        else if (r.nextDouble() < (double) (f1.getDex() / 100)) {
            System.out.println((char) 27 + "[31mDODGE " + (char) 27 + "[0m");
            this.f1.hp -= (this.damage * 0.2);
            System.out.println("DODGE F2 hit F1 for: " + this.damage * 0.2 + " HP");
        }//Реализация обычного удара
        else {
            this.f1.hp -= this.damage;
            System.out.println("NORMAL F2 hit F1 for: " + this.damage + " HP");
        }
        this.fighterSwitch = 1;
    }

}
