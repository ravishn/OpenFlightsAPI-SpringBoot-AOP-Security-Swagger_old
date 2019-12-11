package com.foxtel.spring.demo.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Unit-tests for Controller class
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FlightControllerTest {

    @Autowired
    private MockMvc mvc;

    /**
     * Tests for flight in endpoint
     * @throws Exception
     */
    @Test
    @WithUserDetails("admin")
    public void getAllFlightsIn_EmptyResponse_Test() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/flights/in/airport/1"+ UUID.randomUUID().toString()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]")));
    }

    @Test
    @WithUserDetails("admin")
    public void getAllFlightsIn_Ok_Test() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/flights/in/airport/2290").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    /**
     * Tests for flights out endpoint
     * @throws Exception
     */
    @Test
    @WithUserDetails("admin")
    public void getAllFlightsOut_EmptyResponse_Test() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/flights/out/airport/1"+ UUID.randomUUID().toString()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]")));
    }

    @Test
    @WithUserDetails("admin")
    public void getAllFlightsOut_Ok_Test() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/flights/out/airport/2290").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    /**
     * Tests for flights between airports endpoint
     * @throws Exception
     */
    @Test
    @WithUserDetails("admin")
    public void getAllFlightBetweenAirports_EmptyResponse_Test() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get("/flights/route/source/1/dest/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]")));
    }

    @Test
    @WithUserDetails("admin")
    public void getAllFlightBetweenAirports_Ok_Test() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/flights/route/source/3393/dest/3361").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    
    @Test
    @WithUserDetails("admin")
    public void getAllFlightsBetweenAirports_NotFound_Test() throws Exception {
    	
    	mvc.perform(MockMvcRequestBuilders.get("/flights/route/foo").accept(MediaType.APPLICATION_JSON))
    			.andExpect(status().isNotFound());
    }

}
