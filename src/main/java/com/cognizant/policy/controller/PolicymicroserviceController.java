package com.cognizant.policy.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cognizant.policy.model.Benefits;
import com.cognizant.policy.model.MemberPolicy;
import com.cognizant.policy.model.ProviderPolicy;
import com.cognizant.policy.repository.BenefitRepo;
import com.cognizant.policy.repository.MemberPolicyRepo;
import com.cognizant.policy.repository.ProviderPolicyRepo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/policy")
@Api(value = "policy Microservice Controller", description = "operations related to getting the providers for the claim, getting eligible benfits and also getting the eligible claim Amount")
public class PolicymicroserviceController {
	
	@Autowired
	public ProviderPolicyRepo providerPolicyRepo;

	@Autowired
	public MemberPolicyRepo memberPolicyRepo;

	@Autowired
	public BenefitRepo benefitRepo;

	
	@GetMapping(path = "/getChainOfProviders/{policyId}")
	public ResponseEntity<?> getChainOfProviders(@PathVariable("policyId") int policyId) {
	
		List<ProviderPolicy> providers = providerPolicyRepo.findAllByPolicyIdOrderByLocation(policyId);
		
		return new ResponseEntity<>(providers, HttpStatus.OK);
	}

	@ApiOperation(value = "List of providers which accepts the claim for particular policy")
	@GetMapping(path = "getEligibleBenefits/{policyId}/{memberId}")
	public ResponseEntity<?> getEligibleBenefits(@PathVariable("policyId") int policyId,
			@PathVariable("memberId") int memberId) 
	{

		List<MemberPolicy> providers = memberPolicyRepo.findAllByPolicyIdAndMemberId(policyId, memberId);

		List<Benefits> benefitDetails = new ArrayList<Benefits>();
		for (MemberPolicy memberPolicy : providers) {
			int benefitId = memberPolicy.getBenefitId();
			benefitDetails.add(benefitRepo.findByBenefitId(benefitId));

		}
		return new ResponseEntity<>(benefitDetails, HttpStatus.OK);

	}

	@ApiOperation(value = "List of benefits which can be aviled under a particular policy")
	@GetMapping(value = "/getClaimAmount/{policyId}/{memberId}/{benefitId}")
	public ResponseEntity<?> getEligibleClaimAmount(@PathVariable int policyId, @PathVariable int memberId,
			@PathVariable int benefitId) 
	{
		
		MemberPolicy mempolicy = memberPolicyRepo.findCapAmount(policyId, memberId, benefitId);

		return new ResponseEntity<Double>(mempolicy.getCapAmountBenefits(), HttpStatus.OK);
	}

}
