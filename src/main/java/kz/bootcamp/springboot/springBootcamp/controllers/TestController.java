package kz.bootcamp.springboot.springBootcamp.controllers;

import kz.bootcamp.springboot.springBootcamp.beans.FirstBean;
import kz.bootcamp.springboot.springBootcamp.beans.SecondBean;
import kz.bootcamp.springboot.springBootcamp.beans.TwitterBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/test")
public class TestController {

    @Autowired
    private FirstBean firstBean;

    @Autowired
    @Qualifier("almasBean")
    private SecondBean secondBean;

    @Autowired
    @Qualifier("ilyasBean")
    private SecondBean theSecondBean;

    @Autowired
    @Qualifier("oracleBean")
    private TwitterBean twitterBean;

    @GetMapping(value = "/totest")
    public String toTest(Model model){
//        String text = firstBean.getData();
        String text = theSecondBean.getName() + " - " + theSecondBean.getNumber();

        String userText = twitterBean.getUserData() + " " + twitterBean.getUserAge();
        model.addAttribute("userText", userText);
        model.addAttribute("text", text);
        return "test";
    }

}
