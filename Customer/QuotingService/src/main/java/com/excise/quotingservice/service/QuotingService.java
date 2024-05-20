package com.excise.quotingservice.service;

import com.excise.quotingservice.dto.QuotingDTO;
import com.excise.quotingservice.entity.Category;
import com.excise.quotingservice.repository.QuotingRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
@AllArgsConstructor
@Slf4j
public class QuotingService {
    private QuotingRepository repository;

    private static final double BASE_VALUE = 0.8; 
    private static final double IPHONE_ADJUSTMENT = 0.2; 
    private static final double ANDROID_ADJUSTMENT = 0.15; 
    private static final double TIME_DECAY_RATE = 0.05;
    private static final double NEW_99_DISCOUNT = 0.05; 
    private static final double NEW_98_DISCOUNT = 0.1; 
    private static final double NEW_97_DISCOUNT = 0.15; 
    private static final double NEW_96_DISCOUNT = 0.2; 


    public Mono<Double> calculateRepurchasePrice(QuotingDTO info) {
        // Tính giá cơ bản
        double repurchasePrice = info.getPriceOrigin() * BASE_VALUE;

        // Điều chỉnh giá dựa trên danh mục
        if (info.getCategory() == Category.IPHONE)
            repurchasePrice += info.getPriceOrigin() * IPHONE_ADJUSTMENT;
        else
            repurchasePrice += info.getPriceOrigin() * ANDROID_ADJUSTMENT;

        // Điều chỉnh giá dựa trên thời gian
        Date currentDate = new Date();
        long timeDifferenceInMillis = currentDate.getTime() - info.getDateBy().getTime();
        long yearsDifference = timeDifferenceInMillis / (1000L * 60 * 60 * 24 * 365); 
        double timeAdjustment = yearsDifference * TIME_DECAY_RATE * info.getPriceOrigin();
        repurchasePrice -= timeAdjustment;


        switch (info.getStatus()) {
            case NEW_99:
                repurchasePrice -= info.getPriceOrigin() * NEW_99_DISCOUNT;
                break;
            case NEW_98:
                repurchasePrice -= info.getPriceOrigin() * NEW_98_DISCOUNT;
                break;
            case NEW_97:
                repurchasePrice -= info.getPriceOrigin() * NEW_97_DISCOUNT;
                break;
            case NEW_96:
                repurchasePrice -= info.getPriceOrigin() * NEW_96_DISCOUNT;
                break;
        }

        return Mono.just(repurchasePrice);
    }
}
