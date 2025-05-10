package com.romero.seguridad.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permisos {

    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete"),
    MANAGER_READ("management:read"),
    MANAGER_UPDATE("management:update"),
    MANAGER_CREATE("management:create"),
    MANAGER_DELETE("management:delete"),

    // permisos de CEO
    CEO_READ("ceo:read"),
    CEO_CREATE("ceo:create"),
    CEO_UPDATE("ceo:update"),
    CEO_DELETE("ceo:delete"),

    // permisos de Gestor
    GESTOR_READ("gestor:read"),
    GESTOR_CREATE("gestor:create"),
    GESTOR_UPDATE("gestor:update"),
    GESTOR_DELETE("gestor:delete"),

    // permisos de Responsable de Equipo
    TEAM_LEAD_READ("team_lead:read"),
    TEAM_LEAD_UPDATE("team_lead:update"),

    // permisos de Empleado
    EMPLOYEE_READ("employee:read");
    ;

    @Getter
    private final String permiso;
}
