package fess.service.implementations;

import fess.dto.FESS;
import fess.service.interfaces.FessInterface;
import fess.utils.requests.RequestHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class FessService implements FessInterface {

    private final String url = "http://167.172.36.196:8115/send";

    @PostConstruct
    public void test(){
        List<FESS> fessList = new ArrayList<>();
        fessList.add(FESS.builder()
                .from("40C1E629D69B12AC244C5BD620275EA88299B976")
                .to("FESS7E713AAEFE476B6B74224F06407560209E0268F5")
                .amount(100L)
                .build());
        fessList.add(FESS.builder()
                .from("40C1E629D69B12AC244C5BD620275EA88299B976")
                .to("FESS7E713AAEFE476B6B74224F06407560209E0268F5")
                .amount(30L)
                .build());
        fessList.add(FESS.builder()
                .from("40C1E629D69B12AC244C5BD620275EA88299B976")
                .to("FESS7E713AAEFE476B6B74224F06407560209E0268F5")
                .amount(20L)
                .build());

        ExecutorService executorService = Executors.newFixedThreadPool(fessList.size());
        fessList.forEach(
                fess -> {
                    executorService.submit(() -> {
                        send(fess, 2, 1000);
                    });
        });
    }

    @Override
    public void send(FESS fess, Integer numberThreads, Integer interval) {
        String requiredParams =  createJson(fess);
        List<FESS> fessList = createList(fess, numberThreads);
        ExecutorService executorService = Executors.newFixedThreadPool(numberThreads);
        while (true) {
            fessList.forEach(
                    obj -> {
                        executorService.submit(() -> {
//                            System.out.println(obj);
                            System.out.println(RequestHelper.POST_Request(url,  requiredParams));
                        });
                    });
            try {
                Thread.sleep(interval);
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