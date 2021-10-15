package com.org.web.admin;

import com.org.service.BlogService;
import com.org.service.TypeService;
import com.org.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Create by MengXi on 2021/10/13 15:37.
 */
@Controller
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    /**
     * 组合条件下的分页查询
     * @param pageable
     * @param blogQuery
     * @param model
     * @return
     */
    @GetMapping("/blog")
    public String blogs(@PageableDefault(size = 2, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                        BlogQuery blogQuery, Model model) {
        //直接获取所有类型存入到blog的条件搜索框当中
        model.addAttribute("types", typeService.listType());
        model.addAttribute("page", blogService.listBlog(pageable, blogQuery));
        return "admin/blog";
    }


    /**
     * 使用thymeleaf和ajax刷新局部页面
     * @param pageable
     * @param blogQuery
     * @param model
     * @return
     */
    @PostMapping("/blog/search")
    public String search(@PageableDefault(size = 2, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                         BlogQuery blogQuery, Model model) {
        model.addAttribute("page", blogService.listBlog(pageable, blogQuery));
        return "admin/blog :: blogList";
    }


}
