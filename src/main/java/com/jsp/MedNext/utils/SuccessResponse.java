package com.jsp.MedNext.utils;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SuccessResponse {
	
	private int statusCode;
	private String msg;
	private LocalDateTime dateTime;
	private Object data;
}
