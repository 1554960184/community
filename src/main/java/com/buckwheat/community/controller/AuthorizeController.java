package com.buckwheat.community.controller;

import com.buckwheat.community.dto.AccessTokenDto;
import com.buckwheat.community.provider.GithubProdiver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {
    @Autowired
    private GithubProdiver prodiver;
    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code")String code,@RequestParam(name = "state")String state){
        AccessTokenDto accessTokenDto = new AccessTokenDto();
        //24d06a0f2c14a2758744
        accessTokenDto.setClient_id("24d06a0f2c14a2758744");
        //b039258cbdda34e6bf937526881827d3435d03f86
        accessTokenDto.setClient_secret("b039258cbdda34e6bf937526881827d3435d03f8");
        accessTokenDto.setCode(code);
        accessTokenDto.setState(state);
        accessTokenDto.setRedirect_uri("http://loaclhost:8889/callback");
        System.out.println(accessTokenDto);
        prodiver.getAccessToken(accessTokenDto);
        return "index";
    }
}
