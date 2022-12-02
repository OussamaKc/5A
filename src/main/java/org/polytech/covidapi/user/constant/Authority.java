package org.polytech.covidapi.user.constant;

public class Authority {
    public static final String[] USER_AUTHORITIES = {"center:read", "meeting:create", };
    public static final String[] DOCTOR_AUTHORITIES = {"center:read", "meeting:create", "user:read", "user:update" };
    public static final String[] ADMIN_AUTHORITIES = {"center:read", "meeting:create",  "meeting:delete", "meeting:read", "user:read", "user:create", "user:update", };
    public static final String[] SUPER_ADMIN_AUTHORITIES = { "center:read", "center:create", "center:update", "center:delete","user:read", "user:create", "user:update", "user:delete" };
}
