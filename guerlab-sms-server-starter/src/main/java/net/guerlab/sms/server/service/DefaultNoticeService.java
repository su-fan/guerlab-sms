package net.guerlab.sms.server.service;

import net.guerlab.commons.collection.CollectionUtil;
import net.guerlab.sms.core.domain.NoticeData;
import net.guerlab.sms.core.exception.NotFindSendHandlerException;
import net.guerlab.sms.core.handler.SendHandler;
import net.guerlab.sms.server.properties.SmsProperties;
import net.guerlab.spring.commons.util.SpringApplicationContextUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 短信通知服务实现
 *
 * @author guer
 *
 */
@Service
public class DefaultNoticeService implements NoticeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultNoticeService.class);

    @Autowired
    private SmsProperties properties;

    @Override
    public boolean phoneRegValidation(String phone) {
        return StringUtils.isNotBlank(phone)
                && (StringUtils.isBlank(properties.getReg()) || phone.matches(properties.getReg()));
    }

    @Override
    public boolean send(NoticeData noticeData, Collection<String> phones) {
        if (noticeData == null) {
            LOGGER.debug("noticeData is null");
            return false;
        }
        if (CollectionUtil.isEmpty(phones)) {
            LOGGER.debug("phones is empty");
            return false;
        }

        List<String> phoneList = phones.stream().filter(this::phoneRegValidation).collect(Collectors.toList());

        if (CollectionUtil.isEmpty(phoneList)) {
            LOGGER.debug("filted phones is empty");
            return false;
        }

        Map<String, SendHandler> sendHandlerMap = SpringApplicationContextUtil.getContext()
                .getBeansOfType(SendHandler.class);

        if (sendHandlerMap.isEmpty()) {
            throw new NotFindSendHandlerException();
        }

        Optional<SendHandler> optional = sendHandlerMap.values().stream().findAny();

        if (optional.isPresent()) {
            return optional.get().send(noticeData, phones);
        } else {
            return false;
        }
    }

}