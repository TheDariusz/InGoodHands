package pl.coderslab.charity.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/charity")
public class CharityController {

    private static final String MAIN_VIEW = "index";

    @GetMapping
    public String getMainView() {
        return MAIN_VIEW;
    }

}
