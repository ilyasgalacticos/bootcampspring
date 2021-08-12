package kz.bootcamp.springboot.springBootcamp.beans;

public class SecondBean {

    private String name;
    private int number;

    public SecondBean(String name, int number) {
        System.out.println("Using parametrized constructor of Second Bean");
        this.name = name;
        this.number = number;
    }

    public SecondBean(){
        System.out.println("Using default constructor of Second Bean");
        this.name = "No Name";
        this.number = 0;
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