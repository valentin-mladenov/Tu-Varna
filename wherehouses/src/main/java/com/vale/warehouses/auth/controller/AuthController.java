package com.vale.warehouses.auth.controller;

import com.vale.warehouses.auth.models.TokenEntity;
import com.vale.warehouses.auth.service.AuthService;
import com.vale.warehouses.auth.service.interfaces.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserServiceInterface userService;

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public TokenEntity login(
            @RequestParam("username") final String username,
            @RequestParam("password") final String password
    ) {
        TokenEntity token = authService.login(username, password);

        token.setUser(null);

        return token;
    }

//    @GetMapping("/registration")
//    public String registration(Model model) {
//        model.addAttribute("userForm", new UserEntity());
//
//        return "registration";
//    }
//
//    @PostMapping("/registration")
//    public ResponseEntity<Boolean> registration(@RequestBody UserEntity userForm/* , BindingResult bindingResult */) {
//        //userValidator.validate(userForm, null);
//
////        if (bindingResult.hasErrors()) {
////            return "registration";
////        }
//
//        //userService.save(userForm);
//
//        UsernamePasswordAuthenticationToken a = securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());
//
//        return ResponseEntity.ok().body(true);
//    }

//    @PostMapping("/login")
//    public ResponseEntity<Boolean> login1(@RequestBody UserEntity userForm/* , BindingResult bindingResult */) {
//        //userValidator.validate(userForm, null);
//
////        if (bindingResult.hasErrors()) {
////            return "registration";
////        }
//
//        userService.save(userForm);
//
//        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());
//
//        return ResponseEntity.ok().body(true);
//    }

//    @GetMapping("/login")
//    public ResponseEntity<Boolean> login(Model model, String error) {
//        if (error != null) {
//            model.addAttribute("error", "Your username and/or password is invalid.");
//        }
////        if (logout != null) {
////            model.addAttribute("message", "You have been logged out successfully.");
////        }
//
//        return ResponseEntity.ok().body(true);
//    }

//    @GetMapping("/logout")
//    public ResponseEntity<Boolean> logout(Model model, String error) {
//        if (error != null) {
//            model.addAttribute("error", "Your username and/or password is invalid.");
//        }
////        if (logout != null) {
////            model.addAttribute("message", "You have been logged out successfully.");
////        }
//
//        return ResponseEntity.ok().body(true);
//    }
//
//    @GetMapping({"/", "/welcome"})
//    public String welcome(Model model) {
//        return "welcome";
//    }
}
