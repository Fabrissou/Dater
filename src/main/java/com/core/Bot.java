package com.core;

import java.text.SimpleDateFormat;
import java.util.Arrays;

public class Bot {

    private Seed ourSeed = Seed.Human;
    private Seed oppSeed = Seed.Bot;

    static class DayAndMonth {
        private int day;
        private int month;

        public DayAndMonth(int day, int month) {
            this.day = day;
            this.month = month;
        }

        public DayAndMonth(String date) {
            String[] dates = date.split("\\.");
            this.day = Integer.parseInt(dates[0]);
            this.month = Integer.parseInt(dates[1]);
        }

        public int getDay() {
            return day;
        }

        public int getMonth() {
            return month;
        }

        public void dayPlusOne() {
            day += 1;
        }

        public void dayPlusTwo() {
            day += 2;
        }

        public void monthPlusOne() {
            month += 1;
        }

        public void monthPlusTwo() {
            month += 2;
        }

        @Override
        public String toString() {
            return day + "." + month;
        }
    }
//проверка даты
    private boolean checkDate(DayAndMonth date) {
        boolean check = true;
        SimpleDateFormat newFormat = new SimpleDateFormat("dd.MM.yyyy");
        newFormat.setLenient(false);
        try {
            newFormat.parse(date + "." + "2022");
            return true;
        } catch (Exception e) {
            return false;
        }
    }

//данный метод находит лучший ход для бота. Возвращает 0 - день + 1, 1 - день + 2, 2 - месяц + 1, 3 - месяц + 2;
    public int findOptimalMovement(int day, int month) {
        DayAndMonth dayAndMonth = new DayAndMonth(day, month);
        Seed seed = Seed.Bot;
        int[] dates = new int[4];
        dates[0] = getScore(new DayAndMonth(dayAndMonth.getDay() + 1, dayAndMonth.getMonth()), seed, 8);
        dates[1] = getScore(new DayAndMonth(dayAndMonth.getDay() + 2, dayAndMonth.getMonth()), seed, 8);
        dates[2] = getScore(new DayAndMonth(dayAndMonth.getDay(), dayAndMonth.getMonth() + 1), seed, 8);
        dates[3] = getScore(new DayAndMonth(dayAndMonth.getDay(), dayAndMonth.getMonth() + 2), seed, 8);
        int maxI = -99;
        int max = -99;

        for (int i = 0; i < 4; i++) {
            if (dates[i] > max) {
                max = dates[i];
                maxI = i;
            }
        }

        return maxI;
    }

//метод возвращает количество очков для даты. Условно он считает кол-во побед для заданной даты, а метод который его вызывает,
//    выбирает максимально выгодный ход
    int getScore(DayAndMonth date, Seed seed, int depth) {
        if (checkDate(date)) {
            int currentScore = 0;

            if (depth == 0) {
                return currentScore;
            } else {
                DayAndMonth[] dateList = new DayAndMonth[4];
                dateList[0] = new DayAndMonth(date.getDay() + 1, date.getMonth());
                dateList[1] = new DayAndMonth(date.getDay() + 2, date.getMonth());
                dateList[2] = new DayAndMonth(date.getDay(), date.getMonth() + 1);
                dateList[3] = new DayAndMonth(date.getDay(), date.getMonth() + 2);

                int numberOfValidDates = 0;
                for (DayAndMonth d: dateList) {
                    if (checkDate(d)) {
                        numberOfValidDates++;
                    }
                }

                for (int i = 0; i < 4; i++) {
                    if (checkDate(dateList[i])) {
                        boolean win = dateList[i].toString().equals("31.12");

                        if (seed == ourSeed) {  // Если сыграл ourSeed
                            if (win) {
                                if (numberOfValidDates == 1) {
                                    currentScore--;
                                }
                            }
                            currentScore += getScore(dateList[i], oppSeed, depth - 1);
                        } else {  // Если сыграл oppSeed
                            if (win) {
                                if (numberOfValidDates == 1) {
                                    currentScore++;
                                }
                            }
                            currentScore += getScore(dateList[i], ourSeed, depth - 1);
                        }
                    }
                }
                return currentScore;
            }
        }

        return -100;
    }

}
