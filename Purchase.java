package org.example.financecalcrestr;

import java.time.LocalDate;

public class Purchase {
    //Этот класс содержит поля: дату, название, цену и тип покупки


    private LocalDate date;
    private String name;
    private double price;
    private String type;


    public Purchase(short day, short month, short year, String name, double price, String type){
        this.date = LocalDate.of(year, month, day);
        this.name = name;
        this.price = price < 0 ? 0 : price;
        this.type = type;
    }
    public Purchase(LocalDate date, String name, double price, String type){
        this.date = date;
        this.name = name;
        this.price = price < 0 ? 0 : price;
        this.type = type;
    }


    public String toString(){
        return name;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public double getPrice(){
        return price;
    }
    public void setPrice(double price){
        this.price = price < 0 ? 0 : price;
    }
    public LocalDate getDate(){
        return date;
    }
    public String getType(){
        return type;
    }


}
