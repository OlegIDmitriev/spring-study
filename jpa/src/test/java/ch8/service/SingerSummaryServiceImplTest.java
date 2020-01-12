package ch8.service;

import ch8.config.JpaConfig;
import ch8.view.SingerSummary;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.List;

import static org.junit.Assert.*;

public class SingerSummaryServiceImplTest {
    private static Logger logger = LoggerFactory.getLogger(SingerSummaryServiceImplTest.class);
    private GenericApplicationContext ctx;
    private SingerSummaryService singerSummaryService;

    @Before
    public void setUp() throws Exception {
        ctx = new AnnotationConfigApplicationContext(JpaConfig.class);
        singerSummaryService = ctx.getBean(SingerSummaryService.class);
        assertNotNull(singerSummaryService);
    }

    @Test
    public void findAll() {
        List<SingerSummary> summaries = singerSummaryService.findAll();
        assertEquals(2, summaries.size());
        listSingerSummary(summaries);
    }

    private static void listSingerSummary(List<SingerSummary> summaries) {
        logger.info(" --- Listing singer summary: ");
        for (SingerSummary summary : summaries) {
            logger.info(summary.toString());
        }
    }

    @After
    public void tearDown() throws Exception {
        ctx.close();
    }
}