package com.org.dao;

import com.org.po.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Create by MengXi on 2021/10/12 15:57.
 *
 * pring Data :提供了一整套数据访问层(DAO)的解决方案，致力于减少数据访问层(DAO)的开发量。它使用一个叫作Repository的接口类为基础，它被定义为访问底层数据模型的超级接口。
 *             而对于某种具体的数据访问操作，则在其子接口中定义。
 *              public interface Repository<T, ID extends Serializable> {
 *              }
 *              所有继承这个接口的interface都被spring所管理，此接口作为标识接口，功能就是用来控制domain模型的。
 *              Spring Data可以让我们只定义接口，只要遵循spring data的规范，就无需写实现类。
 *
 * 什么是Repository？
 *     Repository（资源库）：通过用来访问领域对象的一个类似集合的接口，在领域与数据映射层之间进行协调。这个叫法就类似于我们通常所说的DAO，在这里，我们就按照这一习惯把数据访问层叫Repository
 *     Spring Data给我们提供几个Repository，基础的Repository提供了最基本的数据访问功能，其几个子接口则扩展了一些功能。它们的继承关系如下：
 *          Repository： 仅仅是一个标识，表明任何继承它的均为仓库接口类，方便Spring自动扫描识别
 *          CrudRepository： 继承Repository，实现了一组CRUD相关的方法
 *          PagingAndSortingRepository： 继承CrudRepository，实现了一组分页排序相关的方法
 *          JpaRepository： 继承PagingAndSortingRepository，实现一组JPA规范相关的方法
 *          JpaSpecificationExecutor： 比较特殊，不属于Repository体系，实现一组JPA Criteria查询相关的方法
 *     我们自己定义的XxxxRepository需要继承JpaRepository，这样我们的XxxxRepository接口就具备了通用的数据访问控制层的能力。
 *          接口必须继承类型参数化为实体类型和实体类中的Id类型的Repository public interface UserRepository extends JpaRepository<T, ID>,
 *          T 需要类型化为实体类(Entity)User，ID需要实体类User中Id（我定义的Id类型是Long）的类型.
 *
 *
 */
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * 遵循它的命名规则，就会查询数据库
     * @param username
     * @param password
     * @return
     */
    User findByUsernameAndPassword(String username, String password);
}
