package com.ice.androidpractice09_recyclerview;

public class Contact {
    private String name;
    private int imageId;
    public Contact(String name, int imageId){
        this.name = name;
        this.imageId = imageId;
    }

    public String getName(){
        return name;
    }

    public int getImageId(){
        return imageId;
    }

}
