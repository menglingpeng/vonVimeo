package com.menglingpeng.vonvimeo.mvp.model;

public class CardPay {

    private String pictureUrl;
    private String cardName;
    private String dailyLimit;
    private Boolean checked;

    public CardPay(){

    }

    public CardPay(String pictureUrl, String cardName, String dailyLimit, Boolean checked){
        this.pictureUrl = pictureUrl;
        this.cardName = cardName;
        this.dailyLimit = dailyLimit;
        this.checked = checked;

    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getDailyLimit() {
        return dailyLimit;
    }

    public void setDailyLimit(String dailyLimit) {
        this.dailyLimit = dailyLimit;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }
}
