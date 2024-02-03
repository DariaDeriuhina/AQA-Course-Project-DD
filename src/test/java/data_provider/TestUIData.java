package data_provider;

import models.entity.User;
import org.testng.annotations.DataProvider;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.UUID;
import java.util.Random;

public class TestUIData {
    @DataProvider(name = "illegalLoginPassword")
    public Object[][] illegalLoginAndPassword() {
        return new Object[][]{
                {new User("Login" + UUID.randomUUID(), "Password" + UUID.randomUUID())},
                {new User("Login" + UUID.randomUUID(), "Password" + UUID.randomUUID())},
                {new User("Login" + UUID.randomUUID(), "Password" + UUID.randomUUID())}
        };
    }

    @DataProvider(name = "illegalLogin")
    public Object[][] illegalLogin() {
        return new Object[][]{
                {new User("Login" + UUID.randomUUID(), "admin")},
                {new User("Login" + UUID.randomUUID(), "admin")},
                {new User("Login" + UUID.randomUUID(), "admin")}
        };
    }

    @DataProvider(name = "illegalPassword")
    public Object[][] illegalPassword() {
        return new Object[][]{
                {new User("admin", "Password" + UUID.randomUUID())},
                {new User("admin", "Password" + UUID.randomUUID())},
                {new User("admin", "Password" + UUID.randomUUID())}
        };
    }

    @DataProvider(name = "correctUser")
    public Object[][] correctUser() {
        return new Object[][]{
                {new User("admin", "admin")}
        };
    }

    @DataProvider(name = "project")
    public Object[][] project() {
        Random random = new Random();

        return new Object[][]{
                {RandomStringUtils.randomAlphanumeric(10), RandomStringUtils.randomAlphanumeric(10),
                        String.valueOf(random.nextInt(100))}
        };
    }

    @DataProvider(name = "taskData")
    public Object[][] getTaskData() {
        return new Object[][]{
                {"Task1"}
        };
    }

    @DataProvider(name = "commentData")
    public Object[][] getCommentData() {
        return new Object[][]{
                {"This task needs to be addressed to another team"},
                {"This task will be complete in scope of Q2 2024"}
        };
    }
}
