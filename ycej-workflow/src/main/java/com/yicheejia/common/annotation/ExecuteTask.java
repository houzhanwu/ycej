/**
 *
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.yicheejia.common.annotation;

import java.lang.annotation.*;

/**
 * 执行任务
 * @author hunk
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExecuteTask {
    String value() default "";
    /**
     * 业务单据主键名称
     * @return
     */
    String id() default "";
    /**
     * 流程key
     * @return
     */
    String processKey() default "";
    /**
     * 业务参数  ,需要参与运算的业务值
     * @return
     */
    String[] args() default "";
}
