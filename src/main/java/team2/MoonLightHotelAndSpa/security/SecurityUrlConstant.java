package team2.MoonLightHotelAndSpa.security;

public class SecurityUrlConstant {

    protected static final String ADMIN = "ROLE_ADMIN";
    protected static final String CLIENT = "ROLE_CLIENT";
    protected static final String[] PUBLIC_URL_POST = {
            "/rooms", "/rooms/**",
            "/users", "/users/token", "/users/forgot",
            "/contacts",
            "/screens/{id}/findFreeSeatsByScreenIdAndDate"};
    protected static final String[] PUBLIC_URL_GET = {
            "/rooms/**",
            "/capture/room",
            "/capture/table",
            "/swagger/**",
            "/v3/api-docs",
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/swagger-ui.html",
            "/users/check",
            "/users/transfers"
    };
    protected static final String[] PUBLIC_URL_PUT = {"/rooms/**"};
    protected static final String[] PUBLIC_URL_DELETE = {"/rooms/**"};
    protected static final String[] PROTECTED_URL_POST = {
            "/users/reset",
            "/tables",
            "/screens"
    };
    protected static final String[] PROTECTED_URL_POST_CLIENT = {
            "/users/reset", "/users/reset",
    };
    protected static final String[] PROTECTED_URL_GET = {
            "/users", "/users/{id}",
            "/users/reservations", "/users/{uid}/reservations", "/users/{uid}/reservations/{rid}",
            "/users/screens/reservations", "/users/{uid}/screens/reservations", "/users/{uid}/screens/reservation/{rid}",
            "/screens/{id}",
            "/screens/{id}/reservations", "/screens/{id}/reservations/{rid}", "/users/{uid}/transfers/{tid}",
            "/users/{uid}/transfers"
    };
    protected static final String[] PROTECTED_URL_GET_CLIENT = {
            "/users/profile",
            "/users/{uid}/reservations", "/users/{uid}/reservations/{rid}"
    };
    protected static final String[] PROTECTED_URL_PUT = {
            "/users/{id}",
            "/tables/{id}",
            "/screens/{id}",
            "/screens/{id}/reservations/{rid}"
    };
    protected static final String[] PROTECTED_URL_PUT_CLIENT = {
            "/**"};
    protected static final String[] PROTECTED_URL_DELETE = {
            "/users/{id}",
            "/rooms/{id}", "/rooms/{id}/reservation/{rid}",
            "/tables/{id}",
            "/screens/{id}", "/screens/{id}/reservations/{rid}"
    };
    protected static final String[] PROTECTED_URL_DELETE_CLIENT = {
            "/**"
    };
}
