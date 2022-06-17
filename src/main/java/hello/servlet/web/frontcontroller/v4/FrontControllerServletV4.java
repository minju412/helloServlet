package hello.servlet.web.frontcontroller.v4;

import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV4", urlPatterns = "/front-controller/v4/*") // /front-controller/v4 을 포함한 모든 하위 요청은 이 서블릿에서 받아들인다.
public class FrontControllerServletV4 extends HttpServlet {

    // 매핑 정보 저장 // (key, value): (매핑 URL, 호출될 컨트롤러)
    private Map<String, ControllerV4> controllerMap = new HashMap<>();

    public FrontControllerServletV4() {
        controllerMap.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
        controllerMap.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
        controllerMap.put("/front-controller/v4/members", new MemberListControllerV4());
    }

    // service(): requestURL를 받은 뒤, 실제 호출할 컨트롤러를 controllerMap에서 찾아 호출한다.
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        String requestURI = req.getRequestURI();
        ControllerV4 controller = controllerMap.get(requestURI); // 부모는 자식을 받을 수 있다. (다형성)

        if (controller == null) {
            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Map<String, String> paramMap = createParamMap(req);
        Map<String, Object> model = new HashMap<>(); // 모델 객체를 생성한다.

        String viewName = controller.process(paramMap, model); // 모델 객체를 함께 넘겨준다.

        MyView view = viewResolver(viewName); // 논리이름으로 물리위치를 반환한다.
        view.render(model, req, res); // render()를 호출하면서 Model 정보를 넘긴다.
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    // HttpServletRequest에서 파라미터 정보를 모두 꺼내서 Map으로 반환한다.
    private Map<String, String> createParamMap(HttpServletRequest req) {
        Map<String, String> paramMap = new HashMap<>();
        req.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, req.getParameter(paramName)));
        return paramMap;
    }
}
