package ru.mentee.power;

public class ProgressDemo {
    public static void main(String[] args) {

        System.out.printf("Hello and welcome!%n");

        MenteeProgress progress = new MenteeProgress("Laura", 1, 6);

        System.out.println(progress.summary());


        if (progress.readyForSprint()) {
            System.out.println("Status: sprint ready");
        } else {
            System.out.println("Status: backlog first");
        }


        System.out.println("Working in feature/DVT-3");

    }
}