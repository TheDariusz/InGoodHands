package pl.coderslab.charity.api;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import pl.coderslab.charity.donation.CategoryService;
import pl.coderslab.charity.donation.DonationDto;
import pl.coderslab.charity.donation.DonationService;
import pl.coderslab.charity.donation.InstitutionService;
import pl.coderslab.charity.mail.EmailService;
import pl.coderslab.charity.user.OnRegistrationCompleteEvent;
import pl.coderslab.charity.user.UserDto;
import pl.coderslab.charity.user.UserService;
import pl.coderslab.charity.user.repository.entity.UserEntity;
import pl.coderslab.charity.user.repository.entity.VerificationTokenEntity;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.Locale;

@Controller
@RequestMapping(value = "/charity")
@RequiredArgsConstructor
public class CharityController {

    private static final String MAIN_VIEW = "index";
    private static final String FORM_VIEW = "form";
    private static final String FORM_CONFIRMATION_VIEW = "form-confirmation";
    private static final String LOGIN_VIEW = "login";
    private static final String REGISTER_VIEW = "register";
    private static final String LOGOUT_VIEW = "logout";

    private final InstitutionService institutionService;
    private final DonationService donationService;
    private final CategoryService categoryService;
    private final UserService userService;
    private final EmailService emailService;
    private final ApplicationEventPublisher eventPublisher;
    private final MessageSource messages;

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
    public String getLoginForm(@RequestParam(value = "error", required = false) String error, Model model) {
        String errorMessage = null;
        if (error!=null) {
            errorMessage = "Username or Password is incorrect !!";
        }
        model.addAttribute("errorMessage", errorMessage);
        return LOGIN_VIEW;
    }

    @GetMapping("/logout")
    public String getLogoutView() {
        return LOGOUT_VIEW;
    }

    @GetMapping(value = "/register")
    public String getRegisterView(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("userDto", userDto);
        return REGISTER_VIEW;
    }

    @PostMapping("/register")
    public String getRegisterForm(@Valid UserDto userDto, WebRequest request, BindingResult bindingResult) {
        if (userService.existUserDto(userDto)) {
            bindingResult.rejectValue("email", "error.user",
                    "User '" + userDto.getEmail() + "' is already register");
            return REGISTER_VIEW;
        }

        if (bindingResult.hasErrors()) {
            return REGISTER_VIEW;
        }

        final UserEntity userEntity = userService.saveUser(userDto);
        String appUrl = request.getContextPath();
        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(userEntity, request.getLocale(), appUrl));

        return LOGIN_VIEW;
    }

    @GetMapping("/registrationConfirm")
    public String confirmRegistrationView(WebRequest request, Model model, @RequestParam("token") String token) {
        Locale locale = request.getLocale();
        VerificationTokenEntity verificationToken = userService.getVerificationToken(token);

        if (verificationToken == null) {
            String message = messages.getMessage("auth.message.invalidToken", null, locale);
            model.addAttribute("message", message);
            return "redirect:/badUser";
        }

        UserEntity user = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();

        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            String messageValue = messages.getMessage("auth.message.expired", null, locale);
            model.addAttribute("message", messageValue);
            return "redirect:/badUser";
        }

        user.setEnabled(1);
        userService.saveRegisteredUser(user);

        return "redirect:/charity/login";
    }

    @GetMapping("/badUser")
    public String badUserView() {
        return "badUser";
    }

}
