package pl.javastart.weekop.dao;
 
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
 
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
 
import pl.javastart.weekop.model.User;
import pl.javastart.weekop.util.ConnectionProvider;
 
public class UserDAOImpl implements UserDAO {
     
    private static final String CREATE_USER = 
      "INSERT INTO user(username, email, password, is_active) VALUES(:username, :email, :password, :active);";
    private static final String READ_USER = 
      "SELECT user_id, username, email, password, is_active FROM user WHERE user_id = :id";
    private static final String READ_USER_BY_USERNAME = 
      "SELECT user_id, username, email, password, is_active FROM user WHERE username = :username";
 
    private NamedParameterJdbcTemplate template;
     
    public UserDAOImpl() {
        template = new NamedParameterJdbcTemplate(ConnectionProvider.getDataSource());
    }
     
    @Override
    public User create(User user) {
        User resultUser = new User(user);
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource paramSource = new BeanPropertySqlParameterSource(user);
        int update = template.update(CREATE_USER, paramSource, holder);
        if(update > 0) {
            resultUser.setId((Long)holder.getKey());
            setPrivigiles(resultUser);
        }
        return resultUser;
    }
     
    private void setPrivigiles(User user) {
        final String userRoleQuery = "INSERT INTO user_role(username) VALUES(:username)";
        SqlParameterSource paramSource = new BeanPropertySqlParameterSource(user);
        template.update(userRoleQuery, paramSource);
    }
 
    @Override
    public User read(Long primaryKey) {
        User resultUser = null;
        SqlParameterSource paramSource = new MapSqlParameterSource("id", primaryKey);
        resultUser = template.queryForObject(READ_USER, paramSource, new UserRowMapper());
        return resultUser;
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
        User resultUser = null;
        SqlParameterSource paramSource = new MapSqlParameterSource("username", username);
        resultUser = template.queryForObject(READ_USER_BY_USERNAME, paramSource, new UserRowMapper());
        return resultUser;
    }
     
    private class UserRowMapper implements RowMapper<User> {
 
        @Override
        public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            User user = new User();
            user.setId(resultSet.getLong("user_id"));
            user.setUsername(resultSet.getString("username"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password"));
            return user;
        }
         
    }
}