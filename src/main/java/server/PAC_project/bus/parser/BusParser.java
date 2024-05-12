package server.PAC_project.bus.parser;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface BusParser<T> {

    List<T> getData() throws JsonProcessingException;

}
