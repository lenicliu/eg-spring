package com.lenicliu.spring.boot.generator;

import org.springframework.boot.ExitCodeGenerator;

public class SuccessExitCodeGenerator implements ExitCodeGenerator {

	@Override
	public int getExitCode() {
		return 0;
	}
}