package com.profile.protection.admin.resource;

public class EncryptValueRequest {

    private String plainText;
    private DataClassType dataClass;

    public String getPlainText() {
        return plainText;
    }

    public void setPlainText(String plainText) {
        this.plainText = plainText;
    }

    public DataClassType getDataClass() {
        return dataClass;
    }

    public void setDataClass(DataClassType dataClass) {
        this.dataClass = dataClass;
    }
}
