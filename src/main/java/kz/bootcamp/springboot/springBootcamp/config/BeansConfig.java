package kz.bootcamp.springboot.springBootcamp.config;

import kz.bootcamp.springboot.springBootcamp.beans.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfig {

    @Bean(name = "ilyasBean")
    public SecondBean secondBean(){
        return new SecondBean();
    }

    @Bean(name = "almasBean")
    public SecondBean theSecondBean(){
        return new SecondBean("Almas", 333);
    }

    @Bean(name = "normalBean")
    public TwitterBean getNormalBean(){
        return new TwitterBeanImp();
    }

    @Bean(name = "oracleBean")
    public TwitterBean getOracleBean(){
        OracleTwitterBeanImpl twitterBean = new OracleTwitterBeanImpl();
        twitterBean.setUserData("Oracle User");
        return twitterBean;
    }

    @Bean(name = "testItemBean")
    public ItemManager itemManager(){
        return new ItemManagerImpl();
    }

    @Bean(name = "kirillBean")
    public ItemManager itemManageFromKirill(){
        return new MySqlKirillItemManager();
    }

    @Bean(name = "dbBean")
    public DatabaseBean databaseBean(){
        return new DatabaseBean();
    }

}
