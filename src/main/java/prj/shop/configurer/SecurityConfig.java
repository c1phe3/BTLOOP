package prj.shop.configurer; // Đảm bảo package khớp với dự án của bạn

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .anyRequest().permitAll() // Cho phép tất cả các yêu cầu mà không cần xác thực
            .and()
            .csrf().disable() // Vô hiệu hóa CSRF nếu không cần thiết
            .formLogin().disable(); // Vô hiệu hóa form đăng nhập mặc định
    }
}
