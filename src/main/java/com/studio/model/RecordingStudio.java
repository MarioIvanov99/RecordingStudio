package com.studio.model;

import com.studio.exception.InvalidStudioOperationException;
import com.studio.util.ConfigLoader;
import com.studio.util.Validator;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class RecordingStudio implements Comparable<RecordingStudio> {
    private final int id;
    private BigDecimal priceInBGN;
    private final int maxHoursPerDay;
    private int rentedHours;

    private static final BigDecimal minPriceInBGN = ConfigLoader.getBigDecimal("MIN_PRICE_BGN");
    private static final BigDecimal exchangeRateEURtoBGN = ConfigLoader.getBigDecimal("EXCHANGE_RATE_EUR_TO_BGN");

    public RecordingStudio(int id, BigDecimal priceInBGN, int maxHoursPerDay) {
        this.id = id;
        setPrice(priceInBGN);
        Validator.validateMaxRentalHours(maxHoursPerDay);
        this.maxHoursPerDay = maxHoursPerDay;
        this.rentedHours = 0;
    }

    public void setPrice(BigDecimal priceInBGN) {
        if (priceInBGN.compareTo(minPriceInBGN) < 0) {
            this.priceInBGN = minPriceInBGN;
        } else {
            this.priceInBGN = priceInBGN;
        }
    }

    public void setRentedHours(int rentedHours) {
        Validator.validateRentalHours(this.maxHoursPerDay, rentedHours);
        this.rentedHours = rentedHours;
    }

    public BigDecimal getIncomeInBGN() {
        return priceInBGN.multiply(new BigDecimal(rentedHours));
    }

    public BigDecimal getIncomeInEUR() {
        return getIncomeInBGN().divide(exchangeRateEURtoBGN, 2, RoundingMode.HALF_UP);
    }

    @Override
    public int compareTo(RecordingStudio other) {
        return this.getIncomeInBGN().compareTo(other.getIncomeInBGN());
    }

    public int getId() { return id; }
    public BigDecimal getPriceInBGN() { return priceInBGN; }
    public int getMaxHoursPerDay() { return maxHoursPerDay; }
    public int getRentedHours() { return rentedHours; }
    public static BigDecimal getExchangeRateEURtoBGN() { return exchangeRateEURtoBGN; }

    @Override
    public String toString() {
        return "Studio ID: " + id + ", Hourly Price: " + priceInBGN + " BGN, Max Hours: " + maxHoursPerDay +
                ", Rented Hours: " + rentedHours + ", Income: " + getIncomeInBGN() + " BGN / " +
                getIncomeInEUR() + " EUR";
    }
}
