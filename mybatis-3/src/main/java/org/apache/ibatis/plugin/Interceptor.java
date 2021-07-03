/**
 *    Copyright 2009-2015 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.apache.ibatis.plugin;

import java.util.Properties;

/**
 * @author Clinton Begin
 */
public interface Interceptor {

  /**
   * 用于定义拦截逻辑，该方法会在目标方法调用时执行。
   *
   * @param invocation
   * @return
   * @throws Throwable
   */
  Object intercept(Invocation invocation) throws Throwable;

  /**
   * 用于创建Executor、ParameterHandler、ResultSetHandler或StatementHandler的代理对象，
   * 该方法的参数即为Executor、ParameterHandler、ResultSetHandler或StatementHandler组件的实例。
   *
   * @param target Executor、ParameterHandler、ResultSetHandler或StatementHandler组件的实例。
   * @return
   */
  Object plugin(Object target);

  /**
   * 用于设置插件的属性值。
   *
   * @param properties
   */
  void setProperties(Properties properties);

}
