package com.app.dto;

public class ResponseStatus {
    public final static Integer OK = 200,
            BAD_REQUEST = 400,
            UNAUTHORISED = 401,
            NOT_FOUND = 404,
            INTERNAL_SERVER_ERROR = 500,
            REDIRECT = 302,
            FORBIDDEN = 403,
            NO_CONTENT = 204,
            CREATED = 201,
            MISSING_DATA = 620;
}
