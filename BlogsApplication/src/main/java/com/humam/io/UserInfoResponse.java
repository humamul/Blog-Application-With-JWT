package com.humam.io;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserInfoResponse {
	private Integer id;
	private String username;
	private List<String> roles;
}
