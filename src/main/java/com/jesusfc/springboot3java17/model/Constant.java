package com.jesusfc.springboot3java17.model;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class Constant {

    public static final int NAME_MIN_CHAR = 2;
    public static final int NAME_MAX_CHAR = 40;
    public static final String NAME_PATTERN = "^[\\p{L}\\s\\-]+$";

    public static final int USERNAME_MIN_CHAR = 3;
    public static final int USERNAME_MAX_CHAR = 40;
    public static final String USERNAME_PATTERN = "^[\\w\\d]+$";

    public static final int EMAIL_MAX_CHAR = 50;
    public static final String EMAIL_PATTERN = "^(?!.*[=*\\s])(?=.*[.])[\\w\\d\\W]+$";
    public static final String USERNAME_EMAIL_PATTERN = "^(?!.*[=*\\s])[\\w\\d\\W]+$";

    public static final int PASSWORD_MIN_CHAR = 8;
    public static final int PASSWORD_MAX_CHAR = 125;
    // Pattern must consist of at least  8 characters, one lower- and one uppercase alphabetical letter, one numeric number and one special character !@#$%^&_
    public static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[\\W]).{8,125}$";

    // Model messages (can be found in messages.properties files - User constant error messages)
    public static final String FIRST_NAME_NOT_EMPTY = "err.user.not.empty.first.name";
    public static final String LAST_NAME_NOT_EMPTY = "err.user.not.empty.last.name";
    public static final String USERNAME_NOT_EMPTY = "err.user.not.empty.username";
    public static final String EMAIL_NOT_EMPTY = "err.user.not.empty.email";
    public static final String USERNAME_EMAIL_NOT_EMPTY = "err.user.not.empty.username.email";
    public static final String PASSWORD_NOT_EMPTY = "err.user.not.empty.password";

    public static final String FIRST_NAME_CHAR_LENGTH = "err.user.char.first.name";
    public static final String LAST_NAME_CHAR_LENGTH = "err.user.char.last.name";
    public static final String USERNAME_CHAR_LENGTH = "err.user.char.username";
    public static final String EMAIL_CHAR_LENGTH = "err.user.char.email";
    public static final String USERNAME_EMAIL_CHAR_LENGTH = "err.user.char.username.email";
    public static final String PASSWORD_CHAR_LENGTH = "err.user.char.password";

    public static final String FIRST_NAME_PATTERN_ERROR = "err.user.pattern.first.name";
    public static final String LAST_NAME_PATTERN_ERROR = "err.user.pattern.last.name";
    public static final String USERNAME_PATTERN_ERROR = "err.user.pattern.username";
    public static final String EMAIL_PATTERN_ERROR = "err.user.pattern.email";
    public static final String USERNAME_EMAIL_PATTERN_ERROR = "err.user.pattern.username.email";
    public static final String PASSWORD_PATTERN_ERROR = "err.user.pattern.password";

    public static final int FILM_CODE_MIN_CHAR = 5;
    public static final int FILM_CODE_MAX_CHAR = 15;
    public static final int GENERAL_TITLE_MIN_CHAR = 5;
    public static final int GENERAL_TITLE_MAX_CHAR = 80;
    public static final String GENERAL_TITLE_CHAR_LENGTH = "err.film.title.length";
    public static final String FILM_CODE_CHAR_LENGTH = "err.film.code.length";
}