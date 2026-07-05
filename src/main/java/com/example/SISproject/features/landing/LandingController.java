package com.example.SISproject.features.landing;

import com.example.SISproject.features.auth.dto.AuthResponse;
import com.example.SISproject.features.auth.dto.MeResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/landing")
public class LandingController {
    private final LandingService landingService;

    public LandingController(LandingService landingService) {
        this.landingService = landingService;
    }


    @PostMapping("/logout")
    public ResponseEntity<AuthResponse> logout(@AuthenticationPrincipal UserDetails userDetails, HttpServletResponse response){
        deleteCookie(response);
        return ResponseEntity.ok(new AuthResponse("Successfully Logged Out!"));
    }

    @GetMapping("/me")
    public ResponseEntity<MeResponse> me(@AuthenticationPrincipal UserDetails userDetails){
        MeResponse meResponse = landingService.me(userDetails.getUsername());
        return ResponseEntity.ok(meResponse);
    }

    private void deleteCookie(HttpServletResponse response){
        Cookie jwtCookie = new Cookie("jwt",null);
        jwtCookie.setPath("/");
        jwtCookie.setMaxAge(0);
        jwtCookie.setHttpOnly(true);
        jwtCookie.setSecure(true);
        response.addCookie(jwtCookie);
    }
}
