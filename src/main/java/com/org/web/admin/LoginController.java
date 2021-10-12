package com.org.web.admin;

import com.org.po.User;
import com.org.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * @author Create by MengXi on 2021/10/12 16:20.
 * @Controller 用于表现层的注解，其就是@Component的别名，将资源交给spring进行管理
 * @RequestParam 参数绑定注解
 *       参数：  String value  名称的别名
 *              String name  要绑定到的请求参数的名称。
 *              boolean required 是否需要该参数。 默认值为true，如果请求中缺少参数，将引发异常。
 *              String defaultValue  当未提供请求参数或其值为空时用作回退的默认值。
 */
@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String loginPage() {
        return "admin/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes attributes) {
        User user = userService.checkUser(username, password);
        if (user != null) {
            //将密码设为null，防止页面拿到密码
            user.setPassword(null);
            session.setAttribute("user", user);
            return "admin/index";
        } else {
            //这里不能使用Model，原因是：Model存放的是在请求域中，重定向之后是另外一个请求，所以拿不到信息
            attributes.addFlashAttribute("message", "用户名或密码错误");
            //通过redirect重定向过去，而不要通过跳转页面的方式，否则路径错误
            return "redirect:/admin";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        //将session域清空
        session.removeAttribute("user");
        return "redirect:/admin";
    }
}
