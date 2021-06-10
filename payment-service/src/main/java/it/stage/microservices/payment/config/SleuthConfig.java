package it.stage.microservices.payment.config;

import brave.sampler.Sampler;

public class SleuthConfig {

    public Sampler defaultSampler(){
        return Sampler.ALWAYS_SAMPLE;
    }
}
