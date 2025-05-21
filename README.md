# 作业项目名称

**课程名称**  
**学生姓名**：Wameeee  
**学号**：08  
**提交日期**：5月21日14：38
---
## 文件结构
t383-learn-Framework
├── .git/
├── .idea/
├── .mvn/
├── .gitignore
├── learnMybatis.iml
├── pom.xml
└── learnMybatis/
    ├── target/
    ├── pom.xml
    └── src/
        └── main/
            ├── java/
            │   └── com/
            │       └── ktjiaoyu/
            │           ├── entity/
            │           │   ├── SysUser.java
            │           │   ├── Supplier.java
            │           └── mapper/
            │               ├──supplier
            │               │   └── SupplierMapper.java
            │               │  
            │               ├── user/
            │                   └── SysUserMapper.java
            ├── resources/
            │   ├── mybatis-config.xml
            │   ├── database.properties
            │   ├── log4j.properties
            │   └── com/
            │       └── ktjiaoyu/
            │           └── mapper/
            │               ├── supplier/
            │                   └── SupplierMapper.xml    
            │               ├──  user/
            │                   └── SysUserMapper.xml
            ├── test/
            │   ├── com.ktjiaoyu.mapper.user.SysUserMapperTest.java
            │   ├──com.ktjiaoyu.mapper.supplier.SupplierMapperTest.java
            │
            └── webapp/
                ├── WEB-INF/
                │   └── web.xml
                └── index.jsp
