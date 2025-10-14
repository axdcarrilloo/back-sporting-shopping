package com.bt.services.impls;

import com.bt.domain.entities.SaleEntity;
import com.bt.domain.repositories.SaleRepository;
import com.bt.dtos.*;
import com.bt.exceptions.NotFoundException;
import com.bt.exceptions.UnknownException;
import com.bt.mappers.SaleMapper;
import com.bt.mappers.UserMapper;
import com.bt.services.SaleDetailsService;
import com.bt.services.SaleService;
import com.bt.services.UserService;
import com.bt.utils.Constans;
import com.bt.utils.CustomLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {
    private final CustomLogger customLogger;
    private final UserMapper userMapper;
    private final UserService userSvc;
    private final SaleMapper saleMapper;
    private final SaleRepository saleRepository;
    private final SaleDetailsService saleDetailsSvc;

    private BuildLog buildCustomLogger(final String nameMethod, final String message) {
        return BuildLog.builder().nameClass(this.getClass().getName())
                .nameMethod(nameMethod).message(message).build()
        ;
    }

    private List<SaleDetailsRegisterDto> cleanListDetails(final List<SaleDetailsRegisterDto> saleDetails,
                                                          final SaleViewDto saleView) {
        List<SaleDetailsRegisterDto> newSaleDetailList = new ArrayList<>();
        saleDetails.forEach(details -> {
            details.setSaleView(saleView);
            newSaleDetailList.add(details);
        });
        return newSaleDetailList;
    }

    @Override
    public ResponseMainDto getAll() {
        customLogger.logMain(Boolean.FALSE, buildCustomLogger("getAll()",
                "Consultando todas las ventas"))
        ;
        List<SaleViewDto> salesViewDto = new ArrayList<>();
        saleRepository.findAll().forEach(sale -> {
            SaleViewDto saleView = SaleViewDto.builder().id(sale.getId())
                    .client(userMapper.convertToViewFromEntity(sale.getClientEntity()))
                    .saleDetails(saleDetailsSvc.getBySale(saleMapper.convertToViewFromEntity(sale)))
                    .build()
            ;
            salesViewDto.add(saleView);
        });
        return ResponseMainDto.builder().message(Constans.MSG_RESPONSE_QUERY_SUCCESS)
                .response(salesViewDto).build()
        ;
    }

    @Override
    public ResponseMainDto register(SaleRegisterDto saleRegisterDto) {
        try {
            if(Boolean.FALSE.equals(userSvc.existsById(saleRegisterDto.getClient().getId()))) {
                NotFoundException notFoundException =
                        new NotFoundException(ResponseMainDto.builder().message(Constans.MSG_RESPONSE_REGISTER_FAILED)
                                .response(Constans.LOG_USER_NOT_EXISTS).build()
                        )
                ;
                customLogger.logMain(Boolean.TRUE,
                        buildCustomLogger("register()",
                                Constans.MSG_RESPONSE_REGISTER_FAILED+": "+Constans.LOG_USER_NOT_EXISTS)
                        )
                ;
                throw notFoundException;
            }

            SaleEntity saleEntity = saleRepository.save(saleMapper.convertToEntityFromRequest(saleRegisterDto));
            if(Objects.isNull(saleEntity.getId())) {
                UnknownException unknownException = new UnknownException(ResponseMainDto.builder()
                        .message(Constans.MSG_RESPONSE_REGISTER_FAILED).response(Constans.MSG_RESPONSE_ERROR_UNKNOWN)
                        .build())
                ;
                customLogger.logMain(Boolean.TRUE,
                        buildCustomLogger("register()",
                                Constans.MSG_RESPONSE_REGISTER_FAILED+": "+Constans.MSG_RESPONSE_ERROR_UNKNOWN)
                        )
                ;
                throw unknownException;
            }
            saleRegisterDto.setSaleDetails(cleanListDetails(saleRegisterDto.getSaleDetails(), saleMapper.convertToViewFromEntity(saleEntity)));
            String message = saleDetailsSvc.register(saleRegisterDto.getSaleDetails()).getMessage();
            if(message.equals(Constans.MSG_RESPONSE_REGISTER_SUCCESS)) {
                customLogger.logMain(Boolean.FALSE, buildCustomLogger("register()",
                        "Registrando venta"))
                ;
                return ResponseMainDto.builder().message(Constans.MSG_RESPONSE_REGISTER_SUCCESS)
                        .response("Venta registrada exitosamente").build()
                ;
            } else {
                UnknownException unknownException = new UnknownException(ResponseMainDto.builder()
                        .message(Constans.MSG_RESPONSE_REGISTER_FAILED).response(Constans.MSG_RESPONSE_ERROR_UNKNOWN)
                        .build())
                ;
                customLogger.logMain(Boolean.TRUE,
                        buildCustomLogger("register()",
                                Constans.MSG_RESPONSE_REGISTER_FAILED+": "+Constans.MSG_RESPONSE_ERROR_UNKNOWN)
                        )
                ;
                throw unknownException;
            }
        } catch (Exception e) {
            customLogger.logMain(Boolean.TRUE, buildCustomLogger("register()", e.getMessage()));
            throw e;
        }
    }
}
