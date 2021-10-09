package com.org.web;

import com.org.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author MengXi
 */
@Controller
public class IndexController {
    @GetMapping("/")
    public String index() {
//        int i = 1 / 0;
        String blog = null;
        if (blog == null) {
            throw new NotFoundException("博客不存在");
        }
        return "index";
    }
}
