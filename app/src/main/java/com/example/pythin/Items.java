package com.example.pythin;

public class Items {
    public String data;
    public int update;
    public String Time;
    public String Mature;
    public String ImMature;
    public String TotalImages;
    public String harvest;
    public String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String status;

    public String getHarvest() {
        return harvest;
    }

    public void setHarvest(String harvest) {
        this.harvest = harvest;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getUpdate() {
        return update;
    }

    public void setUpdate(int update) {
        this.update = update;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getMature() {
        return Mature;
    }

    public void setMature(String mature) {
        Mature = mature;
    }

    public String getImMature() {
        return ImMature;
    }

    public void setImMature(String imMature) {
        ImMature = imMature;
    }

    public String getTotalImages() {
        return TotalImages;
    }

    public void setTotalImages(String totalImages) {
        TotalImages = totalImages;
    }

    public Items(String data, int update, String time, String mature, String imMature, String totalImages,String her,String sta,String k) {
        this.data = data;
        this.key=k;
        this.status=sta;
        this.harvest=her;
        this.update = update;
        Time = time;
        Mature = mature;
        ImMature = imMature;
        TotalImages = totalImages;
    }
}
