package com.example.springbootcommunityweb.config;

import com.example.springbootcommunityweb.domain.enums.SocialType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.CompositeFilter;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.example.springbootcommunityweb.domain.enums.SocialType.*;

@Configuration
// 웹에서 시큐리티 기능을 사용하겠다는 어노테이션
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    // 자동설정 사용할 수도 있지만 요청,권한,기타 설정에 대해서는 필수적으로 최적화한 설정이 들어가야 함
    // 그러기 위해 configure 오버라이드해서 설정
    protected void configure(HttpSecurity http) throws Exception{
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        http
                //인증 메커니즘을, 요청한 HttpServletRequest 기반으로 설정
                .authorizeRequests()
                    //요청 패턴 설정 및 누구나 접근허용
                    .antMatchers("/","/oauth2/**","/login/**","/css/**","images/**","/js/**","/console/**").permitAll()
                    .antMatchers("/facebook").hasAuthority(FACEBOOK.getRoleType())
                    .antMatchers("/google").hasAuthority(GOOGLE.getRoleType())
                    .antMatchers("/kakao").hasAuthority(KAKAO.getRoleType())
                    // 설정한 요청 이외의 리퀘스트 요청 허용, 인증된 사용자만 요청가능
                    .anyRequest().authenticated()
                .and()
                    // oauth2Login()만 추가하면 구글,페이스북 OAuth2인증방식 기본적용, 인증시 /oauth2/** 접근하므로 권한허용필요
                    .oauth2Login()
                    .defaultSuccessUrl("/loginSuccess")
                    .failureUrl("/loginFailure")
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
                    .csrf().disable();
    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository(
            OAuth2ClientProperties oAuth2ClientProperties, @Value(
            "${custom.oauth2.kakao.client-id}") String kakaoClientId) {
        List<ClientRegistration> registrations = oAuth2ClientProperties
                .getRegistration().keySet().stream()
                .map(client -> getRegistration(oAuth2ClientProperties, client))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());


    }

    private Object getRegistration(OAuth2ClientProperties oAuth2ClientProperties, String client) {
    }


}
