package com.ravi.katas.socialnetworking.domain;

import java.time.LocalDateTime;

public class TimelineMessage {
  private String message;
  private LocalDateTime createDate;

  public LocalDateTime getCreateDate() {
    return createDate;
  }

  public void setCreateDate(LocalDateTime createDate) {
    this.createDate = createDate;
  }

  public TimelineMessage(String message) {
    this.message = message;
    this.createDate = LocalDateTime.now();
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }


}
