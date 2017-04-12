package pl.javastart.weekop.dao;
 
import pl.javastart.weekop.model.Vote;
 
public interface VoteDAO extends GenericDAO<Vote, Long> {
 
    public Vote getVoteByUserIdDiscoveryId(long userId, long discoveryId);
}