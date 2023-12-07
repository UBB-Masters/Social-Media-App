package Controller;

import Entities.Reaction.Reaction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReactionRepositoryTest {

    @Mock
    private ReactionRepository reactionRepository;

    @Mock
    private Reaction reaction;

    @Test
    public void testSaveReaction() {
        when(reactionRepository.save(reaction)).thenReturn(reaction);
        Reaction savedReaction = reactionRepository.save(reaction);
        assertEquals(savedReaction, reaction);
        verify(reactionRepository, times(1)).save(reaction);
    }

    @Test
    public void testFindReactionById() {
        when(reactionRepository.findById(1L)).thenReturn(Optional.of(reaction));
        Optional<Reaction> foundReaction = reactionRepository.findById(1L);
        assertEquals(foundReaction.get(), reaction);
        verify(reactionRepository, times(1)).findById(1L);
    }

    @Test
    public void testDeleteReaction() {
        reactionRepository.delete(reaction);
        verify(reactionRepository, times(1)).delete(reaction);
    }

    @Test
    public void testFindAllReactions() {
        List<Reaction> reactions = new ArrayList<>();
        reactions.add(reaction);
        when(reactionRepository.findAll()).thenReturn(reactions);
        List<Reaction> foundReactions = reactionRepository.findAll();
        assertEquals(foundReactions, reactions);
        verify(reactionRepository, times(1)).findAll();
    }

    @Test
    public void testSaveReaction_NullReaction() {
        when(reactionRepository.save(null)).thenThrow(new IllegalArgumentException());
        assertThrows(IllegalArgumentException.class, () -> reactionRepository.save(null));
        verify(reactionRepository, times(1)).save(null);
    }

    @Test
    public void testFindReactionById_NonExistingId() {
        when(reactionRepository.findById(1L)).thenReturn(Optional.empty());
        Optional<Reaction> foundReaction = reactionRepository.findById(1L);
        assertFalse(foundReaction.isPresent());
        verify(reactionRepository, times(1)).findById(1L);
    }

    @Test
    public void testDeleteReaction_NullReaction() {
        doThrow(new IllegalArgumentException()).when(reactionRepository).delete(null);
        assertThrows(IllegalArgumentException.class, () -> reactionRepository.delete(null));
        verify(reactionRepository, times(1)).delete(null);
    }

    @Test
    public void testFindAllReactions_NoReactions() {
        when(reactionRepository.findAll()).thenReturn(Collections.emptyList());
        List<Reaction> foundReactions = reactionRepository.findAll();
        assertTrue(foundReactions.isEmpty());
        verify(reactionRepository, times(1)).findAll();
    }
}