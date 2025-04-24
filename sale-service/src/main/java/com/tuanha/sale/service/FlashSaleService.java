package com.tuanha.sale.service;

import com.tuanha.sale.entity.FlashSale;
import com.tuanha.sale.entity.FlashSaleItem;
import com.tuanha.sale.enums.FlashSaleStatus;
import com.tuanha.sale.exception.AppException;
import com.tuanha.sale.exception.ErrorCode;
import com.tuanha.sale.repository.FlashSaleItemRepository;
import com.tuanha.sale.repository.FlashSaleRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class FlashSaleService {
    FlashSaleRepository flashSaleRepository;
    FlashSaleItemRepository flashSaleItemRepository;


}
