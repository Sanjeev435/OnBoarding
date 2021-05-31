/*
 * package com.code.onboarding.security;
 * 
 * import java.util.HashMap; import java.util.Map;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.http.HttpMethod; import
 * org.springframework.security.config.annotation.web.builders.HttpSecurity;
 * import org.springframework.security.config.annotation.web.configuration.
 * EnableWebSecurity; import
 * org.springframework.security.config.annotation.web.configuration.
 * WebSecurityConfigurerAdapter; import
 * org.springframework.security.config.annotation.web.configurers.
 * ExpressionUrlAuthorizationConfigurer.ExpressionInterceptUrlRegistry;
 * 
 * import com.code.onboarding.dto.UriAccessDTO; import
 * com.code.onboarding.util.RolesPropertiesUtil;
 * 
 * @Configuration
 * 
 * @EnableWebSecurity public class SecurityConfiguration extends
 * WebSecurityConfigurerAdapter {
 * 
 * @Autowired private RolesPropertiesUtil rolesPropertiesUtil;
 * 
 * private Map<String, UriAccessDTO> configuredRoles = new HashMap<>();
 * 
 * static {
 * 
 * }
 * 
 * @Override protected void configure(HttpSecurity http) throws Exception {
 * 
 * rolesPropertiesUtil.getUser(); rolesPropertiesUtil.getAdmin();
 * rolesPropertiesUtil.getManager();
 * 
 * 
 * http .requestMatchers() .antMatchers("/lti1p/**","/lti2p/**") .and()
 * .addFilterBefore(ltioAuthProviderProcessingFilter,
 * UsernamePasswordAuthenticationFilter.class)
 * .authorizeRequests().anyRequest().hasRole("LTI") .and().csrf().disable();
 * 
 * 
 * 
 * http.authorizeRequests() //.antMatchers(HttpMethod.POST,
 * "/account").permitAll() .antMatchers(HttpMethod.GET,
 * "/employee/*").access("hasRole('ROLE_ADMIN')")
 * .anyRequest().fullyAuthenticated() .and().httpBasic()
 * .and().csrf().disable(); }
 * 
 * 
 * private void getAllRoutesAccess() {
 * 
 * } }
 */