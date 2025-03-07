package com.post.Post.service;

import com.post.Post.dto.PostDTO;
import com.post.Post.model.Post;
import com.post.Post.repository.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PostServiceImplTest {

    private PostRepository postRepository;
    private ModelMapper modelMapper;
    private PostServiceImpl postService;

    private Post post;
    private PostDTO postDTO;

    @BeforeEach
    void setUp() {
        postRepository = mock(PostRepository.class);
        modelMapper = mock(ModelMapper.class);
        postService = new PostServiceImpl(postRepository, modelMapper);

        post = Post.builder()
                .id(1L)
                .title("Test Title")
                .content("Test Content")
                .author("Author")
                .build();

        postDTO = PostDTO.builder()
                .id(1L)
                .title("Test Title")
                .content("Test Content")
                .author("Author")
                .build();
    }

    @Test
    void getAllPosts_shouldReturnListOfPostDTO_whenPostsExist() {
        // Crear algunos objetos Post
        Post post1 = Post.builder()
                .id(1L)
                .title("Title 1")
                .content("Content 1")
                .author("Author 1")
                .build();

        Post post2 = Post.builder()
                .id(2L)
                .title("Title 2")
                .content("Content 2")
                .author("Author 2")
                .build();

        // Crear una lista de PostDTO
        PostDTO postDTO1 = PostDTO.builder()
                .id(1L)
                .title("Title 1")
                .content("Content 1")
                .author("Author 1")
                .build();

        PostDTO postDTO2 = PostDTO.builder()
                .id(2L)
                .title("Title 2")
                .content("Content 2")
                .author("Author 2")
                .build();

        // Simular la respuesta del repositorio
        when(postRepository.findAll()).thenReturn(Arrays.asList(post1, post2));
        when(modelMapper.map(post1, PostDTO.class)).thenReturn(postDTO1);
        when(modelMapper.map(post2, PostDTO.class)).thenReturn(postDTO2);

        // Llamar al método y verificar el resultado
        List<PostDTO> result = postService.getAllPosts();

        assertNotNull(result);
        assertEquals(2, result.size());  // Debería haber 2 elementos
        assertEquals(postDTO1.getId(), result.get(0).getId());
        assertEquals(postDTO2.getTitle(), result.get(1).getTitle());

        verify(postRepository, times(1)).findAll();  // Verificar que se llama al repositorio
    }


    @Test
    void getPostById_shouldReturnPostDTO_whenPostExists() {

        when(postRepository.findById(1L)).thenReturn(Optional.of(post));
        when(modelMapper.map(post, PostDTO.class)).thenReturn(postDTO);

        PostDTO result = postService.getPostById(1L);

        assertNotNull(result);
        assertEquals(postDTO.getId(), result.getId());
        assertEquals(postDTO.getTitle(), result.getTitle());
        assertEquals(postDTO.getContent(), result.getContent());
        assertEquals(postDTO.getAuthor(), result.getAuthor());

        verify(postRepository, times(1)).findById(1L);
    }

    @Test
    void getPostById_shouldThrowException_whenPostDoesNotExist() {
        when(postRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> postService.getPostById(1L));

        verify(postRepository, times(1)).findById(1L);
    }

    @Test
    void createPost_shouldReturnPostDTO_whenPostIsCreated() {
        when(modelMapper.map(postDTO, Post.class)).thenReturn(post);
        when(postRepository.save(post)).thenReturn(post);
        when(modelMapper.map(post, PostDTO.class)).thenReturn(postDTO);

        PostDTO result = postService.createPost(postDTO);

        assertNotNull(result);
        assertEquals(postDTO.getId(), result.getId());
        assertEquals(postDTO.getTitle(), result.getTitle());
        assertEquals(postDTO.getContent(), result.getContent());
        assertEquals(postDTO.getAuthor(), result.getAuthor());

        verify(postRepository, times(1)).save(post);
    }

    @Test
    void updatePost_shouldReturnPostDTO_whenPostExists() {
        when(postRepository.existsById(1L)).thenReturn(true);
        when(modelMapper.map(postDTO, Post.class)).thenReturn(post);
        when(postRepository.save(post)).thenReturn(post);
        when(modelMapper.map(post, PostDTO.class)).thenReturn(postDTO);

        PostDTO result = postService.updatePost(1L, postDTO);

        assertNotNull(result);
        assertEquals(postDTO.getId(), result.getId());
        assertEquals(postDTO.getTitle(), result.getTitle());
        assertEquals(postDTO.getContent(), result.getContent());
        assertEquals(postDTO.getAuthor(), result.getAuthor());

        verify(postRepository, times(1)).save(post);
    }

    @Test
    void updatePost_shouldThrowException_whenPostDoesNotExist() {
        when(postRepository.existsById(1L)).thenReturn(false);

        assertThrows(NoSuchElementException.class, () -> postService.updatePost(1L, postDTO));

        verify(postRepository, times(1)).existsById(1L);
    }

    @Test
    void deletePost_shouldCallDelete_whenPostExists() {
        when(postRepository.existsById(1L)).thenReturn(true);

        postService.deletePost(1L);

        verify(postRepository, times(1)).deleteById(1L);
    }

    @Test
    void deletePost_shouldThrowException_whenPostDoesNotExist() {
        when(postRepository.existsById(1L)).thenReturn(false);

        assertThrows(NoSuchElementException.class, () -> postService.deletePost(1L));

        verify(postRepository, times(1)).existsById(1L);
    }

}