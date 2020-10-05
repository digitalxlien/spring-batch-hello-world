package com.example.simplebatchjob;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Bean
    public Job firstSpringBatchJob(){
        return jobBuilderFactory
                .get("firstSpringBatchJob")
                .start( firstSpringBatchStep() )
                .build();

    }

    public Step firstSpringBatchStep(){
        return stepBuilderFactory
                .get("firstSpringBatchStep")
                .tasklet(
                        (StepContribution contribution, ChunkContext chunkContext) -> {
                            logger.info("Hello world!");
                            return RepeatStatus.FINISHED;
                        }
                )
                .build();
    }


}
