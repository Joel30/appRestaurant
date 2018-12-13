package com.example.joel.apprestaurant.items;

public class ItemListRestaurant {
    //private String idrestaurant;
    private String picture;
    private String name;

    public ItemListRestaurant (String picture, String name){
        this.name = name;
        this.picture = picture;
    }

    public String getPicture(){
        return this.picture;
    }

    public String getName(){
        return this.name;
    }
}
