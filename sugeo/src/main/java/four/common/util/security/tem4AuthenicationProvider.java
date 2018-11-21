package four.common.util.security;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import egovframework.com.cmm.LoginVO;
import egovframework.com.uat.uia.service.EgovLoginService;
import egovframework.com.uat.uia.web.EgovLoginController;

/**
 * Created by Eddie Jeon on 2016-11-08.
 */
public class tem4AuthenicationProvider implements AuthenticationProvider {
    private static final Logger logger = LoggerFactory.getLogger(EgovLoginController.class);
    
    /** EgovLoginService */
	@Resource(name = "loginService")
	private EgovLoginService loginService;
	
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String user_id = authentication.getPrincipal().toString();
        String user_pw = authentication.getCredentials().toString();

        logger.info("사용자가 입력한 로그인정보입니다. {}", user_id + "/" + user_pw);
        System.out.println("사용자가 입력한 로그인정보입니다. user_id["+user_id+"]user_pw["+user_pw+"]");
        
    	//로그인 중복여부 조회 로직
        int cnt = 1;
        
        System.out.println("로그인 ID 수 ["+cnt+"]");
        //아이디가 존재하지 않는 경우
        if(cnt != 1) {
        	throw new BadCredentialsException("ERROR0000||"+user_id);
        }

        LoginVO loginVO = new LoginVO();
        loginVO.setId(user_id);
        loginVO.setPassword(user_pw);
        
        LoginVO logcheckedVO = new LoginVO();
        try {
        	
        	logcheckedVO = loginService.actionLogin(loginVO);
			// 로그인 로그 로직 추가
        	
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        
        if(logcheckedVO.getId() == null){
        	System.out.println("@@@@@@@@@@@@@@@@@@@@@@@결과 null???");
        	throw new BadCredentialsException("ERROR0001||"+user_id);
        }
        logger.info("정상 로그인입니다.");
        
        List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
        roles.add(new SimpleGrantedAuthority(logcheckedVO.getRole()));

        UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(user_id, user_pw, roles);
        result.setDetails(new tem4UserDetails(user_id, user_pw,logcheckedVO));
        return result;
    }
}
