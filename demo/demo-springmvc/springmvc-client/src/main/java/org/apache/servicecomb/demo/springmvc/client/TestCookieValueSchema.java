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
package org.apache.servicecomb.demo.springmvc.client;

import org.apache.servicecomb.demo.CategorizedTestCase;
import org.apache.servicecomb.demo.TestMgr;
import org.apache.servicecomb.provider.springmvc.reference.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class TestCookieValueSchema implements CategorizedTestCase {
  private final RestTemplate restTemplate = RestTemplateBuilder.create();

  @Override
  public void testAllTransport() {
    testCookieValueInt();
    testCookieValueIntDefault();
    testCookieValueString();
    testCookieValueStringDefault();
  }

  private void testCookieValueIntDefault() {
    Integer result =
        restTemplate.getForObject("servicecomb://springmvc/cookie/testCookieDefaultValueInt",
            Integer.class);
    TestMgr.check(100, result);
  }

  private void testCookieValueStringDefault() {
    String result =
        restTemplate.getForObject("servicecomb://springmvc/cookie/testCookieDefaultValueString",
            String.class);
    TestMgr.check("foo", result);
  }

  private void testCookieValueInt() {
    HttpHeaders headers = new HttpHeaders();
    headers.add(HttpHeaders.COOKIE, "param=3");

    HttpEntity<?> requestEntity = new HttpEntity<>(headers);
    ResponseEntity<Integer> result =
        restTemplate.exchange("servicecomb://springmvc/cookie/testCookieDefaultValueInt",
            HttpMethod.GET, requestEntity, Integer.class);
    TestMgr.check(3, result.getBody());
  }

  private void testCookieValueString() {
    HttpHeaders headers = new HttpHeaders();
    headers.add(HttpHeaders.COOKIE, "param=hi");

    HttpEntity<?> requestEntity = new HttpEntity<>(headers);
    ResponseEntity<String> result =
        restTemplate.exchange("servicecomb://springmvc/cookie/testCookieDefaultValueString",
            HttpMethod.GET, requestEntity, String.class);
    TestMgr.check("hi", result.getBody());
  }
}
