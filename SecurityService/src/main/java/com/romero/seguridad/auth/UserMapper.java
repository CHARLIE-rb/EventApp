package com.romero.seguridad.auth;

import com.romero.seguridad.user.User;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring", uses = {})
public interface UserMapper {
    @Mapping(target = "password",
            expression = "java(passwordEncoder.encode(request.getPassword()))")
    User toUser(RegisterRequest request, @Context PasswordEncoder passwordEncoder);

}
