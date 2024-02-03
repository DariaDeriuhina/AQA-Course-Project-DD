package models.projects;

import lombok.Data;

@Data
public class CreateProjectResponse {
    private String jsonrpc;
    private int id;
    private int result;
}
