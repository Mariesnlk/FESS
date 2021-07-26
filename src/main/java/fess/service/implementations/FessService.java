package fess.service.implementations;

import fess.dto.FESS;
import fess.service.interfaces.FessInterface;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class FessService implements FessInterface {

    private final String url = "/send";

    @Override
    public void send(FESS fess, Integer numberThreads, Integer interval) {
        String requiredParams =  createJson(fess);
        List<FESS> fessList = createList(fess, numberThreads);
        ExecutorService executorService = Executors.newFixedThreadPool(numberThreads);
        while (true) {
            fessList.forEach(
                    obj -> {
                        executorService.submit(() -> {
                            //                RequestHelper.POST_Request(url,  requiredParams);
                            System.out.println(System.currentTimeMillis() + "  " + requiredParams);
                        });
                    });
            try {
                Thread.sleep(interval * 60000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public String createJson(FESS fess){
        JSONObject json = new JSONObject();
        json.put("from", fess.getFrom());
        json.put("to", fess.getTo());
        json.put("amount", fess.getAmount());
        return json.toString();
    }

    public List<FESS> createList(FESS fess, Integer numberThreads){
        List<FESS> list = new ArrayList<>();
        for (int i=0; i<numberThreads; i++){
            list.add(fess);
        }
        return list;
    }
}