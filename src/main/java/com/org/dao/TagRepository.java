package com.org.dao;

import com.org.po.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Create by MengXi on 2021/10/14 17:17.
 */
public interface TagRepository extends JpaRepository<Tag, Long> {

    Tag findByName(String name);

}
