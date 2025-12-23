package com.example.demo.servlet;

import jakarta.servlet.annotation.WebServlet;

@WebServlet(name = "HelloServlet", urlPatterns = {"/hello"})
public class HelloServlet extends HttpServlet {
    private String message;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.message = "Hello Tomcat";
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/plain");
        resp.getWriter().write(message);
    }

    @Override
    public void destroy() {
        this.message = null;
    }

    public String getMessage() {
        return message;
    }
}