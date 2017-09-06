package com.epam.demo.soap.endpoint;

import com.epam.demo.soap.domain.Car;
import com.epam.demo.soap.repository.CarRepository;
import com.epam.demo.soap.wscar10.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

/**
 * Created by Timur_Khudiakov on 9/5/2017.
 */
@Endpoint
public class CarsEndpoint {

    private static final String NAMESPACE_URI = "http://epam.com/demo/soap/WSCar10";

    private CarRepository repository;

    @Autowired
    public CarsEndpoint(CarRepository repository) {
        this.repository = repository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetCarsByBrandRequest")
    @ResponsePayload
    public GetCarsByBrandResponse getCarsByBrand(@RequestPayload GetCarsByBrandRequest request) {
        List<Car> carsByBrand = repository.getCarsByBrand(request.getInParams().getBrandName());

        GetCarsByBrandResponse response = new GetCarsByBrandResponse();
        GetCarsByBrandOutParams outParams = new GetCarsByBrandOutParams();
        GetCarsByBrandOutResultSet resultSet = new GetCarsByBrandOutResultSet();
        carsByBrand.stream().map(this::mapToRow).forEach(resultSet.getResultSetRow()::add);
        outParams.setResultSet(resultSet);
        response.setOutParams(outParams);
        return response;
    }

    private GetCarsByBrandOutResultSetRow mapToRow(Car car) {
        GetCarsByBrandOutResultSetRow resultSetRow = new GetCarsByBrandOutResultSetRow();
        resultSetRow.setModel(car.getModel());
        resultSetRow.setColor(car.getColor());
        resultSetRow.setBrand(car.getBrand());
        return resultSetRow;
    }
}
