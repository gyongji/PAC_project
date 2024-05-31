package server.PAC_project.subway.parser;

import java.io.IOException;
import java.util.List;


public interface SubwayParser<T> {

    default List<T> getData() throws IOException {
        return null;
    }
    default List<T> getData(String A) throws IOException {
        return null;
    }

}