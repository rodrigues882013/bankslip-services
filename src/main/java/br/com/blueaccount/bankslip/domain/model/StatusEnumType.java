package br.com.blueaccount.bankslip.domain.model;

import org.apache.commons.lang3.StringUtils;

public enum StatusEnumType {
    PENDING("PENDING"),
    CANCELED("CANCELED"),
    PAID("PAID");

    private final String name;

    StatusEnumType(String role) {
        name = role;
    }

    public static StatusEnumType getStatusEnumTypeFromString(String name) {

        StatusEnumType[] statusEnumTypes = StatusEnumType.values();

        for (StatusEnumType statusEnumType : statusEnumTypes) {
            if(StringUtils.equalsIgnoreCase(statusEnumType.getName(), name)) {
                return statusEnumType;
            }
        }

        return null;
    }

    public String getName() {
        return name;
    }

}
