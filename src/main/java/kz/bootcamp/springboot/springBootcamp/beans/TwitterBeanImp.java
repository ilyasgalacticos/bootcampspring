package kz.bootcamp.springboot.springBootcamp.beans;

public class TwitterBeanImp implements TwitterBean {

    @Override
    public String getUserData() {
        return "This is Ilyas";
    }

    @Override
    public int getUserAge() {
        return 32;
    }
}
