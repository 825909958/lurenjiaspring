package com.example.lurenjiaspring.aop.adviceuntil;

import com.example.lurenjiaspring.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/baseInfo")
public class BaseInfoController {

    /**
     * 添加基本信息
     *
     * @return
     */
    @GetMapping("/addBaseInfo")
    public Object addBaseInfo() {

        Student student = new Student();
        student.setAge(11);
        student.setName("111");
        return student;
    }

    public static void main(String[] args) {
        CommonResult<String> stringCommonResult = new CommonResult<>();
        stringCommonResult.setData("11111111");
        System.out.println(stringCommonResult);
    }
}
