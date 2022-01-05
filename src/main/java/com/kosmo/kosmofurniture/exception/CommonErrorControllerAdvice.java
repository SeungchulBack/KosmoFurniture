package com.kosmo.kosmofurniture.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
@Slf4j
public class CommonErrorControllerAdvice {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView CommonError(Exception e) {
        log.debug("Exception : {}", e.getClass().getName());

        ModelAndView mav = new ModelAndView("common_error");
        mav.addObject("errorCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
        mav.addObject("errorStatus", HttpStatus.INTERNAL_SERVER_ERROR);
        mav.addObject("errorMessage", "서버에 오류가 발생하였습니다.");
        return mav;
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ModelAndView bindError(Exception e) {
        log.debug("Exception : {}", e.getClass().getName());

        ModelAndView mav = new ModelAndView("error");
        mav.addObject("errorCode", HttpStatus.BAD_REQUEST.value());
        mav.addObject("errorStatus", HttpStatus.BAD_REQUEST);
        mav.addObject("errorMessage", "유효한 값을 입력하시오.");
        return mav;
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView notFoundError(Exception e) {
        log.debug("Exception : {}", e.getClass().getName());

        ModelAndView mav = new ModelAndView("error");
        mav.addObject("errorCode", HttpStatus.NOT_FOUND.value());
        mav.addObject("errorStatus", HttpStatus.NOT_FOUND);
        mav.addObject("errorMessage", "요청하신 페이지를 찾을 수 없습니다.");
        return mav;
    }
}
