package egovframework.servicename.config;

import org.egovframe.rte.fdl.cmmn.trace.LeaveaTrace;
import org.egovframe.rte.fdl.cmmn.trace.handler.DefaultTraceHandler;
import org.egovframe.rte.fdl.cmmn.trace.handler.TraceHandler;
import org.egovframe.rte.fdl.cmmn.trace.manager.DefaultTraceHandleManager;
import org.egovframe.rte.fdl.cmmn.trace.manager.TraceHandlerService;
import org.egovframe.rte.psl.dataaccess.mapper.MapperConfigurer;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.AntPathMatcher;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
public class EgovConfigCommon {

    @Bean
    public AntPathMatcher antPathMatcher() {
        return new AntPathMatcher();
    }

    @Bean
    public DefaultTraceHandler defaultTraceHandler() {
        return new DefaultTraceHandler();
    }

    @Bean
    public DefaultTraceHandleManager traceHandlerService() {
        DefaultTraceHandleManager defaultTraceHandleManager = new DefaultTraceHandleManager();
        defaultTraceHandleManager.setReqExpMatcher(antPathMatcher());
        defaultTraceHandleManager.setPatterns(new String[]{"*"});
        defaultTraceHandleManager.setHandlers(new TraceHandler[]{defaultTraceHandler()});
        return defaultTraceHandleManager;
    }

    @Bean
    public LeaveaTrace leaveaTrace() {
        LeaveaTrace leaveaTrace = new LeaveaTrace();
        leaveaTrace.setTraceHandlerServices(new TraceHandlerService[]{traceHandlerService()});
        return leaveaTrace;
    }

    /* 없어도 동작 메뉴얼에 넣어야 한다고하여 넣어놓음 2024.11.07 박원우 */
    @Bean
    public MapperConfigurer mapperConfigurer() {
        MapperConfigurer mapperConfigurer = new MapperConfigurer();
        mapperConfigurer.setBasePackage("classpath:egovframework");
        return mapperConfigurer;
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource) throws IOException {
        PathMatchingResourcePatternResolver pmrpr = new PathMatchingResourcePatternResolver();
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setConfigLocation(pmrpr.getResource("classpath:/egovframework/sqlmap/sqlMapperConfig.xml"));
        sqlSessionFactoryBean.setMapperLocations(pmrpr.getResources("classpath:/egovframework/sqlmap/mappers/*.xml"));
        return sqlSessionFactoryBean;
    }
}