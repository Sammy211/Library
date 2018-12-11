package com.inai.oorpo.library.Library.controllers;


import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController {

    @GetMapping("/error")
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer code = Integer.valueOf(status.toString());

            if (code == HttpStatus.NOT_FOUND.value()) {
                return "errors/404";
            }
        }
        return "errors/500";
    }


    @Override
    public String getErrorPath() {
        return "/error";
    }
}
