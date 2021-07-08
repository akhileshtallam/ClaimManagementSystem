package com.cognizant.policy.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProviderPolicy 
{	
	@Id
	private int providerId;
	private String providerName;
	private String providerAddress;
	private int policyId;
	private String location;
}
