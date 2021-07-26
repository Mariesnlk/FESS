package fess.service.interfaces;

import fess.dto.FESS;

public interface FessInterface {

    void send(FESS fess, Integer numberThreads, Integer interval);

}
