package com.denesgarda.CurrencyConverter;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.util.Timer;
import java.util.TimerTask;

public class FlowConverter extends Converter {
    private boolean running;
    private final FetchConverter base;
    private int refreshRate;
    private boolean fetchOverride;
    private Currency[][] pattern;
    private double[] values;
    private Timer timer;
    private final TimerTask refresher;

    public FlowConverter(Currency[][] pattern, int refreshRate, boolean fetchOverride) {
        super();
        running = false;
        base = new FetchConverter();
        this.refreshRate = refreshRate;
        this.fetchOverride = fetchOverride;
        this.pattern = pattern;
        for (Currency[] set : pattern) {
            if (set.length != 2) {
                throw new IndexOutOfBoundsException("Each set of the pattern must have exactly two values.");
            }
        }
        values = new double[pattern.length];
        refresher = new TimerTask() {
            @Override
            public void run() {
                for (int i = 0; i < pattern.length; i++) {
                    try {
                        double value = base.getExchangeRate(pattern[i][0], pattern[i][1]);
                        values[i] = value;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
    }

    public double convert(double before, Currency from, Currency to) throws IOException {
        if (running) {
            boolean found = false;
            double result = 0;
            for (int i = 0; i < pattern.length; i++) {
                if (pattern[i][0] == from && pattern[i][1] == to) {
                    found = true;
                    result = values[i];
                }
            }
            if (!found) {
                if (fetchOverride) {
                    return base.convert(before, from, to);
                } else {
                    throw new InvalidObjectException("Unable to convert. The set wasn't provided in the arguments of the flow converter.");
                }
            }
            return result;
        } else {
            throw new IllegalStateException("Converter is not running.");
        }
    }

    public void allowFetchOverride(boolean fetchOverride) {
        this.fetchOverride = fetchOverride;
    }

    public int getRefreshRate() {
        return refreshRate;
    }

    public void setRefreshRate(int refreshRate) {
        this.refreshRate = refreshRate;
    }

    public void start() {
        running = true;
        timer = new Timer();
        timer.scheduleAtFixedRate(refresher, 0, refreshRate);
    }

    public void stop() {
        running = false;
        timer.cancel();
        timer = null;
    }

    public void refresh() {
        refresher.run();
    }
}
