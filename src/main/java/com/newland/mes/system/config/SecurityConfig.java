package com.newland.mes.system.config;

import com.newland.mes.system.entity.User;
import com.newland.mes.system.service.AdminService;
import com.newland.mes.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AdminService adminService;
    @Autowired
    private RoleService roleService;

    @Autowired
    private MyAccessDeniedHandler myAccessDeniedHandler;
    @Autowired
    private MyAuthenticationEntryPoint myAuthenticationEntryPoint;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers()
                .frameOptions()
                .disable();

        //使用JWT,不需要csrf
        http.csrf()
                .disable();
        http.authorizeRequests()//指定哪些方法不需要授权
                .antMatchers("/test/*","/Mylogin","/Mylogin.html","/login","/logout","/images/*","/js/*","/layui/**","/**/*.css","/*.ico")
                .permitAll().
                anyRequest().authenticated()
                 .and()
                .formLogin()//表示转到我们自己的登陆表单
                //指定登录页的路径
                .loginPage("/Mylogin")
                //指定自定义form表单请求的路径
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/dashboard")
                //必须允许所有用户访问我们的登录页（例如未验证的用户，否则验证流程就会进入死循环）
                //这个formLogin().permitAll()方法允许所有用户基于表单登录访问/login这个page。
                .permitAll();
        http.logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/Mylogin");
    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        //配置静态文件不需要认证
        web.ignoring().antMatchers( "/static/**","/test/*");
    }
    @Bean
    public UserDetailsService userDetailsService(){
        return username->{
            if(username==null || username.length()==0){
                throw new BadCredentialsException("用户名不能为空！");
            }
          User user=adminService.getUserByUserName(username);
            if (user == null) {
                throw new BadCredentialsException("帐号不存在，请重新输入");
            }
            else{
                user.setAuthorities(roleService.mapToGrantedAuthorities(user));
                return user;
            }

        };
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}
