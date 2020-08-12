package lv.continuum.monitoring.controller.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Redirects from the root URL to Swagger UI.
 */
@Controller
public class SwaggerController {

    @GetMapping(value = "/")
    public Object index(HttpServletRequest request, HttpServletResponse response) {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(request.getServletContext().getContextPath() + "/swagger-ui/");
        return redirectView;
    }
}
