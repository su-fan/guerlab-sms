package net.guerlab.sms.core.handler;

import net.guerlab.sms.core.domain.NoticeData;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Collection;

/**
 * 发送处理
 *
 * @author guer
 *
 */
public interface SendHandler {

    /**
     * 发送通知
     * 
     * @param noticeData
     *            通知内容
     * @param phones
     *            手机号列表
     * @return 是否发送成功
     */
    boolean send(NoticeData noticeData, Collection<String> phones);

    /**
     * 发送通知
     * 
     * @param noticeData
     *            通知内容
     * @param phone
     *            手机号列表
     * @return 是否发送成功
     */
    default boolean send(NoticeData noticeData, String phone) {
        if (StringUtils.isBlank(phone)) {
            return false;
        }

        return send(noticeData, Arrays.asList(phone));
    }

    /**
     * 发送通知
     * 
     * @param noticeData
     *            通知内容
     * @param phones
     *            手机号列表
     * @return 是否发送成功
     */
    default boolean send(NoticeData noticeData, String... phones) {
        if (phones == null) {
            return false;
        }

        return send(noticeData, Arrays.asList(phones));
    }
}