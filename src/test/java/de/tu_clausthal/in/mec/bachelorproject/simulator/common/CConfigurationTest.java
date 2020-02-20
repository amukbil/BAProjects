package de.tu_clausthal.in.mec.bachelorproject.simulator.common;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;

class CConfigurationTest {

    private static Map<String, Object> m_config;

    @BeforeAll
    static void init()
    {
        m_config = CConfiguration.INSTANCE.load("config.yaml").getall();
    }

    @Test
    void load() {
        //check configurations
        System.out.println(m_config);
        //print single nested element
        System.out.println(((Map<String, Object>)m_config.get("grid")).get("rows"));
    }

    @Test
    void get()
    {
        System.out.println("columns: " + CConfiguration.INSTANCE.get("grid", "columns"));
        System.out.println("rows: " + CConfiguration.INSTANCE.get("grid", "rows"));
    }
}