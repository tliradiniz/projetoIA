	package com.tdiniz.backend;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.tdiniz.backend.dateUtils.DateUtil;


@SpringBootApplication
public class SpringBootSecurityJwtApplication {

	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootSecurityJwtApplication.class, args);
	}
	
	@PostConstruct
    private void initDb() {
		
		DateUtil dt = new DateUtil();
		
        System.out.println(String.format("****** Creating table: %s, and Inserting test data ******", "Users"));

        String sqlStatements[] = {
            "drop table users if exists",
            "create table users("
            + "id serial,"
            + " name varchar(255) not null,"
            + " username varchar(30) not null,"
            + " password varchar(100) not null,"
            + " createdDate date not null,"
            + " updatedDate date,"
            + " email varchar(50) not null,"
            + " isAdmin varchar(1) not null)",
            " insert into users(name, username, password, createdDate, email, isAdmin) values('Admin','admin','"+encoder.encode("password")+"','"+ dt.getDateToday() + "','admin@admin.com','Y')"
        };

        Arrays.asList(sqlStatements).stream().forEach(sql -> {
            System.out.println(sql);
            jdbcTemplate.execute(sql);
        });

        System.out.println(String.format("****** Fetching from table: %s ******", "Users"));
        jdbcTemplate.query("select id,name,username,password,createdDate,updatedDate, email,isAdmin from users",
            new RowMapper<Object>() {
                @Override
                public Object mapRow(ResultSet rs, int i) throws SQLException {
                    System.out.println(String.format("id:%s, \nname: %s, \nusername: %s, \npassword: %s, \ncreatedDate: %s, \nupdatedDate: %s, \nemail: %s, \nisAdmin: %s",
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("createdDate"),
                        rs.getString("updatedDate"),
                        rs.getString("email"),
                        rs.getString("isAdmin")));
                    return null;
                }
            });
    }
    
    

    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server inMemoryH2DatabaseServer() throws SQLException {
        return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9091");
    }

}
