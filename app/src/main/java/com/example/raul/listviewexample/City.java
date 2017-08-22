package com.example.raul.listviewexample;

/**
 * Created by Raul on 21/08/2017.
 */

public class City {

    private String code;
    private  String name;

    public City(String name, String code){

        this.name = name;
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name + " ";
    }
}
