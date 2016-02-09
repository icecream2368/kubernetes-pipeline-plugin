/*
 * Copyright (C) 2015 Original Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.fabric8.kubernetes.workflow;

import hudson.Extension;
import org.jenkinsci.plugins.workflow.steps.AbstractStepDescriptorImpl;
import org.jenkinsci.plugins.workflow.steps.AbstractStepImpl;
import org.jenkinsci.plugins.workflow.steps.StepExecution;
import org.kohsuke.stapler.DataBoundConstructor;

import java.io.File;
import java.util.Map;

public class ApplyStep extends AbstractStepImpl {

    private final String file;
    private final String environment;
    private final String registry;
    private Boolean createNewResources = true;
    private Boolean servicesOnly = false;
    private Boolean ignoreServices = false;
    private Boolean ignoreRunningOAuthClients = false;
    private Boolean processTemplatesLocally = false;
    private Boolean deletePodsOnReplicationControllerUpdate = false;
    private Boolean rollingUpgrades = true;
    private Boolean rollingUpgradePreserveScale = true;

    @DataBoundConstructor
    public ApplyStep(String file, String environment, String registry, Boolean createNewResources, Boolean servicesOnly, Boolean ignoreServices, Boolean ignoreRunningOAuthClients, Boolean processTemplatesLocally, Boolean deletePodsOnReplicationControllerUpdate, Boolean rollingUpgrades, Boolean rollingUpgradePreserveScale){
        this.file = file;
        this.environment = environment;
        this.registry = registry;
        if (createNewResources != null) this.createNewResources = createNewResources;
        if (servicesOnly != null) this.servicesOnly = servicesOnly;
        if (ignoreServices != null) this.ignoreServices = ignoreServices;
        if (ignoreRunningOAuthClients != null) this.ignoreRunningOAuthClients = ignoreRunningOAuthClients;
        if (processTemplatesLocally != null) this.processTemplatesLocally = processTemplatesLocally;
        if (deletePodsOnReplicationControllerUpdate != null) this.deletePodsOnReplicationControllerUpdate = deletePodsOnReplicationControllerUpdate;
        if (rollingUpgrades != null) this.rollingUpgrades = rollingUpgrades;
        if (createNewResources != null) this.rollingUpgradePreserveScale = rollingUpgradePreserveScale;

    }

    public String getFile() {
        return file;
    }

    public String getEnvironment() {
        return environment;
    }

    public String getRegistry() { return registry; }

    public Boolean getCreateNewResources() {
        return createNewResources;
    }

    public Boolean getServicesOnly() {
        return servicesOnly;
    }

    public Boolean getIgnoreServices() {
        return ignoreServices;
    }

    public Boolean getIgnoreRunningOAuthClients() {
        return ignoreRunningOAuthClients;
    }

    public Boolean getProcessTemplatesLocally() {
        return processTemplatesLocally;
    }

    public Boolean getDeletePodsOnReplicationControllerUpdate() {
        return deletePodsOnReplicationControllerUpdate;
    }

    public Boolean getRollingUpgrades() {
        return rollingUpgrades;
    }

    public Boolean getRollingUpgradePreserveScale() {
        return rollingUpgradePreserveScale;
    }

    @Extension
    public static class DescriptorImpl extends AbstractStepDescriptorImpl {

        public DescriptorImpl() {
            super(ApplyStepExecution.class);
        }

        public DescriptorImpl(Class<? extends StepExecution> executionType) {
            super(executionType);
        }

        @Override
        public String getFunctionName() {
            return "kubernetesApply";
        }

        @Override
        public String getDisplayName() {
            return "Apply resources to Kubernetes, lazily creating environments and routes";
        }
    }
}
