package fess.service.implementations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import fess.dto.FESS;
import fess.dto.Wallets;
import fess.service.interfaces.FessInterface;
import fess.utils.requests.RequestHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class FessService implements FessInterface {

    private static final String url = "http://147.182.177.117:8115/send";
    private final List<String> addresses = Lists.newArrayList("F49CC9D3AA74BC8738A15FF109CFB5115851AC4A",
            "907ED7B69FDD613CDED87679B620CA1202375DC1", "A13C131726E3D7507BD76AC429591910F126A9DC", "3BA09CDB219538FDB2896764FA72E156977B4E4B",
            "FE10E3A1AB9E36F88AC1692ECFFB7E0076078492");
    private final Random rand = new Random();

    @Scheduled(fixedDelay = 1000)
    public void test() {
        System.out.println(new Date());

        List<Wallets> listOfWallets = parseJSONList();

        ExecutorService executorService = Executors.newFixedThreadPool(50);

        for (int i = 0; i < 50; i++) {
            executorService.submit(() -> send(FESS.builder()
                    .from(addresses.get(rand.nextInt(addresses.size())))
                    .to(listOfWallets.get(rand.nextInt(listOfWallets.size())).getAddress())
                    .amount(listOfWallets.get(rand.nextInt(listOfWallets.size())).getAmount() * 100000)
                    .build()));
        }
    }

    public void send(FESS fess) {
        String requiredParams = createJson(fess);
        System.out.println(RequestHelper.POST_Request(url, requiredParams, new String[]{"token", " ildptu5836dbo93mso85bn9dklw3mn5"}));
    }

    private List<FESS> randomList() {
        List<Wallets> listOfWallets = parseJSONList();
        List<FESS> randomList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            randomList.add(FESS.builder()
                    .from(addresses.get(rand.nextInt(addresses.size())))
                    .to(listOfWallets.get(rand.nextInt(listOfWallets.size())).getAddress())
                    .amount(listOfWallets.get(rand.nextInt(listOfWallets.size())).getAmount())
                    .build());
        }
//        System.out.println(randomList);
        return randomList;
    }

    private List<Wallets> parseJSONList() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writerWithDefaultPrettyPrinter();

        List<Wallets> listOfWallets = null;

        try {
            listOfWallets = objectMapper.readValue(new FileReader("C:\\SYNE\\Wallets.json"),
                    objectMapper.getTypeFactory().constructCollectionType(List.class, Wallets.class));
        } catch (IOException e) {
            e.printStackTrace();
        }

//        System.out.println(listOfWallets);

        return listOfWallets;
    }


    private String createJson(FESS fess) {
        JSONObject json = new JSONObject();
        json.put("from", fess.getFrom());
        json.put("to", fess.getTo());
        json.put("amount", fess.getAmount());
        return json.toString();
    }

    private List<FESS> createList(FESS fess, Integer numberThreads) {
        List<FESS> list = new ArrayList<>();
        for (int i = 0; i < numberThreads; i++) {
            list.add(fess);
        }
        return list;
    }
}