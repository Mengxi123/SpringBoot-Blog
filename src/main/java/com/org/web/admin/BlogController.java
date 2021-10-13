package com.org.web.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Create by MengXi on 2021/10/13 15:37.
 */
@Controller
@RequestMapping("/admin")
public class BlogController {

    @GetMapping("/blog")
    public String blogs() {
        return "admin/blog";
    }
}
