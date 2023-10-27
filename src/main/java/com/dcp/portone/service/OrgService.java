package com.dcp.portone.service;

import com.dcp.portone.entity.Org;
import com.dcp.portone.model.OrgRequestModel;
import com.dcp.portone.model.OrgnizationModel;

import java.util.List;

public interface OrgService {
    Org saveOrganization(OrgnizationModel org);
    List<OrgnizationModel> getOrgs();

    OrgnizationModel getOrgById(Long id);

    Org updateOrg(OrgnizationModel org);
    void deleteOrgById(Long orgId);

    Org insertOrg(OrgRequestModel orgModel);
}
