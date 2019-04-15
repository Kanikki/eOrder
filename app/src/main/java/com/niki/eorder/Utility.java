package com.niki.eorder;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class Utility {
    public String toIDR(long money){
        DecimalFormat indonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols rupiah = new DecimalFormatSymbols();

        rupiah.setCurrencySymbol("IDR ");
        rupiah.setGroupingSeparator('.');
        indonesia.setMinimumFractionDigits(0);
        indonesia.setDecimalFormatSymbols(rupiah);

        return indonesia.format(money);
    }
}
