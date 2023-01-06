package com.humam;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.humam.model.Role;
import com.humam.model.User;
import com.humam.repository.RolesRepo;
import com.humam.repository.UserRepo;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class BlogsApplication implements CommandLineRunner{

	@Autowired
	private RolesRepo roleRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserRepo userRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(BlogsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Role role1=new Role();
		role1.setId(1);
		role1.setRole("ROLE_ADMIN");
		
		Role role2=new Role();
		role2.setId(2);
		role2.setRole("ROLE_USER");
		
		Role role3=new Role();
		role3.setId(3);
		role3.setRole("ROLE_MODERATOR");
		
//		roleRepo.save(role1);
//		roleRepo.save(role2);
//		roleRepo.save(role3);
		
		System.out.println(passwordEncoder.encode("humam"));
		
		User user=new User();
		user.setId(1);
		user.setAbout("first user");
		user.setName("Humam");
		user.setUsername("humam@gmail.com");
		user.setPassword(passwordEncoder.encode("humam"));
		
		Set<Role> roles=new HashSet<>();
		roles.add(role1);
		
		user.setRoles(roles);
//		userRepo.save(user);
		
		
   }
}
