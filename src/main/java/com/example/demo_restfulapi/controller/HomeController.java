package com.example.demo_restfulapi.controller;

import com.example.demo_restfulapi.controller.services.RequestService;
import com.example.demo_restfulapi.models.Account;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    private static int count = 0;
    @GetMapping(value = {"/login","/","index"})
    public ModelAndView showLogin(){
        return new ModelAndView("login");
    }

    @PostMapping(value = {"/login","/"})
    public ModelAndView handleLogin(@RequestParam(name="username") String username,
                                    @RequestParam(name="password") String password){
        ModelAndView mv = new ModelAndView("/login");
        Account acc = new Account(username, password);
        String rs = RequestService.requestLogin(acc);
        System.out.println(String.format("\n##%d##\n%s\n####\n",count++,rs));

        if(rs!=null){
            return new ModelAndView("/sign");
        }
        else
            mv.addObject("alert",false);

        return mv;
    }

    @GetMapping("/sign")
    public String showSignPage(){
        return "sign";
    }
}
