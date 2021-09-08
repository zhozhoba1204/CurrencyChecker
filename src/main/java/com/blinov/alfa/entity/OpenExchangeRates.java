package com.blinov.alfa.entity;

import java.util.Map;


public class OpenExchangeRates {
    private String disclaimer;
    private String licence;
    private Integer timestamp;
    private String base;
    private Map<String, Double> rates;

    public OpenExchangeRates() {
    }
    public OpenExchangeRates(String disclaimer, String licence, Integer timestamp, String base, Map<String, Double> rates) {
        this.disclaimer = disclaimer;
        this.licence = licence;
        this.timestamp = timestamp;
        this.base = base;
        this.rates = rates;
    }

    public String getDisclaimer() {
        return disclaimer;
    }

    public void setDisclaimer(String disclaimer) {
        this.disclaimer = disclaimer;
    }

    public String getLicence() {
        return licence;
    }

    public void setLicence(String licence) {
        this.licence = licence;
    }

    public Integer getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Map<String, Double> getRates() {
        return rates;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }
}
