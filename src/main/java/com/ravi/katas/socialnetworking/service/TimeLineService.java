package com.ravi.katas.socialnetworking.service;

import com.ravi.katas.socialnetworking.domain.TimelineMessage;
import com.ravi.katas.socialnetworking.util.TimeUtil;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import javax.swing.text.html.Option;

public class TimeLineService {

  private String user;
  private Set<String> follows = new HashSet<>();
  private static TimeUtil timeUtil = new TimeUtil();
  public TimeLineService(String user){
    this.user = user;
  }
  private static Map<String,List<TimelineMessage>> timeline = new HashMap<>();
  public void publishToTimeline(TimelineMessage message) {
    List<TimelineMessage> messages = timeline.getOrDefault(user, new ArrayList<>());
    messages.add(message);
    timeline.put(user,messages);
  }

  public String viewTimeline(Optional<String> user) {
    List<String> messages = getMessages(user.orElse(getUser()));
    if(follows.size()>0){
      follows.stream().forEach(follow-> {
        messages.addAll(getMessages(follow));
      });
    }
    return String.join("\\n\\r", messages);
  }

  private List<String> getMessages(String user) {
    return timeline.getOrDefault(user, new ArrayList<>()).stream().map(timeline->
      new StringBuilder().append(timeline.getMessage()).append(" (").append(timeUtil.getDifference(
          LocalDateTime.now(),timeline.getCreateDate())).append(")").toString()
    ).collect(
        Collectors.toList());
  }

  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }


  public Boolean follow(Set<String> followUsers) {
    return follows.addAll(followUsers);
  }
}
