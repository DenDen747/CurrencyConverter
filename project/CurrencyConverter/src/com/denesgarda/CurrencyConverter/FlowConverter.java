package com.denesgarda.CurrencyConverter;

public class FlowConverter extends Converter {
    private int refreshRate;

    public FlowConverter(int refreshRate) {
        super();
        this.refreshRate = refreshRate;
    }

    public int getRefreshRate() {
        return refreshRate;
    }

    public void setRefreshRate(int refreshRate) {
        this.refreshRate = refreshRate;
    }
}
