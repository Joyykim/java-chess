package chess.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import chess.domain.player.User;
import chess.util.DBConnector;

class UserDAOTest {

    private UserDAO userDao;

    @BeforeEach
    public void setup() {
        userDao = new UserDAO(new DBConnector());
    }

    @Test
    public void addUser() throws Exception {
        User user = new User("testUser");
        userDao.addUser(user);
    }

    @Test
    public void findByUserName() throws Exception {
        User user = userDao.findByUserName("testUser").orElse(null);
        assertEquals(new User("testUser"), user);
    }

    @Test
    public void updateNameByUserName() throws Exception {
        User user = userDao.updateUserNameByUserName("testUser", "changedName");
        assertEquals(new User("changedName"), user);
    }

    @Test
    public void deleteNameByUserName() throws Exception {
        Boolean isDeleted = userDao.deleteUserByUserName("testUser");
        assertEquals(isDeleted, true);
    }

    @Test
    public void repeatableCrud() throws Exception {
        addUser();
        findByUserName();
        updateNameByUserName();
        deleteNameByUserName();
    }
}