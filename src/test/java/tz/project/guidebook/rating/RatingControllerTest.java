package tz.project.guidebook.rating;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import tz.project.guidebook.comment.CommentController;
import tz.project.guidebook.landmark.Landmark;
import tz.project.guidebook.landmark.LandmarkController;
import tz.project.guidebook.rating.service.RatingService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class RatingControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private CommentController commentController;
    @MockBean
    private LandmarkController landmarkController;
    @MockBean
    private RatingService ratingService;

    @SneakyThrows
    @Test
    void create() {
        RatingRecord ratingRecord = new RatingRecord();
        ratingRecord.setRating(4.1);
        long landmarkId = 1L;
        Landmark landmark = new Landmark();
        landmark.setId(landmarkId);
        when(ratingService.addRating(ratingRecord, landmarkId)).thenReturn(landmark);

        String result = mockMvc.perform(post(String.format("/rating/%d", landmarkId))
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(ratingRecord)))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        assertEquals(objectMapper.writeValueAsString(landmark), result);
    }

    @SneakyThrows
    @Test
    void createWithoutRating_returnException() {
        RatingRecord ratingRecord = new RatingRecord();
        long landmarkId = 1L;
        Landmark landmark = new Landmark();
        landmark.setId(landmarkId);
        when(ratingService.addRating(ratingRecord, landmarkId)).thenReturn(landmark);

        mockMvc.perform(post(String.format("/rating/%d", landmarkId))
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(ratingRecord)))
                .andExpect(status().isBadRequest());
    }
}