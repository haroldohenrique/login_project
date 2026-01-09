package haroldohenrique.com.login_project.interfaces.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import haroldohenrique.com.login_project.application.dtos.EnderecoDTO;
import haroldohenrique.com.login_project.application.dtos.UserCreateDTO;
import haroldohenrique.com.login_project.interfaces.services.CreateUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ViewController {

    private final CreateUserService createUserService;

    @GetMapping("/")
    public String home() {

        return "layout/home";
    }

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        UserCreateDTO dto = new UserCreateDTO();
        dto.setEndereco(new EnderecoDTO());
        model.addAttribute("userCreateDTO", dto);
        return "auth/register";
    }

    @PostMapping("/register")
    public String save(
            @Valid @ModelAttribute("userCreateDTO") UserCreateDTO userCreateDTO,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            return "auth/register";
        }

        try {
            createUserService.execute(userCreateDTO);
            return "redirect:/login";
        } catch (Exception e) {
            model.addAttribute("error", "Não foi possível criar a conta. Tente novamente.");
            return "auth/register";
        }
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard/index";
    }
}
