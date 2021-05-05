package config;

import com.example.springbootcommunityweb.oauth.ClientResources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
// 웹에서 시큐리티 기능을 사용하겠다는 어노테이션
@EnableWebSecurity 
@EnableOAuth2Client
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private OAuth2ClientContext oAuth2ClientContext;

    @Override
    // 자동설정 사용할 수도 있지만 요청,권한,기타 설정에 대해서는 필수적으로 최적화한 설정이 들어가야 함
    // 그러기 위해 configure 오버라이드해서 설정
    protected void configure(HttpSecurity http) throws Exception{
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        http
                //인증 메커니즘을, 요청한 HttpServletRequest 기반으로 설정
                .authorizeRequests()
                    //요청 패턴 설정 및 누구나 접근허용
                    .antMatchers("/","/login/**","/css/**","imeges/**","/js/**","/console/**").permitAll()
                    // 설정한 요청 이외의 리퀘스트 요청 허용, 인증된 사용자만 요청가능
                    .anyRequest().authenticated()
                .and()
                    // 헤더 설정 안할경우 디폴트, XFrameOptionHeaderWriter의 최적화 설정 불허용
                    .headers().frameOptions().disable()
                .and()
                    .exceptionHandling()
                    //인증의 진입점, 인증되지 않은 사용자가 허용되지 않은 경로로 리퀘스트 요청 시 '/login'으로 이동시킴
                    .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"))
                .and()
                    //로그인 설정
                    .formLogin()
                    //로그인 성공 시 설정 경로로 이동 
                    .successForwardUrl("/board/list")
                .and()
                    //로그아웃 설정
                    .logout()
                    //로그아웃 수행될 url
                    .logoutUrl("/logout")
                    //로그아웃 이후 이동할 url
                    .logoutSuccessUrl("/")
                    //로그아웃 후 삭제할 쿠키값
                    .deleteCookies("JSESSIONID")
                    //설정된 세션 무효화 true
                    .invalidateHttpSession(true)
                .and()
                    //첫 번째 인자보다 먼저 시작될 필터 두번째 인자에 등록
                    .addFilterBefore(filter, CsrfFilter.class)
                    .addFilterBefore(oau)
                    .csrf().disable();
    }

    @Bean
    public FilterRegistrationBean oauth2ClientFilterRegistration(
            OAuth2ClientContextFilter filter) {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(filter);
        registration.setOrder(-100);
        return registration;
    }

    private Filter oauth2Filter(){
        Com
    }


    @Bean
    @ConfigurationProperties("facebook")
    public ClientResources facebook(){
        return new ClientResources();
    }

    @Bean
    @ConfigurationProperties("google")
    public ClientResources google(){
        return new ClientResources();
    }

    @Bean
    @ConfigurationProperties("kakao")
    public ClientResources kakao(){
        return new ClientResources();
    }
}
