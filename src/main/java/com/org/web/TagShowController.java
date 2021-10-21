package com.org.web;

import com.org.po.Tag;
import com.org.service.BlogService;
import com.org.service.TagService;
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
public class TagShowController {

    @Autowired
    private TagService tagService;

    @Autowired
    private BlogService blogService;


    /**
     * 获取该Tag标签下的所有博客，如果是第一次进入首页面，就从Tag中拿第一个
     * @param pageable
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/tags/{id}")
    public String tags(@PageableDefault(size = 5, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable
                        ,@PathVariable Long id, Model model) {
        List<Tag> tags = tagService.listTagTop(100000);
        if (id == -1) {
            id = tags.get(0).getId();
        }
        model.addAttribute("tags", tags);
        model.addAttribute("page", blogService.listBlog(id, pageable));
        model.addAttribute("activeTagId", id);
        return "tags";
    }
}
