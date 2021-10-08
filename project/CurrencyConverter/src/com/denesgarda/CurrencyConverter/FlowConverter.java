package com.denesgarda.CurrencyConverter;

import java.util.Timer;
import java.util.TimerTask;

public class FlowConverter extends Converter {
    private int refreshRate;
    private Currency[][] pattern;
    private double[][] values;
    private Timer timer;
    private TimerTask refresher;

    public FlowConverter(Currency[][] pattern, int refreshRate) {
        super();
        this.refreshRate = refreshRate;
        this.pattern = pattern;
        for (Currency[] set : pattern) {
            if (set.length != 2) {
                throw new IndexOutOfBoundsException("Each set of the pattern must have exactly two values.");
            }
        }
        values = new double[pattern.length][2];
        refresher = new TimerTask() {
            @Override
            public void run() {
                // Refresh all values
            }
        };
    }

    public int getRefreshRate() {
        return refreshRate;
    }

    public void setRefreshRate(int refreshRate) {
        this.refreshRate = refreshRate;
    }

    public void start() {
        timer = new Timer();
        timer.scheduleAtFixedRate(refresher, 0, refreshRate);
    }

    public void stop() {
        timer.cancel();
        timer = null;
    }

    public void refresh() {
        refresher.run();
    }
}
