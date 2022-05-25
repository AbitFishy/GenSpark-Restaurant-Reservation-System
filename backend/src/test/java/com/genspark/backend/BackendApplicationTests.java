package com.genspark.backend;

import com.genspark.backend.Dao.UserAccountDao;
import com.genspark.backend.Entity.UserAccount;
import com.genspark.backend.Service.UserAccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestPropertySource
class BackendApplicationTests {

	@Autowired
	private UserAccountDao userAccountDao;

	@Autowired
	private UserAccountService userAccountService;

	@Test
	void contextLoads() {
	}

	@BeforeEach
	void before(){
		userAccountDao.deleteAll();
	}

	@Test
	void authenticateUser(){
		UserAccount account = new UserAccount(0,"Robert",
				"45612389",userAccountService.hashNewPassword("sad;fjASDF156"), "sldfj@asdf.asdf");
		userAccountDao.saveAndFlush(account);
		account = new UserAccount(0,"Jessica",
				"45612389",userAccountService.hashNewPassword("asdfSDF"), "sldfj@asdf.asdf");
		userAccountDao.saveAndFlush(account);


	}

}
