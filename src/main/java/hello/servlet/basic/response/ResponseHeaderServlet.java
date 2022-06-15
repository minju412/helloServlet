package hello.servlet.basic.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        // [status-line]
        res.setStatus(HttpServletResponse.SC_OK); // 200

        // [response-headers]
        res.setHeader("Content-Type", "text/plain;charset=utf-8");
        res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // cache 무효화
        res.setHeader("Pragma", "no-cache"); // cache 무효화
        res.setHeader("my-header", "hello"); // 임의의 헤더 생성

        // [편의 메서드]
//        content(res);
//        cookie(res);
//        redirect(res);

        // [message body]
        PrintWriter writer = res.getWriter();
        writer.println("안녕하세요!");
    }

    // [Header 편의 메서드]
    private void content(HttpServletResponse response) {
        //Content-Type: text/plain;charset=utf-8
        //Content-Length: 2
        //response.setHeader("Content-Type", "text/plain;charset=utf-8");
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        // response.setContentLength(2); //(생략시 자동 생성)
    }

    // [Cookie 편의 메서드]
    private void cookie(HttpServletResponse response) {
        //Set-Cookie: myCookie=good; Max-Age=600;
        //response.setHeader("Set-Cookie", "myCookie=good; Max-Age=600");
        Cookie cookie = new Cookie("myCookie", "good");
        cookie.setMaxAge(600); //600초
        response.addCookie(cookie);
    }

    // [Redirect 편의 메서드]
    private void redirect(HttpServletResponse response) throws IOException {
        //Status Code 302
        //Location: /basic/hello-form.html

        //response.setStatus(HttpServletResponse.SC_FOUND); //302
        //response.setHeader("Location", "/basic/hello-form.html");
        response.sendRedirect("/basic/hello-form.html"); // 이 한 줄과 위 두 줄이 같은 코드
    }
}
