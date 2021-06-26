package pl.coderslab.charity.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.donation.DonationService;
import pl.coderslab.charity.donation.InstitutionService;

@Controller
@RequestMapping(value = "/charity")
@RequiredArgsConstructor
public class CharityController {

    private static final String MAIN_VIEW = "index";
    private final InstitutionService institutionService;
    private final DonationService donationService;

    @GetMapping
    public String getMainView(Model model) {
        model.addAttribute("institutions", institutionService.fetchAllInstitutions());
        model.addAttribute("donations", donationService.countDonations());
        model.addAttribute("bags", donationService.sumOfBags());
        return MAIN_VIEW;
    }

    @GetMapping(value = "/contact")
    public String getContactView(Model model) {
        return "redirect:#contact";
    }

    @GetMapping(value = "/form")
    public String getFormView(Model model) {
        return "form";
    }

}
