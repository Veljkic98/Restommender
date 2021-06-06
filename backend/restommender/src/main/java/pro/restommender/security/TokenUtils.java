package pro.restommender.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import pro.restommender.model.Authority;
import pro.restommender.model.User;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Utility class for working with JSON Web Tokens.
 */
@Component
public class TokenUtils {

    @Value("spring-security-example")
    private String APP_NAME;

    @Value("somesecret")
    public String SECRET;

    @Value("180000000") // 5h
    private int EXPIRES_IN;

    @Value("Authorization")
    private String AUTH_HEADER;

    // private static final String AUDIENCE_UNKNOWN = "unknown";

    private static final String AUDIENCE_WEB = "web";

    private static final String AUDIENCE_MOBILE = "mobile";
    
    private static final String AUDIENCE_TABLET = "tablet";

    // JWT signature alghorithm
    private SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;

    /**
     * Function to generate JWT.
     * 
     * @param username of user
     * @param type is role of user
     * @return token
     */
    public String generateToken(String username, String type) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // Authority auth = (Authority) user.getAuthorities().toArray()[0];

        return Jwts.builder().setIssuer(APP_NAME).setSubject(username).setAudience(generateAudience())
                .setIssuedAt(new Date()).setExpiration(generateExpirationDate())
                .claim("role", type)
                .claim("id", user.getId())
                .signWith(SIGNATURE_ALGORITHM, SECRET).compact();
    }

    private String generateAudience() {

        // String audience = AUDIENCE_UNKNOWN;
        // if (device.isNormal()) {
        // audience = AUDIENCE_WEB;
        // } else if (device.isTablet()) {
        // audience = AUDIENCE_TABLET;
        // } else if (device.isMobile()) {
        // audience = AUDIENCE_MOBILE;
        // }
        return AUDIENCE_WEB;
    }

    private Date generateExpirationDate() {

        return new Date(new Date().getTime() + EXPIRES_IN);
    }

    /**
     * Function to refresh JWT.
     * 
     * @param token
     * @return
     */
    public String refreshToken(String token) {

        String refreshedToken;

        try {
            final Claims claims = this.getAllClaimsFromToken(token);
            claims.setIssuedAt(new Date());

            refreshedToken = Jwts.builder().setClaims(claims).setExpiration(generateExpirationDate())
                    .signWith(SIGNATURE_ALGORITHM, SECRET).compact();
        } catch (Exception e) {
            refreshedToken = null;
        }

        return refreshedToken;
    }

    public boolean canTokenBeRefreshed(String token) {

        return (!(this.isTokenExpired(token)) || this.ignoreTokenExpiration(token));
    }

    /**
     * Function to validate JWT.
     * 
     * @param token
     * @param userDetails
     * @return
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);

        return (username != null && username.equals(((User) userDetails).getEmail()));
    }

    public String getUsernameFromToken(String token) {

        String username;

        try {
            final Claims claims = this.getAllClaimsFromToken(token);

            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }

        return username;
    }

    public Date getIssuedAtDateFromToken(String token) {

        Date issueAt;

        try {
            final Claims claims = this.getAllClaimsFromToken(token);

            issueAt = claims.getIssuedAt();
        } catch (Exception e) {
            issueAt = null;
        }

        return issueAt;
    }

    public String getAudienceFromToken(String token) {

        String audience;

        try {
            final Claims claims = this.getAllClaimsFromToken(token);

            audience = claims.getAudience();
        } catch (Exception e) {
            audience = null;
        }

        return audience;
    }

    /**
     * Get date of token expiration.
     * 
     * @param token is JWT
     * @return token expiration date
     */
    public Date getExpirationDateFromToken(String token) {

        Date expiration;

        try {
            final Claims claims = this.getAllClaimsFromToken(token);

            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }

        return expiration;
    }

    public int getExpiredIn() {

        return EXPIRES_IN;
    }

    /**
     * Function to Get JWT from request.
     * 
     * @param request
     * @return JWT
     */
    public String getToken(HttpServletRequest request) {

        String authHeader = getAuthHeaderFromHeader(request);

        // JWT is forwarded through Autorization header in Bearer format
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }

        return null;
    }

    public String getAuthHeaderFromHeader(HttpServletRequest request) {

        return request.getHeader(AUTH_HEADER);
    }

    private Boolean isTokenExpired(String token) {

        final Date expiration = this.getExpirationDateFromToken(token);

        return expiration.before(new Date());
    }

    private Boolean ignoreTokenExpiration(String token) {

        String audience = this.getAudienceFromToken(token);

        return (audience.equals(AUDIENCE_TABLET) || audience.equals(AUDIENCE_MOBILE));
    }

    /**
     * Get all data from JWT.
     * 
     * @param token
     * @return Claims
     */
    private Claims getAllClaimsFromToken(String token) {

        Claims claims;

        try {
            claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            claims = null;
        }

        return claims;
    }
}
