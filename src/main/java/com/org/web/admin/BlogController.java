package com.org.web.admin;

import com.org.po.Blog;
import com.org.po.Tag;
import com.org.po.User;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * @author Create by MengXi on 2021/10/13 15:37.
 */
@Controller
@RequestMapping("/admin")
public class BlogController {

    /**
        定义静态私有变量，抽取重复代码方式
     */
    private static final String INPUT = "admin/blog-input";
    private static final String LIST = "admin/blog";
    private static final String REDIRECT_LIST = "redirect:/admin/blog";

    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;

    /**
     * 组合条件下的分页查询
     * @param pageable
     * @param blogQuery
     * @param model
     * @return
     */
    @GetMapping("/blog")
    public String blogs(@PageableDefault(size = 5, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                        BlogQuery blogQuery, Model model) {
        //直接获取所有类型存入到blog的条件搜索框当中
        model.addAttribute("types", typeService.listType());
        model.addAttribute("page", blogService.listBlog(pageable, blogQuery));
        return LIST;
    }


    /**
     * 使用thymeleaf和ajax刷新局部页面
     * @param pageable
     * @param blogQuery
     * @param model
     * @return
     */
    @PostMapping("/blog/search")
    public String search(@PageableDefault(size = 5, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                         BlogQuery blogQuery, Model model) {
        model.addAttribute("page", blogService.listBlog(pageable, blogQuery));
        return "admin/blog :: blogList";
    }

    /**
     * 跳转到发布博客的页面
     * @param model 初始化一个对象，目的就是防止和修改共用一个页面的时候，返回已有值，而报错
     * @return
     */
    @GetMapping("/blog/input")
    public String input(Model model) {
        setTypeAndTag(model);
        model.addAttribute("blog", new Blog());
        return INPUT;
    }

    /**
     * 抽取公共方法
     * @param model
     */
    private void setTypeAndTag(Model model) {
        //直接获取所有类型存入到blog的输入框当中
        model.addAttribute("types", typeService.listType());
        //直接获取所有标签存入到blog的输入框当中
        model.addAttribute("tags", tagService.listTag());
    }

    /**
     * 修改博客
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/blog/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        setTypeAndTag(model);
        Blog blog = blogService.getBlog(id);
        blog.init();
        model.addAttribute("blog", blog);
        return INPUT;
    }

    /**
     * 新增博客
     * @param blog
     * @param attributes
     * @param session
     * @return
     */
    @PostMapping("/blog")
    public String post(Blog blog, RedirectAttributes attributes, HttpSession session) {
        blog.setUser((User) session.getAttribute("user"));
        blog.setType(typeService.getType(blog.getType().getId()));
        blog.setTags(tagService.listTag(blog.getTagIds()));
        Blog b = blogService.saveBlog(blog);
        if (b == null) {
            attributes.addFlashAttribute("message", "操作失败");
        } else {
            attributes.addFlashAttribute("message", "操作成功");
        }
        return REDIRECT_LIST;
    }

    /**
     * 删除一个博客
     * @param id
     * @param attributes
     * @return
     */
    @GetMapping("/blog/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message", "删除成功");
        return REDIRECT_LIST;
    }

}
