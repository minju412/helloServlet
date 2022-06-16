package hello.servlet.web.servletmvc;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "mvcMemberFormServlet", urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String viewPath = "/WEB-INF/views/new-form.jsp"; // 컨트롤러(서블릿)를 거쳐야지만 뷰(jsp) 호출 가능
        RequestDispatcher dispatcher = req.getRequestDispatcher(viewPath);// 컨트롤러 => 뷰 이동하기 위해서
        dispatcher.forward(req,res); // 서블릿에서 jsp(혹은 다른 서블릿)로 이동할 수 있는 기능이다. 서버 내부에서 다시 호출이 발생한다.

    }
}
