package util.beans;

import com.zyr.common.util.beans.TransferUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Optional;

/**
 * <p>功能描述</p>
 *
 * @author zhengyongrui.
 * @version 1.0
 * Create In  2019-04-09
 */
@Slf4j
public class TransferUtilTest {

    @Test
    public void testCopyBean() throws Exception {
        TransferUtilTestBeanBySource sourceBean = new TransferUtilTestBeanBySource();
        sourceBean.setStr1("1");
        sourceBean.setInt1(1);
        sourceBean.setInt2(2);
        TransferUtilTestBeanByTarget targetBean = TransferUtil.copyBean(sourceBean, TransferUtilTestBeanByTarget.class, false);
        if (!"TransferUtilTestBeanByTarget{str1='1', str2='1', int1=2, int2=1}".equals(Optional.ofNullable(targetBean).orElse(new TransferUtilTestBeanByTarget()).toString())) {
            throw new Exception("TransferUtil测试通过不通过");
        } else {
            log.info("TransferUtil测试通过");
        }
    }

}
