package com.jesusfc.springboot3java17.controller.rest;


import com.jesusfc.springboot3java17.openapi3.v1.api.V1Api;
import com.jesusfc.springboot3java17.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.LocaleResolver;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/rest/user")
public class UserRestController implements V1Api {

    private final UserService userService;
    private final LocaleResolver localeResolver;
    private final MessageSource messageSource;
/*
    @Operation(operationId = "logsTest")
    @GetMapping("/logs-test")
    public HttpStatus getLogsTest() {

        log.trace("some trace logging...");
        log.debug("some debug logging...");
        log.info("some info logging...");
        log.warn("some warn logging...");
        log.error("some error logging...");

        return HttpStatus.OK;
    }

    @Operation(operationId = "userList")
    @GetMapping("/list")
    public ResponseEntity<UserEntityListRS> getUserList(Locale locale) {
        String message = messageSource.getMessage("app.name", null, locale);
        log.debug("message: " + message);
        log.error("message: " + message);
                List < UserEntity > userList = userService.getUserList();
        return new ResponseEntity<>(UserEntityListRS.userEntityListRS(userList), HttpStatus.OK);
    }

    @GetMapping("/language-by-param")
    public String hello(HttpServletRequest request, HttpServletResponse httpResponse,
                        @RequestParam(value = "idioma", defaultValue = "es") String lang) {
        localeResolver.setLocale(request, httpResponse, new Locale(lang));
        return String.format("New Language: %s!", lang);
    }

    @GetMapping("/language-by-interceptor")
    public String hello(Locale locale) {
        String message = messageSource.getMessage("app.name", null, locale);
        return String.format("Cambiamos el idioma atravez del interceptor: " + message);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable(value = "email") String email) {
        Locale locale = LocaleContextHolder.getLocale();
        String message = messageSource.getMessage("app.name", null, locale);
        System.out.println("Lang: " + locale.getLanguage() + ", Message: " + message);
        Optional<UserEntity> byEmail = userService.getUserByEmail(email);
        if (byEmail.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(byEmail.get(), HttpStatus.OK);
    }

 */
}
