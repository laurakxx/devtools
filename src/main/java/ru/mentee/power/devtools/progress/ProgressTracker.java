package ru.mentee.power.devtools.progress;

import java.sql.SQLOutput;

public class ProgressTracker {
    public String calculateTotalProgress(Mentee[] mentees) {
        // TODO: Реализовать логику подсчёта суммарного прогресса с использованием цикла while

        if(mentees==null || mentees.length==0) {
            throw new IllegalArgumentException();
        }
        int totalCompleted=0; int totalTotal=0; int index=0;
        while(index<mentees.length) {

            totalCompleted += mentees[index].completedLessons();
            totalTotal += mentees[index].totalLessons();
            index++;
        }

        int totalRemaining = totalTotal - totalCompleted;
        return "Total: completed " + totalCompleted +
                " of " + totalTotal +
                " lessons, remaining " + totalRemaining;
    }
    public static void main(String[] args) {

        ProgressTracker tracker = new ProgressTracker();

        Mentee[] mentees = {
                new Mentee("Иван", "Москва", "Backend", 5, 12),
                new Mentee("Мария", "СПб", "Fullstack", 8, 12),
                new Mentee("Пётр", "Казань", "Java", 12, 12)
        };

        String result = tracker.calculateTotalProgress(mentees);

        System.out.println(result);
    }
}
