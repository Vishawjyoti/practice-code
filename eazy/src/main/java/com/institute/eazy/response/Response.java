package com.institute.eazy.response;

import java.util.List;

public class Response {
    private Integer id;
    Status status;
    List <ResponseError> errors;
    Object data;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<ResponseError> getErrors() {
        return errors;
    }

    public void setErrors(List<ResponseError> errors) {
        this.errors = errors;
    }

    public Object getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Response{" +
                "id=" + id +
                ", status=" + status +
                ", errors=" + errors +
                ", data=" + data +
                '}';
    }

    public void setData(Object data) {
        this.data = data;

    }
}
