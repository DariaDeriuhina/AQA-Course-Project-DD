package models.projects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateProjectRequest {
    private String jsonrpc;
    private String method;
    private int id;
    private CreateProjectParams params;

    // Геттеры и сеттеры не требуются благодаря аннотации @Data
}
