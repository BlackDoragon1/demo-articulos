package mx.com.gapsi.jega.demo_articulos;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import mx.com.gapsi.jega.demo_articulos.entities.Articulo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;


import static org.hamcrest.Matchers.is;

@SpringBootTest
@AutoConfigureMockMvc
class ArticuloTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnOkWhenGetAllArticles() throws Exception{
        mockMvc.perform(get("/api/articulos/"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnOkWhenGetArticleById() throws Exception{
        mockMvc.perform(get("/api/articulos/{id}", "A123456789"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnErrorWhenArticuloNotFound() throws Exception{
        mockMvc.perform(get("/api/articulos/{id}", "XYZ123"))
        .andExpect(status().isNotFound());
    }

    @Test
    void shouldOnlyUpdateDescripcionAndModelo() throws Exception{
        MvcResult mvcResult = mockMvc.perform(get("/api/articulos/{id}", "A123456789"))
                                     .andExpect(status().isOk())
                                     .andReturn();
        
        String jsonResponse = mvcResult.getResponse().getContentAsString();

        Articulo articuloDB = objectMapper.readValue(jsonResponse, Articulo.class);
        articuloDB.setDescripcion("descripcion prueba");
        articuloDB.setModelo("TZZ2023");

        String requestBody = objectMapper.writeValueAsString(articuloDB);

        mockMvc.perform(put("/api/articulos/{id}", "A123456789")
                .contentType("application/json")
                .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.descripcion", is("descripcion prueba")))
                .andExpect(jsonPath("$.modelo", is("TZZ2023")));
    }
}
