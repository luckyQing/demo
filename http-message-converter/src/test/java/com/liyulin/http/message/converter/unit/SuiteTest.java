package com.liyulin.http.message.converter.unit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.liyulin.http.message.converter.unit.cases.JsonProductControllerTest;
import com.liyulin.http.message.converter.unit.cases.XmlProductControllerTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({ JsonProductControllerTest.class, XmlProductControllerTest.class })
public class SuiteTest {

}
