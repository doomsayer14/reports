package com.internship.reports.proxy;

import com.internship.reports.entity.TreatmentModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "treatment-service")
public interface Proxy {
    @GetMapping("/api/v1/treatments/20/USD")
    List<TreatmentModel> treatments();
}