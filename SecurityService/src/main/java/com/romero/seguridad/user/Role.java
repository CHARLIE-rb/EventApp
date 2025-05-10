package com.romero.seguridad.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.romero.seguridad.user.Permisos.*;

@RequiredArgsConstructor
public enum Role {
    USER(Collections.emptySet()),

    ADMIN(Set.of(ADMIN_READ, ADMIN_CREATE, ADMIN_UPDATE, ADMIN_DELETE,
            MANAGER_READ, MANAGER_CREATE, MANAGER_UPDATE, MANAGER_DELETE)),

    GESTOR(Set.of(GESTOR_READ, GESTOR_CREATE, GESTOR_UPDATE, GESTOR_DELETE)),

    RESPONSABLE_EQUIPO(Set.of(TEAM_LEAD_READ, TEAM_LEAD_UPDATE)),

    EMPLEADO(Set.of(EMPLOYEE_READ)),

    CEO(Set.of(
            ADMIN_READ, ADMIN_CREATE, ADMIN_UPDATE, ADMIN_DELETE,
            MANAGER_READ, MANAGER_CREATE, MANAGER_UPDATE, MANAGER_DELETE,
            GESTOR_READ, GESTOR_CREATE, GESTOR_UPDATE, GESTOR_DELETE,
            TEAM_LEAD_READ, TEAM_LEAD_UPDATE,
            EMPLOYEE_READ,
            CEO_READ, CEO_CREATE, CEO_UPDATE, CEO_DELETE
    ));

    @Getter
    private final Set<Permisos> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = permissions.stream()
                .map(p -> new SimpleGrantedAuthority(p.getPermiso()))
                .collect(Collectors.toList());
        // y también el ROLE_*
        authorities.add(new SimpleGrantedAuthority("ROLE_" + name()));
        return authorities;
    }
}

