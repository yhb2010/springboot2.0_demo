package com.websocketchat;

import java.io.Serializable;

import lombok.Setter;
import lombok.Getter;
import lombok.ToString;

@SuppressWarnings("serial")
@Getter
@Setter
@ToString
public class Info implements Serializable {

	private String toUserId;
	private String fromUserId;
	private String contentText;

}
