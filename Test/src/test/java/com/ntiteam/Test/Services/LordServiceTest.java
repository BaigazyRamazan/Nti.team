package com.ntiteam.Test.Services;

import com.ntiteam.Test.Lord.Lord;
import com.ntiteam.Test.Lord.LordRepository;
import com.ntiteam.Test.Lord.LordService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LordServiceTest {

    @Autowired
    private LordService lordService;

    @MockBean
    private LordRepository lordRepository;

    @Test
    public void testCreateLord(){
        Lord caesar = new Lord("Julius Caesar",55);
        caesar.setLordId(1l);

        Mockito.when(lordRepository.save(caesar)).thenReturn(caesar);

        assertThat(lordService.createLord(caesar)).isEqualTo(caesar);
    }

}
