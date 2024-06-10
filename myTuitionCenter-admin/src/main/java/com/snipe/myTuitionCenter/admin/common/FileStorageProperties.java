package com.snipe.myTuitionCenter.admin.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties(prefix = "mytuitioncenter")
@Configuration("fileproperties")
public class FileStorageProperties {

	@Value("${mytuitioncenter.upload-dir}")
	private String uploadDir;
}
