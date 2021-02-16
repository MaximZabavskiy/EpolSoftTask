package by.zabavskiy.controller.request;

import lombok.Data;

@Data
public class ElementCreateRequest {

    private Long taskId;

    private String name;

    private String description;

    private String value;

}
