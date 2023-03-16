package com.jesusfc.springboot3java17.configuration;

import com.jesusfc.springboot3java17.exception.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

/**
 * @author jesusfc
 * Created on mar 2023
 */
@ControllerAdvice
public class ErrorHandlerController {
    @ExceptionHandler({ArithmeticException.class})
    public String arithmeticException(Exception ex, Model model) {
        model.addAttribute("error", "Error ArithmeticException");
        model.addAttribute("message", ex.getMessage());
        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);
        model.addAttribute("timestamp", new Date());
        return "/error/arithmeticException";
    }

    @ExceptionHandler({NullPointerException.class})
    public String nullPointerException(Exception ex, Model model) {
        model.addAttribute("error", "Error NullPointerException");
        model.addAttribute("message", ex.getMessage());
        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);
        model.addAttribute("timestamp", new Date());
        return "/error/arithmeticException";
    }
    @ExceptionHandler({Exception.class})
    public String genericException(Exception ex, Model model) {
        model.addAttribute("error", "Error GenericException");
        model.addAttribute("message", ex.getMessage());
        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);
        model.addAttribute("timestamp", new Date());
        return "/error/genericException";
    }

    @ExceptionHandler({UserException.class})
    public String userException(Exception ex, Model model) {
        model.addAttribute("error", "Error UserException");
        model.addAttribute("message", ex.getMessage());
        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);
        model.addAttribute("timestamp", new Date());
        return "/error/genericException";
    }
}
