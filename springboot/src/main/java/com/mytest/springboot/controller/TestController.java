package com.mytest.springboot.controller;

import com.mytest.springboot.beans.Profile;
import com.mytest.springboot.beans.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ziheng on 2019-08-11.
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/show")
    public Result show(Profile profile) {
        System.out.println(profile);
        Result result = Result.wrapSuccessfulResult(profile);

        return result;
    }

}
