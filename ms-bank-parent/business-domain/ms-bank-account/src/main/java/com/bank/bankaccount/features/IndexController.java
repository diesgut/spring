package com.bank.bankaccount.features;

import com.bank.bankaccount.common.constants.ApiConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(ApiConstants.API_V1_VERSION)
public class IndexController {

    private final Environment env;

    @GetMapping("/check")
    public String check() {
        return "Hello your proerty value is: " + env.getProperty("activeProfileName");
    }

}
