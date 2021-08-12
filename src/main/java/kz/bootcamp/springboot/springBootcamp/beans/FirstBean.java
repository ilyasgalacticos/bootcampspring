package kz.bootcamp.springboot.springBootcamp.beans;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
//@Scope("prototype")
public class FirstBean {

    private String name;
    private int number;

    public FirstBean(){
        this.name = "Ilyas";
        this.number = 3456;
        System.out.println("Using Default Constructor of First Bean");
    }

    public String getData(){
        return this.name + " - " + this.number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}