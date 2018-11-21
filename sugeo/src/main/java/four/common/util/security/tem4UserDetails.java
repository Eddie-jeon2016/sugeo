package four.common.util.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import egovframework.com.cmm.LoginVO;

/**
 * Created by Eddie Jeon on 2016-11-08.
 */
public class tem4UserDetails implements UserDetails {
    private static final long serialVersionUID = -4450269958885980297L;
    private String username;
    private String password;
    private LoginVO loginInfo;

    public tem4UserDetails(String userName, String password,LoginVO loginVO)
    {
        this.username = userName;
        this.password = password;
        this.loginInfo = loginVO;
    }

    public LoginVO getLoginInfo() {
        return loginInfo;
    }

    public void setLoginInfo(LoginVO loginInfo) {
        this.loginInfo = loginInfo;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
