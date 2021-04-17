package com.anytime.studymaker.service.common;

public interface CrudService<Request, Response> {
    void create(Request request);
    Response read(Long id);
    Response update(Request request);
    void delete(Long id);
}
