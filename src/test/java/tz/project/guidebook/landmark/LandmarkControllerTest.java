package tz.project.guidebook.landmark;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import tz.project.guidebook.comment.CommentController;
import tz.project.guidebook.landmark.dto.LandmarkFindParams;
import tz.project.guidebook.landmark.service.LandmarkService;
import tz.project.guidebook.rating.RatingController;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class LandmarkControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private CommentController commentController;
    @MockBean
    private LandmarkService landmarkService;
    @MockBean
    private RatingController ratingController;

    @SneakyThrows
    @Test
    void create() {
        Landmark landmark = new Landmark();
        landmark.setName("name");
        landmark.setLocationLat(45.5);
        landmark.setLocationLon(45.5);
        when(landmarkService.create(landmark)).thenReturn(landmark);

        String result = mockMvc.perform(post("/landmark")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(landmark)))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        assertEquals(objectMapper.writeValueAsString(landmark), result);
    }

    @SneakyThrows
    @Test
    void createEmptyLandmark_returnBadRequest() {
        Landmark landmark = new Landmark();
        when(landmarkService.create(landmark)).thenReturn(landmark);

        mockMvc.perform(post("/landmark")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(landmark)))
                .andExpect(status().isBadRequest());
    }

    @SneakyThrows
    @Test
    void find() {
        LandmarkFindParams params = new LandmarkFindParams();

        mockMvc.perform(get("/landmark"))
                .andDo(print())
                .andExpect(status().isOk());

        verify(landmarkService).findByParams(params);
    }

    @SneakyThrows
    @Test
    void findById() {
        long landmarkId = 1L;

        mockMvc.perform(get(String.format("/landmark/%d", landmarkId)))
                .andDo(print())
                .andExpect(status().isOk());

        verify(landmarkService).findById(landmarkId);
    }

    @SneakyThrows
    @Test
    void findByIdWithComments() {
        long landmarkId = 1L;

        mockMvc.perform(get(String.format("/landmark/%d/comments", landmarkId)))
                .andDo(print())
                .andExpect(status().isOk());

        verify(landmarkService).findByIdWithComments(landmarkId);
    }
}