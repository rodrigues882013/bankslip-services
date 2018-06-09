package br.com.blueaccount.bankslip.domain.model.constants;

public enum StatusEnumType {
    PENDING("PENDING"),
    CANCELED("CANCELED"),
    PAID("PAID");

    private final String name;

    StatusEnumType(String role) {
        name = role;
    }



    public String getName() {
        return name;
    }

}
