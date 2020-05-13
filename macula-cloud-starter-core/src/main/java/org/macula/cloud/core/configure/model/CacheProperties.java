package org.macula.cloud.core.configure.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CacheProperties implements Serializable {

	private static final long serialVersionUID = 1L;

	private boolean j2cache;

}