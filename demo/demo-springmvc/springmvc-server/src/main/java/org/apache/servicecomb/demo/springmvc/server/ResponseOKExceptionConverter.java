/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.servicecomb.demo.springmvc.server;

import javax.ws.rs.core.Response.Status;

import org.apache.servicecomb.swagger.invocation.Response;
import org.apache.servicecomb.swagger.invocation.SwaggerInvocation;
import org.apache.servicecomb.swagger.invocation.exception.ExceptionToProducerResponseConverter;
import org.apache.servicecomb.swagger.invocation.exception.InvocationException;

public class ResponseOKExceptionConverter implements
    ExceptionToProducerResponseConverter<ResponseOKException> {

  @Override
  public Class<ResponseOKException> getExceptionClass() {
    return ResponseOKException.class;
  }

  @Override
  public Response convert(SwaggerInvocation swaggerInvocation, ResponseOKException e) {
    // This is for compatible usage. For best practise, any status code
    // should have only one type of response.
    return Response.createFail(new InvocationException(Status.OK,
        new ResponseOKData("code-005", "error-005")));
  }
}
