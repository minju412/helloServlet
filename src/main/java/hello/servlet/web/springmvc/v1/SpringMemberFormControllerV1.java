package hello.servlet.web.springmvc.v1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller // 스프링이 자동으로 스프링 빈으로 등록한다. (컴포넌트 스캔의 대상이 됨)
public class SpringMemberFormControllerV1 {

    @RequestMapping("/springmvc/v1/members/new-form") // 요청 정보를 매핑한다. 해당 URL이 호출되면 이 메서드가 호출된다.
    public ModelAndView process(){
        return new ModelAndView("new-form"); // 모델과 뷰 정보를 담아서 반환한다.
    }
}
