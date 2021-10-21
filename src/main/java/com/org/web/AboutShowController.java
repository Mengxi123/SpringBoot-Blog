package com.org.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Create by MengXi on 2021/10/21 17:42.
 */
@Controller
public class AboutShowController {
    @GetMapping("/about")
    public String about() {
        return "about";
    }
}
