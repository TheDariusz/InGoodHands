package pl.coderslab.charity.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.donation.Institution;
import pl.coderslab.charity.donation.InstitutionServiceImpl;

import java.util.List;

@Controller
@RequestMapping(value = "/charity")
@RequiredArgsConstructor
public class CharityController {

    private static final String MAIN_VIEW = "index";
    private final InstitutionServiceImpl institutionService;

    @GetMapping
    public String getMainView(Model model) {
        final List<Institution> institutions = institutionService.fetchAllInstitutions();
        model.addAttribute("institutions", institutions);
        return MAIN_VIEW;
    }

}
