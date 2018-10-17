package com.xmasworking.tis.controller.app;

import com.xmasworking.tis.Exception.ReturnStatusCode;
import com.xmasworking.tis.Exception.ReturnTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 *
 * @author XmasPiano
 * @date 2018/10/13 - 下午5:17
 * Created by IntelliJ IDEA.
 */
@Slf4j
@ControllerAdvice
public class ExceptionHandlerBean  extends ResponseEntityExceptionHandler {

    /**
     * 数据找不到异常
     * @param ex
     * @param request
     * @return
     * @throws IOException
     */
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleDataNotFoundException(RuntimeException ex, WebRequest request) throws IOException {
        log.info("ExceptionHandler[Exception]");
        System.out.println("");
        ex.printStackTrace();
        return getResponseEntity(ex,request, ReturnStatusCode.Exception);
    }

    /**
     * 根据各种异常构建 ResponseEntity 实体. 服务于以上各种异常
     * @param ex
     * @param request
     * @param specificException
     * @return
     */
    private ResponseEntity<Object> getResponseEntity(RuntimeException ex, WebRequest request, int specificException) {
        log.info("ExceptionHandler[Exception]:getResponseEntity");
        ReturnTemplate returnTemplate = new ReturnTemplate();
        returnTemplate.setCode(specificException);
        returnTemplate.setMsg(ex.getMessage());

        return handleExceptionInternal(ex, returnTemplate,
                new HttpHeaders(), HttpStatus.OK, request);
    }

}