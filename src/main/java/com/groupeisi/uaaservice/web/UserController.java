package com.groupeisi.uaaservice.web;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    private String dbname;

    public UserController(@Value("${dbname}") String dbname) {
        this.dbname = dbname;
    }

    @GetMapping("/username")
    public String getUserName(){
        return "mail@groupeisi.com";
    }
    /**
     * Ce endpoint recupere la config du microservice via le micro service de confog
     * @return : la valeur de la propriete dbname qui est definie dans la config via lapi gateway http://localhost:8889/CLIENT-SERVICE/clients/config
     */
    @GetMapping("/config")
    public ResponseEntity<Map<String, Object>> getConfig() {
        Map<String, Object> params = new HashMap<>();
        params.put("dbanameParam", dbname);
// Un thread est sous process du process parent : en ajoutant @RefreshScope
        params.put("threadName", Thread.currentThread().getName());
        //actuatorRefresh();
        return new ResponseEntity<>(params, HttpStatus.OK);
    }
}
