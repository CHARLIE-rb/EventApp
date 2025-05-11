package com.romero.seguridad.auth;

import com.romero.seguridad.user.User;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring", uses = {})
public abstract class UserMapper {
    @Autowired
    protected PasswordEncoder passwordEncoder;

    @Mapping(target = "password", ignore = true)
    public abstract User toUser(RegisterRequest request);

    @AfterMapping
    protected void encodePassword(RegisterRequest request, @MappingTarget User user) {
        user.setPassword(passwordEncoder.encode(request.getPassword()));
    }

}
