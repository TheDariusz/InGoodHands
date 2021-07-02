package pl.coderslab.charity.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.charity.donation.CategoryService;
import pl.coderslab.charity.donation.DonationDto;
import pl.coderslab.charity.donation.DonationService;
import pl.coderslab.charity.donation.InstitutionService;
import pl.coderslab.charity.user.UserDto;
import pl.coderslab.charity.user.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/charity")
@RequiredArgsConstructor
public class CharityController {

    private static final String MAIN_VIEW = "index";
    private static final String FORM_VIEW = "form";
    private static final String FORM_CONFIRMATION_VIEW = "form-confirmation";
    private static final String LOGIN_VIEW = "login";
    private static final String REGISTER_VIEW = "register";

    private final InstitutionService institutionService;
    private final DonationService donationService;
    private final CategoryService categoryService;
    private final UserService userService;

    @GetMapping
    public String getMainView(Model model) {
        model.addAttribute("institutions", institutionService.fetchInstitutions());
        model.addAttribute("donations", donationService.countDonations());
        model.addAttribute("bags", donationService.sumOfBags());
        return MAIN_VIEW;
    }

    @GetMapping(value = "/form")
    public String getFormView(Model model) {
        DonationDto donationDto = new DonationDto();
        model.addAttribute("categories", categoryService.fetchCategories());
        model.addAttribute("institutions", institutionService.fetchInstitutions());
        model.addAttribute("donation", donationDto);
        return FORM_VIEW;
    }

    @PostMapping(value = "/form")
    public String getForm(DonationDto donationDto) {
        donationService.keepDonation(donationDto);
        return FORM_CONFIRMATION_VIEW;
    }

    @GetMapping(value = "/login")
    public String getLoginView() {
        return LOGIN_VIEW;
    }

    @PostMapping(value = "/login")
    public String getLoginForm( @RequestParam(value = "error", required = false) String error,   Model model) {
        String errorMessage = null;
        if (error!=null) {
            errorMessage = "Username or Password is incorrect !!";
        }
        model.addAttribute("errorMessage", errorMessage);
        return "login";
    }

    @GetMapping(value = "/register")
    public String getRegisterView(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("userDto", userDto);
        return REGISTER_VIEW;
    }

    @PostMapping("/register")
    public String getRegisterForm(@Valid UserDto userDto, BindingResult bindingResult) {
        if (userService.existUserDto(userDto)) {
            bindingResult.rejectValue("email", "error.user",
                    "User '" + userDto.getEmail() + "' is already register");
            return REGISTER_VIEW;
        }

        if (bindingResult.hasErrors()) {
            return REGISTER_VIEW;
        } else {
            userService.saveUserDto(userDto);
            return LOGIN_VIEW;
        }
    }
}
