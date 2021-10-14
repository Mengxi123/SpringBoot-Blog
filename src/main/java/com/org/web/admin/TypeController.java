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
import org.springframework.web.bind.annotation.PathVariable;
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

    /**
     * 在页面列表分页展示所有分类
     * @param pageable
     * @param model
     * @return
     */
    @GetMapping("/type")
    public String list(@PageableDefault(size = 7, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
                       Model model) {
        model.addAttribute("page",  typeService.listType(pageable));
        return "admin/type";
    }

    /**
     *跳转到新增一个分类的页面
     * @param model
     * @return
     */
    @GetMapping("type/input")
    public String input(Model model) {
        model.addAttribute("type", new Type());
        return "admin/type-input";
    }

    /**
     * 编辑一个分类时，附带当前分类值跳转页面
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/type/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        model.addAttribute("type", typeService.getType(id));
        return "admin/type-input";
    }

    /**
     * 新增一个分类，并做了去重验证以及非空验证
     * @param type 校验时，Type参数一定要和BindingResult参数挨在一起，否则没有效果。
     * @param result
     * @param attributes
     * @return
     */
    @PostMapping("/type")
    public String post(@Valid Type type, BindingResult result, RedirectAttributes attributes) {
        //避免重复标签校验
        Type t1 = typeService.getTypeByName(type.getName());
        if (t1 != null) {
            result.rejectValue("name", "nameError","不能添加重复分类");
        }
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


    /**
     * 更新一个分类，并做了去重验证以及非空验证
     * @param type
     * @param result
     * @param id
     * @param attributes
     * @return
     */
    @PostMapping("/type/{id}")
    public String editPost(@Valid Type type, BindingResult result, @PathVariable Long id, RedirectAttributes attributes) {
        //避免重复标签校验
        Type t1 = typeService.getTypeByName(type.getName());
        if (t1 != null) {
            result.rejectValue("name", "nameError","不能添加重复分类");
        }
        //如果字段校验有误，就报错result为错误信息,值为@NotBlank里面的参数
        if (result.hasErrors()) {
            return "admin/type-input";
        }
        Type t = typeService.updateType(id, type);
        if (t == null) {
            attributes.addFlashAttribute("message", "更新失败");
        } else {
            attributes.addFlashAttribute("message", "更新成功");
        }
        return "redirect:/admin/type";
    }


    /**
     * 删除一个分类
     * @param id
     * @param attributes
     * @return
     */
    @GetMapping("/type/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        typeService.deleteType(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/type";
    }

}
