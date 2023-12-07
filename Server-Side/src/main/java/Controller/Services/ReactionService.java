package Controller.Services;

import Controller.ReactionRepository;
import Entities.Reaction.Reaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReactionService {

    private final ReactionRepository reactionRepository;

    @Autowired
    public ReactionService(ReactionRepository reactionRepository) {
        this.reactionRepository = reactionRepository;
    }

    public void addReaction(Reaction reaction) {
        reactionRepository.save(reaction);
    }

    public void removeReactionById(Long reactionId) {
        reactionRepository.deleteById(reactionId);
    }

    public List<Reaction> getAllReactions() {
        return reactionRepository.findAll();
    }

    // You can add more methods as needed, e.g., getReactionsByUser, getReactionsByPost, etc.
}
