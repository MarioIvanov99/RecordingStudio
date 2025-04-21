package com.studio;

import com.studio.model.RecordingStudio;
import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        try {
            RecordingStudio studio1 = new RecordingStudio(101, new BigDecimal("25.50"), 10);
            studio1.setRentedHours(5);
            System.out.println(studio1);

            RecordingStudio studio2 = new RecordingStudio(102, new BigDecimal("15.00"), 8);
            studio2.setRentedHours(6);
            System.out.println(studio2);

            int comparison = studio1.compareTo(studio2);
            if (comparison > 0) {
                System.out.println("Studio 1 has higher income.");
            } else if (comparison < 0) {
                System.out.println("Studio 2 has higher income.");
            } else {
                System.out.println("Both studios have the same income.");
            }

            studio1.setRentedHours(11);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}