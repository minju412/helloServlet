package hello.servlet.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        System.out.println("HelloServlet.service");
        System.out.println("req = " + req);
        System.out.println("res = " + res);

        String username = req.getParameter("username"); // 쿼리 파리미터 조회
        System.out.println("username = " + username);

        res.setContentType("text/plain"); // header에 추가
        res.setCharacterEncoding("utf-8"); // header에 추가
        res.getWriter().write("hello" + username); // body에 추가
    }
}
