package integration;

import batch.DataSourceConfig;
import batch.Singer;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
@Import(DataSourceConfig.class)
@ComponentScan("integration")
public class BatchIntConfig {
    @Autowired
    private JobBuilderFactory jobs;
    @Autowired
    private StepBuilderFactory steps;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private SingerItemProcessor itemProcessor;

    @Bean
    public Job job(JobExecutionStatsListener listener) {
        return jobs.get("singerJob").listener(listener).flow(step1()).end().build();
    }

    @Bean
    public Job singerJob() {
        return jobs.get("singerJob").start(step1()).build();
    }

    @Bean
    protected Step step1() {
        return steps.get("step1")
                .<Singer, Singer>chunk(10)
                .reader(itemReader())
                .processor(itemProcessor)
                .writer(itemWriter())
                .build();
    }

    @Bean
    @StepScope
    public ItemReader itemReader() {
        FlatFileItemReader<Singer> itemReader = new FlatFileItemReader<>();
        itemReader.setResource(new ClassPathResource("support/test-data.csv"));
        itemReader.setLineMapper(new DefaultLineMapper<Singer>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[] { "firstName", "lastName", "song" });
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<Singer>() {{
                setTargetType(Singer.class);
            }});
        }});
        return itemReader;
    }

    @Bean
    public ItemWriter itemWriter() {
        JdbcBatchItemWriter<Singer> itemWriter = new JdbcBatchItemWriter<>();
        itemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        itemWriter.setSql("INSERT INTO singer(first_name, last_name, song) " +
                "VALUES(:firstName, :lastName, :song)");
        itemWriter.setDataSource(dataSource);

        return itemWriter;
    }
}
