package com.company.amazon;

public class AngleBetweenHourHandAndMinuteHand {

    public static void main(String[] args) {
        String[] time = {"9:60", "3:30", "4:49", "12:45", "12:60", "12:00", "06:00"};
        for (String str : time) {
            String[] split = str.split(":");
            System.out.println("Angle Between " + str + " is: " + getAngle(Integer.parseInt(split[0]), Integer.parseInt(split[1])));
        }
    }

    public static int getAngle(int hour, int minute) {
        int hour_angle = (hour % 12) * 30; // In 1 hour, hour hand moves 30 degree
        // We have taken Mod because at 12:00 the hour hand make 0 degrees
        // So (12 % 12) * 30 = 0; otherwise without mode 12 * 30 = 360 degree which is wrong.

        int minute_angle = (minute % 60) * 6; // In 1 minute, minute hand moves 6 degrees, Same mode(%) concept here
        // let's say time is (09:60) So minute will be (60 % 60) * 6
        // and In 1 hour minute moves = 360 degree
        // So 60 min = 360 degree; 1 min = 6 degree

        // Now when minute moves so hour must have sweep a little bit.
        // 1 hour = 30 degree
        // 60 minute = 30 degree
        // 1 minute = 30/60 degree
        // 1 minute = 0.5 degree.
        int hour_with_respect_to_minute = (minute % 60) * 30 / 60;

        int diff = Math.abs(hour_angle + hour_with_respect_to_minute - minute_angle);

        return Math.min(diff, 360 - diff);
    }
}
