package tz.project.guidebook.comment;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import tz.project.guidebook.comment.service.CommentService;
import tz.project.guidebook.landmark.LandmarkController;
import tz.project.guidebook.rating.RatingController;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class CommentControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private CommentService commentService;
    @MockBean
    private LandmarkController landmarkController;
    @MockBean
    private RatingController ratingController;

    @SneakyThrows
    @Test
    void create() {
        Comment comment = new Comment();
        comment.setDescription("description");
        long landmarkId = 1L;
        when(commentService.addComment(comment, landmarkId)).thenReturn(comment);

        String result = mockMvc.perform(post(String.format("/comment/%d", landmarkId))
                    .contentType("application/json")
                    .content(objectMapper.writeValueAsString(comment)))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        assertEquals(objectMapper.writeValueAsString(comment), result);
    }

    @SneakyThrows
    @Test
    void createWithoutDescription_returnException() {
        Comment comment = new Comment();
        long landmarkId = 1L;
        when(commentService.addComment(comment, landmarkId)).thenReturn(comment);

        mockMvc.perform(post(String.format("/comment/%d", landmarkId))
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(comment)))
                .andExpect(status().isBadRequest());
    }
}