package com.example.spetsrestapi.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@MappedSuperclass
@NoArgsConstructor
public class BaseResponse {

	private long version;

	@JsonFormat(pattern="dd/MM/yyyy")
	private Date createdAt;

	@JsonFormat(pattern="dd/MM/yyyy")
	private Date updatedAt;

}
