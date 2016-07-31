package com.lenicliu.spring.boot;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class ApplicationIntegrationTests {

    @Autowired
    private ProfileRepository profileRepository;

    @Test
    public void testProfileRepository() {
        List<Profile> profiles = profileRepository.findAll();
        Assert.assertTrue(profiles.size() == 2);
    }
}
