package com.org.dao;

import com.org.po.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author Create by MengXi on 2021/10/15 16:23.
 *
 * JpaSpecificationExecutor  复杂动态查询实例
 */
public interface BlogRepository extends JpaRepository<Blog, Long>, JpaSpecificationExecutor<Blog> {
}
