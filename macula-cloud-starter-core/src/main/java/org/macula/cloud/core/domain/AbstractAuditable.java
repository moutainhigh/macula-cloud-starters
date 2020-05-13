package org.macula.cloud.core.domain;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.springframework.data.domain.Auditable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.ToString;

@MappedSuperclass
@ToString(callSuper = true)
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractAuditable<PK extends Serializable> extends AbstractPersistable<PK>
		implements Auditable<String, PK, Instant> {

	@Version
	@Column(name = "OBJECT_VERSION_NUMBER", nullable = false, length = 50)
	private Long version;

	@Column(name = "CREATED_BY", nullable = false, length = 50)
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_TIME", nullable = false)
	private Date createdDate;

	@Column(name = "LAST_UPDATED_BY", nullable = false, length = 50)
	private String lastModifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LAST_UPDATED_TIME", nullable = false)
	private Date lastModifiedDate;

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	@Override
	public Optional<String> getCreatedBy() {
		return Optional.ofNullable(createdBy);
	}

	@Override
	public void setCreatedBy(final String createdBy) {
		this.createdBy = createdBy;
	}

	@Override
	public Optional<Instant> getCreatedDate() {
		if (createdDate == null) {
			return Optional.empty();
		}
		return Optional.of(Instant.ofEpochMilli(createdDate.getTime()));
	}

	@Override
	public Optional<String> getLastModifiedBy() {
		return Optional.ofNullable(lastModifiedBy);
	}

	@Override
	public void setLastModifiedBy(final String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	@Override
	public Optional<Instant> getLastModifiedDate() {
		if (lastModifiedDate == null) {
			return Optional.empty();
		}
		return Optional.of(Instant.ofEpochMilli(lastModifiedDate.getTime()));
	}

	@Override
	public void setCreatedDate(Instant creationDate) {
		if (creationDate != null) {
			this.createdDate = new Date(creationDate.toEpochMilli());
		}
	}

	@Override
	public void setLastModifiedDate(Instant lastModifiedDate) {
		if (lastModifiedDate != null) {
			this.lastModifiedDate = new Date(lastModifiedDate.toEpochMilli());
		}
	}

	public AbstractAuditable<PK> clone(AbstractAuditable<PK> entity) {
		super.clone(entity);
		entity.setVersion(getVersion());
		entity.setCreatedBy(getCreatedBy().orElse(null));
		entity.setCreatedDate(getCreatedDate().orElse(null));
		entity.setLastModifiedBy(getLastModifiedBy().orElse(null));
		entity.setLastModifiedDate(getLastModifiedDate().orElse(null));
		return entity;
	}
}