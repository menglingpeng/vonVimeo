package com.menglingpeng.vonvimeo.mvp.model;

public class BankCardInfo {

    private String bank;
    private String cardType;
    private String key;
    private String stat;
    private boolean validated;

    public void setBank(String bank) {
        this.bank = bank;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public void setValidated(boolean validated) {
        this.validated = validated;
    }

    public String getBank() {
        return bank;
    }

    public String getCardType() {
        return cardType;
    }

    public String getKey() {
        return key;
    }

    public String getStat() {
        return stat;
    }

    public boolean getValidated() {
        return validated;
    }
}
