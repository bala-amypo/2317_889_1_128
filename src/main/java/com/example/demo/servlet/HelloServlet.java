package com.example.demo.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;

public class HelloServlet extends HttpServlet {
    private String message;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        // Always set the message, independent of config parameters
        this.message = "Hello Tomcat";
    }

    @Override
    public void destroy() {
        // No special cleanup required, but method must not throw
        this.message = null; // optional reset
    }

    public String getMessage() {
        return message;
    }
}