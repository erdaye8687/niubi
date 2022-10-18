package com.ljy.logindemo.config;

import com.ljy.logindemo.security.JwtAuthenticationTokenFilter;
import com.ljy.logindemo.security.RestAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author liangjy
 * @Description TODO
 * @createTime 2022/10/18
 */
@Slf4j
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final IgnoreUrlsConfig ignoreUrlsConfig;
    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
    @Autowired
    private LogoutSuccessHandlerImpl logoutSuccessHandler;

    /**
     * anyRequest          |   匹配所有请求路径
     * access              |   SpringEl表达式结果为true时可以访问
     * anonymous           |   匿名可以访问
     * denyAll             |   用户不能访问
     * fullyAuthenticated  |   用户完全认证可以访问（非remember-me下自动登录）
     * hasAnyAuthority     |   如果有参数，参数表示权限，则其中任何一个权限可以访问
     * hasAnyRole          |   如果有参数，参数表示角色，则其中任何一个角色可以访问
     * hasAuthority        |   如果有参数，参数表示权限，则其权限可以访问
     * hasIpAddress        |   如果有参数，参数表示IP地址，如果用户IP和参数匹配，则可以访问
     * hasRole             |   如果有参数，参数表示角色，则其角色可以访问
     * permitAll           |   用户可以任意访问
     * rememberMe          |   允许通过remember-me登录的用户访问
     * authenticated       |   用户登录后可访问
     */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        log.info("-------------------------------------------------------------");
        // 注解标记允许匿名访问的url
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = httpSecurity.authorizeRequests();
        // url白名单
        ignoreUrlsConfig.getUrls().forEach(url -> registry.antMatchers(url).permitAll());

        httpSecurity
                // CSRF禁用，因为不使用session
                .csrf().disable()
                // 基于token，所以不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // 认证失败处理类
                .exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint)
                .and()
                // 过滤请求
                .authorizeRequests()
                // 对于登录login 注册register 验证码captchaImage 允许匿名访问
//                .antMatchers("/login", "/register", "/captchaImage").anonymous()
                // 静态资源，可匿名访问
//                .antMatchers(HttpMethod.GET, "/", "/*.html", "/**/*.html", "/**/*.css", "/**/*.js", "/profile/**").permitAll()
//                .antMatchers("/swagger-ui.html", "/swagger-resources/**", "/webjars/**", "/*/api-docs", "/druid/**").permitAll()
                // 除上面外的所有请求全部需要鉴权认证
                .anyRequest().authenticated()
                .and()
//                .headers().frameOptions().disable();
                // 添加Logout filter
                .logout().logoutUrl("/logout").logoutSuccessHandler(logoutSuccessHandler)
                .and()
                // 添加JWT filter
                .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        // 添加Logout filter
//        httpSecurity.logout().logoutUrl("/logout").logoutSuccessHandler(logoutSuccessHandler);
        // 添加CORS filter
//        httpSecurity.addFilterBefore(corsFilter, JwtAuthenticationTokenFilter.class);
//        httpSecurity.addFilterBefore(corsFilter, LogoutFilter.class);


    }

    /**
     * 强散列哈希加密实现
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        log.info("-------bCryptPasswordEncoder-----------");
        return new BCryptPasswordEncoder();
    }

    /**
     * 身份认证接口
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        log.info("------身份认证-----{configure}");
        auth.userDetailsService(userDetailsService()).passwordEncoder(bCryptPasswordEncoder());
    }

    public static void main(String[] args) {
        //密码加密方式
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        System.out.println(bCryptPasswordEncoder.encode("admin123"));
    }
}
