package com.snaplink.auth_service.feign;

import com.snaplink.auth_service.feign.dto.UserRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "user-service")
public interface UserClient {

    @PostMapping("/users/save")
    public void saveUser(UserRequestDTO userRequestDTO);

    @GetMapping("/users/auth/{authId}")
    public Long findUserWithAuth(@PathVariable Long authId);
}
