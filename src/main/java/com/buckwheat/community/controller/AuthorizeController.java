package com.buckwheat.community.controller;

import com.buckwheat.community.dto.AccessTokenDto;
import com.buckwheat.community.dto.GithubUser;
import com.buckwheat.community.provider.GithubProdiver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {
    @Autowired
    private GithubProdiver prodiver;
    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri;
    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code")String code,@RequestParam(name = "state")String state){
        AccessTokenDto accessTokenDto = new AccessTokenDto();
        //24d06a0f2c14a2758744
        accessTokenDto.setClient_id(clientId);
        //b039258cbdda34e6bf937526881827d3435d03f86
        accessTokenDto.setClient_secret(clientSecret);
        accessTokenDto.setCode(code);
        accessTokenDto.setState(state);
        accessTokenDto.setRedirect_uri(redirectUri);
        System.out.println(accessTokenDto);
        String accessToken = prodiver.getAccessToken(accessTokenDto);
        System.out.println(accessToken);
        GithubUser user = prodiver.getGithubUser(accessToken);
        System.out.println(user.getName());
        return "index";
    }
}
