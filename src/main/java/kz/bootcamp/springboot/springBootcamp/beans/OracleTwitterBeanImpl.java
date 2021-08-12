package kz.bootcamp.springboot.springBootcamp.beans;

public class OracleTwitterBeanImpl implements TwitterBean {

    private String userData;

    @Override
    public String getUserData() {
        return userData;
    }

    @Override
    public int getUserAge() {
        return 25;
    }

    public void setUserData(String userData) {
        this.userData = userData;
    }
}
