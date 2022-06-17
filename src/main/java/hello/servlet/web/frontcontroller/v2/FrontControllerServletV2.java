package hello.servlet.web.frontcontroller.v2;

import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v2.controller.MemberFormControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberListControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberSaveControllerV2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV2", urlPatterns = "/front-controller/v2/*") // /front-controller/v2 을 포함한 모든 하위 요청은 이 서블릿에서 받아들인다.
public class FrontControllerServletV2 extends HttpServlet {

    // 매핑 정보 저장 // (key, value): (매핑 URL, 호출될 컨트롤러)
    private Map<String, ControllerV2> controllerMap = new HashMap<>();

    public FrontControllerServletV2() {
        controllerMap.put("/front-controller/v2/members/new-form", new MemberFormControllerV2());
        controllerMap.put("/front-controller/v2/members/save", new MemberSaveControllerV2());
        controllerMap.put("/front-controller/v2/members", new MemberListControllerV2());
    }

    // service(): requestURL를 받은 뒤, 실제 호출할 컨트롤러를 controllerMap에서 찾아 호출한다.
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        String requestURI = req.getRequestURI();
        ControllerV2 controller = controllerMap.get(requestURI); // 부모는 자식을 받을 수 있다. (다형성)

        if (controller == null) {
            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        MyView view = controller.process(req, res);// MyView 객체를 반환한다.
        view.render(req, res); // MyView에 있는 render()를 호출하면 foward 로직을 수행해서 JSP가 실행된다.

    }
}
