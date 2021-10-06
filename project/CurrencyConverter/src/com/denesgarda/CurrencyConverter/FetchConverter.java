package com.denesgarda.CurrencyConverter;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class FetchConverter extends Converter {
    public FetchConverter() {
        super();
    }

    public double convert(double before, Currency from, Currency to) throws IOException {
        System.setProperty("http.agent", "Chrome");
        Scanner scanner = new Scanner(new URL("https://www.google.com/search?q=" + from.toString().toLowerCase() + "+to+" + to.toString().toLowerCase() + "+exchange+rate").openStream());
        double rate = 0;
        while(scanner.hasNext()) {
            String line = scanner.nextLine();
            if (line.contains("<div><div class=\"BNeawe iBp4i AP7Wnd\">")) {
                int begin = line.indexOf("<div><div class=\"BNeawe iBp4i AP7Wnd\">") + 76;
                String rateString = line.substring(begin, begin + 4);
                rate = Double.parseDouble(rateString);
            }
        }
        return before * rate;
    }

    public double getExchangeRate(Currency from, Currency to) throws IOException {
        System.setProperty("http.agent", "Chrome");
        Scanner scanner = new Scanner(new URL("https://www.google.com/search?q=" + from.toString().toLowerCase() + "+to+" + to.toString().toLowerCase() + "+exchange+rate").openStream());
        double rate = 0;
        while(scanner.hasNext()) {
            String line = scanner.nextLine();
            if (line.contains("<div><div class=\"BNeawe iBp4i AP7Wnd\">")) {
                int begin = line.indexOf("<div><div class=\"BNeawe iBp4i AP7Wnd\">") + 76;
                String rateString = line.substring(begin, begin + 4);
                rate = Double.parseDouble(rateString);
            }
        }
        return rate;
    }
}
