package cn.itsource.aisell.repository;

import cn.itsource.aisell.domain.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

     @Test
      public void testFindAll() throws Exception{
         System.out.println(employeeRepository);
         //SimpleJpaRepository@6443b128
         System.out.println(employeeRepository.getClass());

         List<Employee> list = employeeRepository.findAll();
         list.forEach(e -> System.out.println(e));
     }

   @Test
   public void testSave() throws Exception{
        Employee employee = new Employee();
        employee.setUsername("小张");
        employee.setPassword("1234");
        employee.setEmail("1234@qq.com");
        employee.setAge(46);
        //save:添加或者修改
        employeeRepository.save(employee);

   }
   @Test
   public void testUpdate() throws Exception{
            Employee employee = new Employee();
            employee.setUsername("小丽");
            employee.setPassword("1234");
            employee.setEmail("1234@qq.com");
            employee.setAge(46);
            employee.setId(273L);
            employeeRepository.save(employee);
       }

    @Test
    public void testDelete() throws Exception{
        employeeRepository.delete(273L);
    }
    @Test
    public void testFind() throws Exception{
        Employee employee = employeeRepository.findOne(274L);
       // Employee employee = employeeRepository.getOne(274L);
        System.out.println(employee.getClass());
    }


    //分页
     @Test
     public void testPage() throws Exception{
         /**
          * page:第一个参数 -> 第几页(从0开始算)
          * size：每页条数
          */
         Pageable pageable = new PageRequest(13,10);

         /**
          * 要分页，需要一个分页对象
          * Pageable
          */
         Page<Employee> page = employeeRepository.findAll(pageable);

         System.out.println(page.getTotalElements()); //139 -> 总条数
         System.out.println(page.getTotalPages()); //14 -> 总页数
         System.out.println(page.getContent());  //当前页的数据
         System.out.println(page.getNumber()); //当前在第几页
         System.out.println(page.getNumberOfElements()); //当前这一页有多少条数据
         System.out.println(page.getSize()); //每页条数

         page.forEach(e -> System.out.println(e));
     }

    //排序
    @Test
    public void testSort() throws Exception{
        /**
         * 第一个参数：排序类型(升降)
         * 第二个参数: 根据什么字段排序
         */
        Sort sort = new Sort(Sort.Direction.DESC,"age");
        List<Employee> list = employeeRepository.findAll(sort);
        list.forEach(e -> System.out.println(e));
    }
    //分页排序
    @Test
    public void testPageSort() throws Exception{
         //排序对象
        Sort sort = new Sort(Sort.Direction.DESC,"age");
         //分页对象
        Pageable pageable = new PageRequest(0,10,sort);

        Page<Employee> page = employeeRepository.findAll(pageable);
        page.forEach(e -> System.out.println(e));
    }

//     @Test
//      public void testFindByUsername() throws Exception{
////         List<Employee> list = employeeRepository.findByUsernameLike("%1%");
////         list.forEach(e -> System.out.println(e));
//         List<Employee> list = employeeRepository.findUsername("%1%");
//         list.forEach(e -> System.out.println(e));
//     }
//     @Test
//      public void testFindByUsernameAndPassword() throws Exception{
////         Employee employee = employeeRepository.findByUsernameAndPassword("admin811c5f", "123456");
//         Employee employee = employeeRepository.login("admin811c5f", "123456");
//         System.out.println(employee);
//     }
//     @Test
//      public void testSQLCount() throws Exception{
//         System.out.println(employeeRepository.myCount());
//     }
//
//    /**
//     * 根据JpaSpecificationExecutor
//     * @throws Exception
//     */
//    @Test
//    public void testFind01() throws Exception{
//          List<Employee> list = employeeRepository.findAll(new Specification<Employee>() {
//              /**
//               *官方解释:
//               * Root<T> root:代表了可以查询和操作的实体对象的根，
//               *              可以通过它的 Path<Y> get(String attributeName); 这个方法拿到我们要操作的字段
//               *              注意:只可以拿到对应的T的字段(Employee)
//               * CriteriaQuery<?> query:代表一个specific的顶层查询对象
//               *              包含查询的各个部分,比如select,from,where,group by ,order by 等
//               *              简单理解 就是它提供 了查询ROOT的方法(where,select,having)
//               * CriteriaBuilder cb:用来构建CriteriaQuery的构建器对象(相当于条件或者说条件组合)
//               *              构造好后以Predicate的形式返回
//               */
//              /**
//               * 自己来确定使用什么规则进行查询
//               * @param root :根(表) -> 获取要查询的字段(username,password,age,...)
//               * @param criteriaQuery -> 查询哪些字段，排序是什么(主要是把多个查询的条件连系起来)
//               *                              and,or,...
//               * @param criteriaBuilder
//               *      字段之间是什么关系，如何生成一个查询条件，每一个查询条件都是什么方式
//               *                      主要判断关系（和这个字段是相等，大于，小于like等）
//               * @return Predicate 断言，断定; 宣布 -> 最后确定好的规则
//               *                    where key = value and key =value ,...
//               */
//              @Override
//              public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
//                  //1.拿到用户名字段
//                  Path usernamePath = root.get("username");
//                  //2.确定条件
//                  Predicate p = criteriaBuilder.like(usernamePath, "%1%");
//                  //3.返回规则
//                  return p;
//              }
//          });
//          list.forEach(e -> System.out.println(e));
//      }
//    /**
//     * 根据JpaSpecificationExecutor
//     * @throws Exception
//     */
//    @Test
//    public void testFind02() throws Exception{
//        List<Employee> list = employeeRepository.findAll(new Specification<Employee>() {
//            @Override
//            public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
//                //1.拿到用户名字段
//                Path usernamePath = root.get("username");
//                //2.拿到邮件字符
//                Path emailPath = root.get("email");
//
//                //用户名的判断
//                Predicate p1 = builder.like(usernamePath, "%1%");
//                //邮件判断
//                Predicate p2 = builder.like(emailPath, "%2%");
//                //把条件加到一起
//                Predicate pall = query.where(p1, p2).getRestriction();
//
//                //3.返回规则
//                return pall;
//            }
//        });
//        list.forEach(e -> System.out.println(e));
//    }
//
//
//    @Test
//    public void testFind03() throws Exception{
//        //创建排序对象
//        Sort sort = new Sort(Sort.Direction.DESC,"age");
//        //创建分页对象
//        Pageable pageable = new PageRequest(0,5,sort);
//
//        Page<Employee> list = employeeRepository.findAll(new Specification<Employee>() {
//            @Override
//            public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
//                //1.拿到用户名字段
//                Path usernamePath = root.get("username");
//                //2.确定条件
//                Predicate p = criteriaBuilder.like(usernamePath, "%1%");
//                //3.返回规则
//                return p;
//            }
//        },pageable);
//        list.forEach(e -> System.out.println(e));
//    }
//
//
//    @Test
//    public void testFind01Spec() throws Exception{
//        //创建出规则
//        Specification<Employee> spec = Specifications.<Employee>and()
//                .like("username", "%1%")
//                .build();
//        //完成查询
//        List<Employee> list = employeeRepository.findAll(spec);
//
//        list.forEach(e -> System.out.println(e));
//    }
//
//    @Test
//    public void testFind02Spec() throws Exception{
//        //创建出规则
//        Specification<Employee> spec = Specifications.<Employee>and()
//                .like("username", "%1%")
//                .like("email", "%2%")
//                .build();
//        //完成查询
//        List<Employee> list = employeeRepository.findAll(spec);
//
//        list.forEach(e -> System.out.println(e));
//    }
//
//
//    @Test
//    public void testFind03Spec() throws Exception{
//        //创建排序对象
//        Sort sort = new Sort(Sort.Direction.DESC,"age");
//        //创建分页对象
//        Pageable pageable = new PageRequest(0,5,sort);
//        //创建出规则
//        Specification<Employee> spec = Specifications.<Employee>and()
//                .like("username", "%1%")
//                .build();
//        //完成查询
//        Page<Employee> list = employeeRepository.findAll(spec,pageable);
//
//        list.forEach(e -> System.out.println(e));
//    }
//
//    @Test
//    public void testFindQuery() throws Exception{
//        //创建Query对象
//        EmployeeQuery query = new EmployeeQuery();
//        query.setUsername("1");
//        query.setCurrentPage(1);
//        query.setPageSize(5);
//
//        query.setOrderName("age");
//        query.setOrderType("desc");
////        query.setEmail("2");
////        query.setAge(20);
//        //创建分页对象
//        Pageable pageable = new PageRequest(query.getJpaPage(),query.getPageSize(),query.createSort());
//        //创建出规则
//        Specification<Employee> spec = query.createSpec();
//        //完成查询
//        Page<Employee> list = employeeRepository.findAll(spec,pageable);
//        list.forEach(e -> System.out.println(e));
//    }
//
//
//    @Test
//    public void testFindQueryhaha() throws Exception{
//        //创建Query对象
//        EmployeeQuery query = new EmployeeQuery();
//        query.setUsername("1");
//        query.setCurrentPage(1);
//        query.setPageSize(5);
//        query.setOrderName("age");
//        query.setOrderType("desc");
//
//        Page page = employeeRepository.findPageByQuery(query);
//
//        page.forEach(e -> System.out.println(e));
//    }

}
