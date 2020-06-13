package com.ravi.katas.socialnetworking.service;

import com.ravi.katas.socialnetworking.domain.TimelineMessage;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class TimelineServiceTest {

  TimeLineService aliceTimeLineService;
  TimeLineService bobTimeLineService;
  TimeLineService charlietimeLineService;
  @Before
  public void setup(){
     aliceTimeLineService = new TimeLineService("alice");
     bobTimeLineService = new TimeLineService("bob");
     charlietimeLineService = new TimeLineService("charlie");
  }
  @After
  public void tearup(){
    aliceTimeLineService = null;
    bobTimeLineService = null;
    charlietimeLineService = null;
  }
  @Test
  public void feature01_testTimelinePublish(){
    aliceTimeLineService = new TimeLineService("alice");
    aliceTimeLineService.publishToTimeline(new TimelineMessage("test message 01"));
    assert aliceTimeLineService.viewTimeline(Optional.empty()).contains("test message 01");
    aliceTimeLineService.publishToTimeline(new TimelineMessage("test message 02"));
    assert aliceTimeLineService.viewTimeline(Optional.of("alice")).contains("test message 01");
    assert aliceTimeLineService.viewTimeline(Optional.of("alice")).contains("test message 02");
  }

  @Test
  public void feature02_testViewTimelineByUser(){
     aliceTimeLineService = new TimeLineService("alice");
    aliceTimeLineService.publishToTimeline(new TimelineMessage("test message 01"));
     bobTimeLineService = new TimeLineService("bob");
    bobTimeLineService.publishToTimeline(new TimelineMessage("test message 02"));
    assert bobTimeLineService.viewTimeline(Optional.of("alice")).contains("test message 01");
    assert bobTimeLineService.viewTimeline(Optional.empty()).contains("test message 02");
    assert bobTimeLineService.viewTimeline(Optional.of("steve")).contains("");
  }

  @Test
  public void feature02_testFollowUser(){
     aliceTimeLineService = new TimeLineService("alice");
    aliceTimeLineService.publishToTimeline(new TimelineMessage("test message 01"));
     bobTimeLineService = new TimeLineService("bob");
    bobTimeLineService.publishToTimeline(new TimelineMessage("test message 02"));
     charlietimeLineService = new TimeLineService("charlie");
    charlietimeLineService.publishToTimeline(new TimelineMessage("test message 03"));
    charlietimeLineService.publishToTimeline(new TimelineMessage("test message 04"));
    Set<String> followUsers = new HashSet<>();
    followUsers.add("alice");
    followUsers.add("bob");
    charlietimeLineService.follow(followUsers);
    String messages = charlietimeLineService.viewTimeline(Optional.empty());
    assert messages.contains("test message 03");
    assert messages.contains("test message 04");
    assert messages.contains("test message 01");
    assert messages.contains("test message 02");

  }

}
