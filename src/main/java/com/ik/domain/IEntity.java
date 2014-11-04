package com.ik.domain;

import java.io.Serializable;

public interface IEntity extends Serializable {
	public Long getId();
	public void setId(final Long id);
}