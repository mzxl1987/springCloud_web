package com.miicrown.enums;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.miicrown.SpringCloudWebApplication;
import com.miicrown.netty.protocol.ProtocolType;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=SpringCloudWebApplication.class)
@Slf4j
public class EnumsTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		
		log.info(" ProtocolType.LOGIN = {} ",ProtocolType.LOGIN);
		
	}

}
