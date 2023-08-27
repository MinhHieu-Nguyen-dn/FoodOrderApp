package com.example.model;


public class Order {

    String inputName;
    String inputAddress;
    String inputCity;
    String inputState;
    String inputZip;
    String inputCardNumber;
    String inputCardExpiry;
    String inputCardPin;
    String tvSubtotalAmount;
    String tvDeliveryChargeAmount;
    String tvDeliveryCharge;
    String tvTotalAmount;

    public Order() {
    }

    public Order(String inputName, String inputAddress, String inputCity, String inputState, String inputZip, String inputCardNumber, String inputCardExpiry, String inputCardPin, String tvSubtotalAmount, String tvDeliveryChargeAmount, String tvDeliveryCharge, String tvTotalAmount) {
        this.inputName = inputName;
        this.inputAddress = inputAddress;
        this.inputCity = inputCity;
        this.inputState = inputState;
        this.inputZip = inputZip;
        this.inputCardNumber = inputCardNumber;
        this.inputCardExpiry = inputCardExpiry;
        this.inputCardPin = inputCardPin;
        this.tvSubtotalAmount = tvSubtotalAmount;
        this.tvDeliveryChargeAmount = tvDeliveryChargeAmount;
        this.tvDeliveryCharge = tvDeliveryCharge;
        this.tvTotalAmount = tvTotalAmount;
    }

    public String getInputName() {
        return inputName;
    }

    public void setInputName(String inputName) {
        this.inputName = inputName;
    }

    public String getInputAddress() {
        return inputAddress;
    }

    public void setInputAddress(String inputAddress) {
        this.inputAddress = inputAddress;
    }

    public String getInputCity() {
        return inputCity;
    }

    public void setInputCity(String inputCity) {
        this.inputCity = inputCity;
    }

    public String getInputState() {
        return inputState;
    }

    public void setInputState(String inputState) {
        this.inputState = inputState;
    }

    public String getInputZip() {
        return inputZip;
    }

    public void setInputZip(String inputZip) {
        this.inputZip = inputZip;
    }

    public String getInputCardNumber() {
        return inputCardNumber;
    }

    public void setInputCardNumber(String inputCardNumber) {
        this.inputCardNumber = inputCardNumber;
    }

    public String getInputCardExpiry() {
        return inputCardExpiry;
    }

    public void setInputCardExpiry(String inputCardExpiry) {
        this.inputCardExpiry = inputCardExpiry;
    }

    public String getInputCardPin() {
        return inputCardPin;
    }

    public void setInputCardPin(String inputCardPin) {
        this.inputCardPin = inputCardPin;
    }

    public String getTvSubtotalAmount() {
        return tvSubtotalAmount;
    }

    public void setTvSubtotalAmount(String tvSubtotalAmount) {
        this.tvSubtotalAmount = tvSubtotalAmount;
    }

    public String getTvDeliveryChargeAmount() {
        return tvDeliveryChargeAmount;
    }

    public void setTvDeliveryChargeAmount(String tvDeliveryChargeAmount) {
        this.tvDeliveryChargeAmount = tvDeliveryChargeAmount;
    }

    public String getTvDeliveryCharge() {
        return tvDeliveryCharge;
    }

    public void setTvDeliveryCharge(String tvDeliveryCharge) {
        this.tvDeliveryCharge = tvDeliveryCharge;
    }

    public String getTvTotalAmount() {
        return tvTotalAmount;
    }

    public void setTvTotalAmount(String tvTotalAmount) {
        this.tvTotalAmount = tvTotalAmount;
    }
}
