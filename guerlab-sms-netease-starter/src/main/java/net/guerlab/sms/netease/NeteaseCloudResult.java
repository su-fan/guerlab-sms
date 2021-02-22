/*
 * Copyright 2018-2021 the original author or authors.
 *
 * Licensed under the GNU LESSER GENERAL PUBLIC LICENSE, Version 3 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.guerlab.sms.netease;

import lombok.Data;

/**
 * 响应结果
 *
 * @author guer
 */
@Data
public class NeteaseCloudResult {

    /**
     * 成功代码
     */
    public static final Integer SUCCESS_CODE = 200;

    /**
     * 请求返回的结果码。
     */
    private int code;

    /**
     * 请求返回的结果码描述。
     */
    private String msg;

    private Long obj;
}
