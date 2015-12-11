package com.mycompany.licit.controllers;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class HomeController {

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String index() {
        return "html/basicauth.html";
    }
    
    @RequestMapping("/user")
    @ResponseBody
    public String user() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(auth);
        } catch (JsonGenerationException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        } catch (JsonMappingException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
    }
}
