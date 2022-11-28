package com.example.milkyway;

public class ListData {
    private String imgUrl;
    private String link;
    private String place;
    private String descript;
    public ListData(String imgUrl, String link, String place, String descript){
        this.imgUrl = imgUrl;
        this.link = link;
        this.place = place;
        this.descript = descript;
    }

    public void setImgUrl(String imgUrl){
        this.imgUrl = imgUrl;
    }
    public void setLink(String link){
        this.link = link;
    }
    public void setPlace(String place){
        this.link = place;
    }
    public void setDescript(String descript){
        this.link = descript;
    }
    public String getImgUrl(){
        return imgUrl;
    }
    public String getLink(){
        return link;
    }
    public String getPlace(){
        return place;
    }
    public String getDescript(){
        return descript;
    }
}
