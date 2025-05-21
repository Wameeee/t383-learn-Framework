package com.ktjiaoyu.mapper.supplier;

import com.ktjiaoyu.entity.Supplier;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SupplierMapperTest {

    @Test
    void getSupplierList()  throws Exception{
        SqlSession sqlSession = null;
        List<Supplier> supplierList = null;

        try{
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            sqlSession = sqlSessionFactory.openSession();
            supplierList =sqlSession.selectList("com.ktjiaoyu.mapper.supplier.SupplierMapper.getSupplierList");

            for(Supplier supplier :supplierList){
                System.out.println(supplier.getSupName());
            }

        }finally {
            if(sqlSession != null){
                sqlSession.close();
            }
        }
    }
}