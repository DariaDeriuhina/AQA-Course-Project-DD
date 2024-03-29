package api.steps;

import models.BodyArgs;
import models.Result;
import models.users.CreateUser;
import models.users.UserId;
import io.restassured.response.Response;

import static api.enums.UserRole.USER;
import static api.methods.Users.CREATE_USER;
import static api.methods.Users.DELETE_USER;
import static utils.EnvProperties.API_TOKEN;
import static utils.EnvProperties.API_USERNAME;

public class UserApiSteps extends BaseApiSteps {

    public String createUser(String username, String pass) {
        CreateUser args = CreateUser.builder()
                .username(username)
                .name(username)
                .password(pass)
                .email(username + "@gmail.com")
                .role(USER.getRole())
                .build();

        BodyArgs bodyArgs = BodyArgs.builder()
                .params(args)
                .method(CREATE_USER)
                .build();

        Response response = postRequest(API_USERNAME, API_TOKEN, bodyArgs);
        response.then().statusCode(200);
        Result result = response.as(Result.class);
        return result.getResult().toString();
    }

    public boolean deleteUser(String userId) {

        BodyArgs bodyArgs = BodyArgs.builder()
                .params(new UserId(Integer.valueOf(userId)))
                .method(DELETE_USER)
                .build();

        Response response = postRequest(API_USERNAME, API_TOKEN, bodyArgs);
        return (boolean) response.as(Result.class).getResult();
    }
}
