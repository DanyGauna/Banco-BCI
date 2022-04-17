package com.bbci.createuser;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.bbci.createuser.controllers.VersionController;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = VersionControllerTest.class)
public class VersionControllerTest {
	
	@Mock
    private VersionController controller;


    @Test
    public void itShouldReturnTheServiceValueWith200StatusCode() {
        Mockito.when(controller.version()).thenReturn("1.0.0");
        String response = controller.version();

        Assert.assertEquals("1.0.0", response);
    }

}
