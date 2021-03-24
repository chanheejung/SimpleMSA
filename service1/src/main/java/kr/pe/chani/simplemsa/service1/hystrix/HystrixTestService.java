package kr.pe.chani.simplemsa.service1.hystrix;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import kr.pe.chani.simplemsa.service1.rest.RibbonClientCommunicator;
import kr.pe.chani.simplemsa.service1.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

@Slf4j
@RefreshScope
@Service
public class HystrixTestService {

    @Autowired
    RibbonClientCommunicator ribbonClientCommunicator;

    @Autowired
    HystrixTestDao hystrixTestDao;

    /** 기본 설정 - 1초 이상 지연 발생시 500 Internal Serve Error 발생 */
    @HystrixCommand
    public String getName(String id) {
        log.info("Communicating ...");

        /** 호출 3회중 랜덤 1회 3초 통신지 지연된걸로 간주한다. */
        CommonUtil.randomlyRunLong(3, 3000);
        return ribbonClientCommunicator.getName(id);
    }

    @HystrixCommand(
            fallbackMethod = "buildFallbackIdInfo",
            // 스레드 풀의 고유 이름 지정
            threadPoolKey = "idInfoThreadPool",
            // 스레드 풀 속성으로 동작 정의 및 설정
            threadPoolProperties =
                    // 스레드 풀의 갯수를 정의
                    {@HystrixProperty(name = "coreSize",value="30"),
                            // 스레드 풀 앞에 배치할 큐와 큐에 넣을 요청 수를 정의
                            //  => 스레드가 분주할 때 큐 이용
                            @HystrixProperty(name="maxQueueSize", value="10")},
            commandProperties={
                    // Timeout 설정 12초
                    // @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="12000"),
                    // 히스트릭스가 호출 차단을 고려하는데 필요한 시간
                    @HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value="10"),
                    // 호출 차단 실패 비율
                    @HystrixProperty(name="circuitBreaker.errorThresholdPercentage", value="75"),
                    // 차단 후 서비스의 회복 상태를 확인할 때까지 대기할 시간
                    @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds", value="7000"),
                    // 서비스 호출 문제를 모니터할 시간 간격을 설정
                    @HystrixProperty(name="metrics.rollingStats.timeInMilliseconds", value="15000"),
                    // 설정한 시간 간격 동안 통계를 수집할 횟수를 설정
                    @HystrixProperty(name="metrics.rollingStats.numBuckets", value="5")}
    )
    public String getIdInfo(String id) {
        return hystrixTestDao.getIdInfo(id);
    }

    private String buildFallbackIdInfo(String id){
        return id + "'s Fallback Info";
    }

}
