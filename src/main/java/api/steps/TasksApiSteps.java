package api.steps;

import models.BodyArgs;
import models.Result;
import models.tasks.CreateTask;
import models.tasks.TaskId;
import io.restassured.response.Response;

import static api.methods.Tasks.CREATE_TASK;
import static api.methods.Tasks.REMOVE_TASK;
import static utils.EnvProperties.API_TOKEN;
import static utils.EnvProperties.API_USERNAME;

public class TasksApiSteps extends BaseApiSteps {

    public String createTaskRequiredParams(String taskTitle, Integer projectId) {
        CreateTask args = CreateTask.builder()
                .title(taskTitle)
                .project_id(projectId)
                .build();

        BodyArgs bodyArgs = BodyArgs.builder()
                .params(args)
                .method(CREATE_TASK)
                .build();

        Response response = postRequest(API_USERNAME, API_TOKEN, bodyArgs);
        response.then().statusCode(200);
        Result bodyResult = response.as(Result.class);
        return bodyResult.getResult().toString();
    }

    public boolean removeTask(Integer taskId) {
        BodyArgs bodyArgs = BodyArgs.builder()
                .params(new TaskId(taskId))
                .method(REMOVE_TASK)
                .build();
        Response response = postRequest(API_USERNAME, API_TOKEN, bodyArgs);
        response.then().statusCode(200);
        return (boolean) response.as(Result.class).getResult();
    }
}