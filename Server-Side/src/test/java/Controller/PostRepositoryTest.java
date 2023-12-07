package Controller;

import Entities.Post.Post;
import Entities.User.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PostRepositoryTest {

    @Mock
    private PostRepository postRepository;

    @Mock
    private Post post;

    @Test
    public void testSavePost() {
        when(postRepository.save(post)).thenReturn(post);
        Post savedPost = postRepository.save(post);
        assertEquals(savedPost, post);
        verify(postRepository, times(1)).save(post);
    }

    @Test
    public void testFindPostById() {
        when(postRepository.findById(1L)).thenReturn(Optional.of(post));
        Optional<Post> foundPost = postRepository.findById(1L);
        assertEquals(foundPost.get(), post);
        verify(postRepository, times(1)).findById(1L);
    }

    @Test
    public void testDeletePost() {
        postRepository.delete(post);
        verify(postRepository, times(1)).delete(post);
    }

    @Test
    public void testFindAllWithUser() {
        List<Post> posts = new ArrayList<>();
        posts.add(post);
        when(postRepository.findAllWithUser()).thenReturn(posts);
        List<Post> foundPosts = postRepository.findAllWithUser();
        assertEquals(foundPosts, posts);
        verify(postRepository, times(1)).findAllWithUser();
    }

    @Test
    public void testFindByUser() {
        User user = new User();
        List<Post> posts = new ArrayList<>();
        posts.add(post);
        when(postRepository.findByUser(user)).thenReturn(posts);
        List<Post> foundPosts = postRepository.findByUser(user);
        assertEquals(foundPosts, posts);
        verify(postRepository, times(1)).findByUser(user);
    }

    @Test
    public void testSavePost_NullPost() {
        when(postRepository.save(null)).thenThrow(new IllegalArgumentException());
        assertThrows(IllegalArgumentException.class, () -> postRepository.save(null));
        verify(postRepository, times(1)).save(null);
    }

    @Test
    public void testFindPostById_NonExistingId() {
        when(postRepository.findById(1L)).thenReturn(Optional.empty());
        Optional<Post> foundPost = postRepository.findById(1L);
        assertFalse(foundPost.isPresent());
        verify(postRepository, times(1)).findById(1L);
    }

    @Test
    public void testDeletePost_NullPost() {
        doThrow(new IllegalArgumentException()).when(postRepository).delete(null);
        assertThrows(IllegalArgumentException.class, () -> postRepository.delete(null));
        verify(postRepository, times(1)).delete(null);
    }

    @Test
    public void testFindAllWithUser_NoPosts() {
        when(postRepository.findAllWithUser()).thenReturn(Collections.emptyList());
        List<Post> foundPosts = postRepository.findAllWithUser();
        assertTrue(foundPosts.isEmpty());
        verify(postRepository, times(1)).findAllWithUser();
    }

    @Test
    public void testFindByUser_NullUser() {
        when(postRepository.findByUser(null)).thenThrow(new IllegalArgumentException());
        assertThrows(IllegalArgumentException.class, () -> postRepository.findByUser(null));
        verify(postRepository, times(1)).findByUser(null);
    }
}