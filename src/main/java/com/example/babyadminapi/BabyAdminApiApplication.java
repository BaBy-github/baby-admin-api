package com.example.babyadminapi;

import com.example.babyadminapi.service.CRUDClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.List;

@SpringBootApplication
public class BabyAdminApiApplication {

    @Autowired
    private CRUDClassService crudClassService;

    public static void main(String[] args) {
        SpringApplication.run(BabyAdminApiApplication.class, args);
    }

    //执行顺序Constructor(构造方法) -> @Autowired(依赖注入) -> @PostConstruct(注释的方法)
    @PostConstruct
    public void init(){
        List<String> classNotFoundList = crudClassService.loadCRUDClass2CRUDClassMapCache();
        if(classNotFoundList.size() > 0){
            System.out.println("<<<<<< 以下类未找到, 请检查数据库中的类名是否正确: ");
            classNotFoundList.forEach(System.out::println);
            throw new Error();
        }
    }
}
