package com.profile.protection.admin.resource;

public class DecryptValueRequest {

    private String cipherText;
    private DataClassType dataClass;

    public String getCipherText() {
        return cipherText;
    }

    public void setCipherText(String cipherText) {
        this.cipherText = cipherText;
    }

    public DataClassType getDataClass() {
        return dataClass;
    }

    public void setDataClass(DataClassType dataClass) {
        this.dataClass = dataClass;
    }
}
