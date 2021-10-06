package com.denesgarda.CurrencyConverter;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class FetchConverter extends Converter {
    public FetchConverter() {
        super();
    }

    public double convert(double before, Currency from, Currency to) throws IOException {
        URLConnection connection = new URL("https://www.google.com/search?q=" + from.toString().toLowerCase() + "+to+" + to.toString().toLowerCase() + "+exchange+rate").openConnection();
        return 0;
    }
}
