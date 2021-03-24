package kr.pe.chani.simplemsa.service1.hystrix;

import kr.pe.chani.simplemsa.service1.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class HystrixTestDao {

    public String getIdInfo(String id) {
        log.info("Searching from Database ...");

        /** DB 호출 3회중 랜덤 1회 11초 지연 */
        CommonUtil.randomlyRunLong();
        return id + "'s info";
    }
}
