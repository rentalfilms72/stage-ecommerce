package it.stage.microservices.order.config;

import brave.sampler.Sampler;

public class SleuthConfig {

    public Sampler defaultSampler(){
        return Sampler.ALWAYS_SAMPLE;
    }
}
