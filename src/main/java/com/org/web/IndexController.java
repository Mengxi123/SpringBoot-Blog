package com.org.web;

import com.org.NotFoundException;
import com.org.po.Type;
import com.org.service.BlogService;
import com.org.service.TagService;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author MengXi
 * @Controller 用于表示层的注解，作用与@Component注解一样，表明将该资源交给spring进行管理
 *             String value : 指定bean的id，如果不屑，默认为该类的类名(且将其首字母小写)
 */
@Controller
public class IndexController {
    /**
     *  @GetMapping  建立请求url和处理请求方法之间的映射关系，配置在类上为一级注解，配置在方法上为二级注解
     *              String value : 指定请求的url
     *              String method :指定请求的方法
     *              String params : 限制请求参数的条件，支持简单的表达式
     *
     * Restful风格的参数获取：url + 请求方式
     *          在url中使用占位符进行参数绑定，同时在方法中使用@PathVariable注解与占位符进行匹配,参数名称要一致。
     */

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    /**
     * 分页获取博客内容
     * @param pageable
     * @param model
     * @return
     */
    @GetMapping("/")
    public String index(@PageableDefault(size = 5, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable
                        ,Model model) {
        model.addAttribute("page", blogService.listBlog(pageable));
        model.addAttribute("types", typeService.listTypeTop(6));
        model.addAttribute("tags", tagService.listTagTop(10));
        model.addAttribute("recommendBlogs", blogService.listRecommendBlogTop(8));
        return "index";
    }

    @PostMapping("/search")
    public String search(@PageableDefault(size = 5, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable
                         , @RequestParam String query, Model model) {
        model.addAttribute("page", blogService.listBlog("%" + query + "%", pageable));
        model.addAttribute("query", query);
        return "search";

    }

    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id, Model model) {
        model.addAttribute("blog", blogService.getAndConvert(id));
        return "blog";
    }
}
