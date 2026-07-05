package com.example.SISproject.features.landing;

import com.example.SISproject.features.auth.dto.MeResponse;
import com.example.SISproject.models.UserModel;
import com.example.SISproject.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class LandingService {

    private final UserRepository userRepository;

    public LandingService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public MeResponse me(String email){
        UserModel user = userRepository.findUserByEmail(email);
        if (user == null) return null;
        return new MeResponse(
                user.getName(),
                user.getEmail(),
                user.getActivityStatus()
        );
    }
}
