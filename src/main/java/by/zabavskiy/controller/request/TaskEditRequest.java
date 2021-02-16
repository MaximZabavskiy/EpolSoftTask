package by.zabavskiy.controller.request;

import lombok.Data;

@Data
public class TaskEditRequest extends TaskCreateRequest {

    private Long id;
}
