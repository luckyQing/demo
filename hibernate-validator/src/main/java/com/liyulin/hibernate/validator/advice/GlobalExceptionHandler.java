package com.liyulin.hibernate.validator.advice;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.liyulin.hibernate.validator.entity.Result;
import com.liyulin.hibernate.validator.entity.StateCode;

import lombok.extern.slf4j.Slf4j;

/**
 * 全局异常
 *
 * @author liyulin
 * @version 1.0 2018年3月31日 下午5:31:32
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public Result handleException(Exception exception, HttpServletRequest request) {
		log.error("exception result is: " + exception.getMessage());
		if (exception instanceof BindException) {
			BindException exs = (BindException) exception;

			List<ObjectError> errorList = exs.getAllErrors();
			if (null != errorList) {
				for (ObjectError error : errorList) {
					return new Result(error.getDefaultMessage(), StateCode.BUSINESS_FAILED.getCode());
				}
			}
		} else if (exception instanceof ConstraintViolationException) {
			ConstraintViolationException constraintViolationException = (ConstraintViolationException) exception;
			Set<ConstraintViolation<?>> constraintViolationSet = constraintViolationException.getConstraintViolations();
			if (null != constraintViolationSet) {
				for (ConstraintViolation<?> e : constraintViolationSet) {
					return new Result(e.getMessage(), StateCode.BUSINESS_FAILED.getCode());
				}
			}
		}

		return new Result(exception.getMessage(), StateCode.BUSINESS_FAILED.getCode());
	}
}
