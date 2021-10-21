package com.org.web;

import com.org.po.Blog;
import com.org.po.Type;
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
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author Create by MengXi on 2021/10/21 15:33.
 */
@Controller
public class TypeShowController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;


    /**
     * 获取该type标签下的所有博客，如果是第一次进入首页面，就从type中拿第一个
     * @param pageable
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/types/{id}")
    public String types(@PageableDefault(size = 5, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable
                        ,@PathVariable Long id, Model model) {
        List<Type> types = typeService.listTypeTop(100000);
        if (id == -1) {
            id = types.get(0).getId();
        }
        BlogQuery blogQuery = new BlogQuery();
        blogQuery.setTypeId(id);
        model.addAttribute("types", types);
        model.addAttribute("page", blogService.listBlog(pageable, blogQuery));
        model.addAttribute("activeTypeId", id);
        return "types";
    }
}
