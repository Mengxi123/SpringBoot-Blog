package com.org.web.admin;

import com.org.po.Type;
import com.org.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * @author Create by MengXi on 2021/10/13 17:02.
 * Pageable定义了很多方法，但其核心的信息只有两个：一是分页的信息（page、size），二是排序的信息。
 *          使用@PageableDefault的时候可以自定义分页信息
 *           @PageableDefault (value = 15, sort = { "update_time" }, direction = Sort.Direction.DESC) Pageable pageable)。
 *
 * @Valid  注解搭配@NotBlank使用
 */
@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping("/type")
    public String list(@PageableDefault(size = 10, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
                       Model model) {
        model.addAttribute("page",  typeService.listType(pageable));
        return "admin/type";
    }

    @GetMapping("type/input")
    public String input(Model model) {
        model.addAttribute("type", new Type());
        return "admin/type-input";
    }

    @PostMapping("/type")
    public String post(@Valid Type type, BindingResult result, RedirectAttributes attributes) {
        //如果字段校验有误，就报错result为错误信息,值为@NotBlank里面的参数
        if (result.hasErrors()) {
            return "admin/type-input";
        }
        Type t = typeService.saveType(type);
        if (t == null) {
            attributes.addFlashAttribute("message", "操作失败");
        } else {
            attributes.addFlashAttribute("message", "操作成功");
        }
        return "redirect:/admin/type";
    }
}
