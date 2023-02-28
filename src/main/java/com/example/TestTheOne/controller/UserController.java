package com.example.TestTheOne.controller;

import com.example.TestTheOne.config.UserAPIProperties;
import com.example.TestTheOne.model.ResUser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;

@RestController
@RequestMapping("/v1")
public class UserController {

    @Autowired
    private UserAPIProperties userAPIProperties;

    @GetMapping("users/{seed}")
    public ResUser getUsers (@PathVariable("seed") String seed) throws IOException {

        ResUser resUser = new ResUser();
        String url = userAPIProperties.getUrl() + "?seed=" + seed;
        JsonNode json = new ObjectMapper().readTree(new URL(url));
        resUser.setGender(removeText(json.get("results").get(0).get("gender").toString()));
        resUser.setFirstName(removeText(json.get("results").get(0).get("name").get("first").toString()));
        resUser.setLastName(removeText(json.get("results").get(0).get("name").get("last").toString()));
        resUser.setEmail(removeText(json.get("results").get(0).get("email").toString()));

        return resUser;
    }

    private String removeText (String str) {
        if (str != null) {
            return str.replace("\"", "");
        } else {
            return "";
        }
    }
}
