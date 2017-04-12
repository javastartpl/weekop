package pl.javastart.weekop.dao;
 
import java.util.List;
import pl.javastart.weekop.model.User;
 
public class UserDAOImpl implements UserDAO {
 
    @Override
    public User create(User newObject) {
        return null;
    }
 
    @Override
    public User read(Long primaryKey) {
        return null;
    }
 
    @Override
    public boolean update(User updateObject) {
        return false;
    }
 
    @Override
    public boolean delete(Long key) {
        return false;
    }
 
    @Override
    public List<User> getAll() {
        return null;
    }
 
    @Override
    public User getUserByUsername(String username) {
        return null;
    }
}