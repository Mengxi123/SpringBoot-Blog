package com.org.service;

import com.org.po.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author Create by MengXi on 2021/10/13 16:47.
 */
public interface TypeService {
    Type saveType(Type type);

    Type getType(Long id);

    //通过名称查询Type
    Type getTypeByName(String name);

    Page<Type> listType(Pageable pageable);

    List<Type> listType();

    //获取指定数量的type
    List<Type> listTypeTop(Integer size);

    Type updateType(Long id, Type type);

    void deleteType(Long id);
}
