package api.steps;

import models.BodyArgs;
import models.Result;
import models.projects.CreateProjectParams;
import models.projects.ProjectID;
import io.restassured.response.Response;

import static api.methods.Projects.CREATE_PROJECT;
import static api.methods.Projects.REMOVE_PROJECT;
import static utils.EnvProperties.API_TOKEN;
import static utils.EnvProperties.API_USERNAME;

public class ProjectAPISteps extends BaseApiSteps{
    public String createProject(String title) {
        // Замените значения параметров на реальные данные
        CreateProjectParams args = CreateProjectParams.builder()
                .name(title)
                .build();

        BodyArgs bodyArgs = BodyArgs.builder()
                .params(args)
                .method(CREATE_PROJECT)
                .build();

        // Выполняем запрос на создание проекта
        Response response = postRequest(API_USERNAME, API_TOKEN, bodyArgs);
        response.then().statusCode(200);
        Result bodyResult = response.as(Result.class);
        return bodyResult.getResult().toString();
    }

    public boolean removeProject(int projectId) {
        BodyArgs bodyArgs = BodyArgs.builder()
                .params(new ProjectID(projectId))
                .method(REMOVE_PROJECT)
                .build();
        Response response = postRequest(API_USERNAME, API_TOKEN, bodyArgs);
        response.then().statusCode(200);
        return (boolean) response.as(Result.class).getResult();
    }
}
