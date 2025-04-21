package com.studio.util;

import com.studio.exception.InvalidStudioOperationException;

public class Validator {
    public static void validateMaxRentalHours(int maxHoursPerDay) {
        if (maxHoursPerDay < 0 || maxHoursPerDay > 24) {
            throw new InvalidStudioOperationException("Max rental hours must be between 0 and 24.");
        }
    }

    public static void validateRentalHours(int maxHoursPerDay, int rentedHours) {
        if (rentedHours < 0 || rentedHours > maxHoursPerDay) {
            throw new InvalidStudioOperationException("Rented hours must be between 0 and " + maxHoursPerDay);
        }
    }
}
