package test.miicrown.thread.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.miicrown.SpringCloudWebApplication;
import com.miicrown.config.ApplicationContextProvider;
import com.miicrown.thread.service.AsyncTaskService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=SpringCloudWebApplication.class)
public class AsyncTaskServiceTest {

	private Logger log = LoggerFactory.getLogger(AsyncTaskServiceTest.class);
	
	@Test
	public void show() throws Exception{
		
		AsyncTaskService s = ApplicationContextProvider.getBean(AsyncTaskService.class);
		s.executeMinus();
		s.executePlus();
	}
	
}
